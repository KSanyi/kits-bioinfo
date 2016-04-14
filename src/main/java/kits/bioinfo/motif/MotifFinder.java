package kits.bioinfo.motif;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import kits.bioinfo.base.Sequence;
import kits.bioinfo.matcher.ApproximateSubSequenceMatcher;
import kits.bioinfo.matcher.Matcher;

public class MotifFinder {

	public Set<Sequence> findMotifs(Collection<Sequence> sequences, int k, int d) {
		
		if(sequences.isEmpty()) {
			throw new IllegalArgumentException("Empty sequence list");
		}
		
		Sequence firstSequence = sequences.iterator().next();
		Set<Sequence> candidateMotifs = generateCandidateMotifs(firstSequence, k);
		
		Set<Sequence> motifs = new HashSet<>();
		for(Sequence motif : candidateMotifs) {
			Matcher matcher = new ApproximateSubSequenceMatcher(motif, d);
			if(sequences.stream().allMatch(s -> matcher.matches(s))) {
				motifs.add(motif);
			}
		}
		
		return motifs;
	}
	
	private Set<Sequence> generateCandidateMotifs(Sequence firstSequence, int k) {
		Set<Sequence> candidateMotifs = new HashSet<>();
		
		for(int index=0;index<firstSequence.length()-k+1;index++) {
			Sequence kmer = firstSequence.subSequence(index, k);
			candidateMotifs.addAll(kmer.neighbours(k));
		}
		
		return candidateMotifs;
	}
	
}
