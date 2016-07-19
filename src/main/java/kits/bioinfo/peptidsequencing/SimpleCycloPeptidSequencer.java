package kits.bioinfo.peptidsequencing;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import kits.bioinfo.core.AminoAcid;
import kits.bioinfo.core.Peptid;

public class SimpleCycloPeptidSequencer extends CycloPeptidSequencerBase {

	public Set<Peptid> sequencePeptids(List<Integer> spectrum) {
		final List<Integer> experimentalSpectrum = Collections.unmodifiableList(spectrum);
		final Set<Peptid> result = new HashSet<>();
		Set<Peptid> candidatePeptids = new HashSet<>();
		candidatePeptids.add(new Peptid(new LinkedList<>()));

		while (!candidatePeptids.isEmpty()) {
			candidatePeptids = extendedPeptids(candidatePeptids, new HashSet<>(Arrays.asList(AminoAcid.values())));
			candidatePeptids = candidatePeptids.stream().filter(peptid -> consistentWithSpectrum(peptid, experimentalSpectrum))
					.collect(Collectors.toSet());

			Set<Peptid> matchingPeptids = candidatePeptids.stream().filter(peptid -> matchesWithSpectrum(peptid, experimentalSpectrum))
					.collect(Collectors.toSet());
			result.addAll(matchingPeptids);
			candidatePeptids.removeAll(matchingPeptids);
		}

		return result;
	}

	private boolean consistentWithSpectrum(Peptid peptid, List<Integer> experimentalSpectrum) {
		List<Integer> theoreticalSpectrum = new MassSpectrometer().generateMassSpectrumForPeptid(peptid);
		return experimentalSpectrum.containsAll(theoreticalSpectrum);
	}

	private boolean matchesWithSpectrum(Peptid peptid, List<Integer> experimentalSpectrum) {
		List<Integer> theoreticalSpectrum = new MassSpectrometer().generateMassSpectrumForCyclidPeptid(peptid);
		return experimentalSpectrum.equals(theoreticalSpectrum);
	}

}
