package kits.bioinfo.peptidsequencing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import kits.bioinfo.core.AminoAcid;
import kits.bioinfo.core.Peptid;
import kits.bioinfo.util.FrequencyMap;

public class CycloPeptidSequencer extends CycloPeptidSequencerBase {

	private final int peptidCutRank;
	private final int aminoAcidCutRank;

	public CycloPeptidSequencer(int peptidCutRank, int aminoAcidCutRank) {
		this.peptidCutRank = peptidCutRank;
		this.aminoAcidCutRank = aminoAcidCutRank;
	}

	public Set<Peptid> sequencePeptids(List<Integer> spectrum) {
		List<Integer> sortedSpectrum = new ArrayList<>(spectrum);
		Collections.sort(sortedSpectrum);
		final List<Integer> experimentalSpectrum = Collections.unmodifiableList(sortedSpectrum);
		final Set<AminoAcid> candidateAminoAcids = aminoAcidCutRank < AminoAcid.values().length ? guessCandidateAminoAcids(experimentalSpectrum)
				: new HashSet<>(Arrays.asList(AminoAcid.values()));
		final int experimentalMass = experimentalSpectrum.get(experimentalSpectrum.size() - 1);
		final Set<Peptid> matchingPeptids = new HashSet<>();
		Set<Peptid> candidatePeptids = new HashSet<>();
		candidatePeptids.add(new Peptid(new LinkedList<>()));

		while (!candidatePeptids.isEmpty()) {
			candidatePeptids = extendedPeptids(candidatePeptids, candidateAminoAcids);
			Map<Peptid, Integer> peptidScores = candidatePeptids.stream()
					.collect(Collectors.toMap(Function.identity(), peptid -> linearScore(peptid, experimentalSpectrum)));
			candidatePeptids = cut(peptidScores, peptidCutRank);

			List<Peptid> matchingCandidates = candidatePeptids.stream().filter(peptid -> peptid.mass() == experimentalMass)
					.collect(Collectors.toList());
			matchingPeptids.addAll(matchingCandidates);
			candidatePeptids.removeAll(matchingCandidates);
			List<Peptid> tooLargeCandidates = candidatePeptids.stream().filter(peptid -> peptid.mass() > experimentalMass)
					.collect(Collectors.toList());
			candidatePeptids.removeAll(tooLargeCandidates);
		}

		Map<Peptid, Integer> peptidScores = matchingPeptids.stream()
				.collect(Collectors.toMap(Function.identity(), peptid -> linearScore(peptid, experimentalSpectrum)));

		return cut(peptidScores, 1);
	}

	private Set<AminoAcid> guessCandidateAminoAcids(List<Integer> spectrum) {
		List<Integer> convolution = generateConvolution(spectrum);
		Set<Integer> candidateMasses = candidateMasses(convolution);
		Set<AminoAcid> aminoAcids = new HashSet<>();
		for (int mass : candidateMasses) {
			aminoAcids.addAll(Arrays.asList(AminoAcid.values()).stream().filter(aminoAcid -> aminoAcid.mass == mass).collect(Collectors.toSet()));
		}
		return aminoAcids;
	}

	private List<Integer> generateConvolution(List<Integer> spectrum) {
		List<Integer> convolution = new LinkedList<>();
		for (int i = 0; i < spectrum.size(); i++) {
			for (int j = 0; j < i; j++) {
				if (spectrum.get(i) > spectrum.get(j)) {
					convolution.add(spectrum.get(i) - spectrum.get(j));
				}
			}
		}
		return Collections.unmodifiableList(convolution);
	}

	private Set<Integer> candidateMasses(List<Integer> convolution) {
		List<Integer> relevantConvolution = convolution.stream().filter(value -> 57 <= value && value <= 200).collect(Collectors.toList());
		if (relevantConvolution.size() <= aminoAcidCutRank) {
			return new HashSet<>(relevantConvolution);
		} else {
			FrequencyMap<Integer> frequencyMap = new FrequencyMap<>();
			for (int value : relevantConvolution) {
				frequencyMap.put(value);
			}
			List<Entry<Integer, Integer>> sortedEntries = frequencyMap.sortedEntries();
			int cutFrequency = sortedEntries.get(aminoAcidCutRank).getValue();
			return sortedEntries.stream().filter(entry -> entry.getValue() >= cutFrequency).map(entry -> entry.getKey()).collect(Collectors.toSet());
		}
	}

	private int linearScore(Peptid peptid, List<Integer> experimentalSpectrum) {
		List<Integer> theoreticalSpectrum = new LinkedList<>(new MassSpectrometer().generateMassSpectrumForPeptid(peptid));
		int score = 0;
		for (int value : experimentalSpectrum) {
			if (theoreticalSpectrum.contains(value)) {
				score++;
				theoreticalSpectrum.remove(theoreticalSpectrum.indexOf(value));
			}
		}
		return score;
	}

	private Set<Peptid> cut(Map<Peptid, Integer> peptidScores, int n) {
		if (peptidScores.size() <= n) {
			return peptidScores.keySet();
		}
		List<Integer> scores = new ArrayList<>(peptidScores.values());
		Collections.sort(scores);
		Collections.reverse(scores);
		int cutScore = scores.get(n);
		return peptidScores.keySet().stream().filter(peptid -> peptidScores.get(peptid) >= cutScore).collect(Collectors.toSet());
	}

}
