package kits.bioinfo.motif;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import kits.bioinfo.core.Sequence;
import kits.bioinfo.util.AllPossibleSequenceGenerator;

public class MedianStringFinder {

	public Set<Sequence> findMedianStrings(Collection<Sequence> sequences, int k) {
		Set<Sequence> candidateKmers = new AllPossibleSequenceGenerator().generateAllPossibleSequences(k);
		int minDistance = Integer.MAX_VALUE;
		final Set<Sequence> medianStrings = new HashSet<>();
		for(Sequence candidate : candidateKmers) {
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
