package kits.bioinfo.peptidsequencing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import kits.bioinfo.core.AminoAcid;
import kits.bioinfo.core.Peptid;

public class CycloPeptidSequencer {

	public static Set<Peptid> sequencePeptids(List<Integer> spectrum){
		final List<Integer> experimentalSpectrum = Collections.unmodifiableList(spectrum);
		final Set<Peptid> result = new HashSet<>();
		Set<Peptid> candidatePeptids = new HashSet<>();
		candidatePeptids.add(new Peptid(new LinkedList<>()));
		
		while(!candidatePeptids.isEmpty()) {
			candidatePeptids = extendedPeptids(candidatePeptids);
			candidatePeptids = candidatePeptids.stream().filter(peptid -> consistentWithSpectrum(peptid, experimentalSpectrum)).collect(Collectors.toSet());
			
			Set<Peptid> matchingPeptids = candidatePeptids.stream().filter(peptid -> matchesWithSpectrum(peptid, experimentalSpectrum)).collect(Collectors.toSet());
			result.addAll(matchingPeptids);
			candidatePeptids.removeAll(matchingPeptids);
		}
		
		return result;
	}
	
	private static Set<Peptid> extendedPeptids(Set<Peptid> peptids){
		Set<Peptid> extendedPeptids = new HashSet<>();
		for(Peptid peptid : peptids){
			for(AminoAcid aminoAcid : AminoAcid.values()){
				extendedPeptids.add(peptid.extendedPeptid(aminoAcid));
			}
		}
		return extendedPeptids;
	}
	
	private static boolean consistentWithSpectrum(Peptid peptid, List<Integer> experimentalSpectrum){
		List<Integer> theoreticalSpectrum = new MassSpectrometer().generateMassSpectrumForPeptid(peptid);
		return experimentalSpectrum.containsAll(theoreticalSpectrum);
	}
	
	private static boolean matchesWithSpectrum(Peptid peptid, List<Integer> experimentalSpectrum){
		List<Integer> theoreticalSpectrum = new MassSpectrometer().generateMassSpectrumForCyclidPeptid(peptid);
		return experimentalSpectrum.equals(theoreticalSpectrum);
	}
	
	public static Set<Peptid> sequencePeptids(List<Integer> spectrum, int cutRank){
		final List<Integer> experimentalSpectrum = Collections.unmodifiableList(spectrum);
		final int experimentalMass = experimentalSpectrum.get(experimentalSpectrum.size()-1);
		final Set<Peptid> matchingPeptids = new HashSet<>();
		Set<Peptid> candidatePeptids = new HashSet<>();
		candidatePeptids.add(new Peptid(new LinkedList<>()));
		
		while(!candidatePeptids.isEmpty()) {
			candidatePeptids = extendedPeptids(candidatePeptids);
			Map<Peptid, Integer> peptidScores = candidatePeptids.stream().collect(Collectors.toMap(Function.identity(), peptid -> linearScore(peptid, experimentalSpectrum)));
			candidatePeptids = cut(peptidScores, cutRank);
			
			List<Peptid> matchingCandidates = candidatePeptids.stream().filter(peptid -> peptid.mass() == experimentalMass).collect(Collectors.toList());
			matchingPeptids.addAll(matchingCandidates);
			candidatePeptids.removeAll(matchingCandidates);
			List<Peptid> tooLargeCandidates = candidatePeptids.stream().filter(peptid -> peptid.mass() > experimentalMass).collect(Collectors.toList());
			candidatePeptids.removeAll(tooLargeCandidates);
		}
		
		Map<Peptid, Integer> peptidScores = matchingPeptids.stream().collect(Collectors.toMap(Function.identity(), peptid -> linearScore(peptid, experimentalSpectrum)));
		
		return cut(peptidScores, 1);
	}
	
	public static int score(Peptid peptid, List<Integer> experimentalSpectrum){
		List<Integer> theoreticalSpectrum = new LinkedList<>(new MassSpectrometer().generateMassSpectrumForCyclidPeptid(peptid));
		int score = 0;
		for(int value : experimentalSpectrum){
			if(theoreticalSpectrum.contains(value)){
				score ++;
				theoreticalSpectrum.remove(theoreticalSpectrum.indexOf(value));
			}
		}
		return score;
	}
	
	private static int linearScore(Peptid peptid, List<Integer> experimentalSpectrum){
		List<Integer> theoreticalSpectrum = new LinkedList<>(new MassSpectrometer().generateMassSpectrumForPeptid(peptid));
		int score = 0;
		for(int value : experimentalSpectrum){
			if(theoreticalSpectrum.contains(value)){
				score ++;
				theoreticalSpectrum.remove(theoreticalSpectrum.indexOf(value));
			}
		}
		return score;
	}
	
	private static Set<Peptid> cut(Map<Peptid, Integer> peptidScores, int n){
		if(peptidScores.size() <= n){
			return peptidScores.keySet();
		}
		List<Integer> scores = new ArrayList<>(peptidScores.values());
		Collections.sort(scores);
		Collections.reverse(scores);
		int cutScore = scores.get(n);
		return peptidScores.keySet().stream().filter(peptid -> peptidScores.get(peptid) >= cutScore).collect(Collectors.toSet());
	}
	
}
