package kits.bioinfo.motif;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import kits.bioinfo.core.DnaSequence;
import kits.bioinfo.util.AllPossibleSequenceGenerator;

public class MedianStringFinder {

	public Set<DnaSequence> findMedianStrings(Collection<DnaSequence> sequences, int k) {
		Set<DnaSequence> candidateKmers = new AllPossibleSequenceGenerator().generateAllPossibleSequences(k);
		int minDistance = Integer.MAX_VALUE;
		final Set<DnaSequence> medianStrings = new HashSet<>();
		for(DnaSequence candidate : candidateKmers) {
			int distance = Motifs.distance(sequences, candidate);
			if(distance < minDistance) {
				minDistance = distance;
				medianStrings.clear();
				medianStrings.add(candidate);
			} else if(distance == minDistance) {
				medianStrings.add(candidate);
			}
		}
		return medianStrings;
	}
	
}
