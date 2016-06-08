package kits.bioinfo.motif;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import kits.bioinfo.core.Sequence;

public class GreedyMotifFinder {

	public List<Sequence> findMotifs(List<Sequence> sequences, int k) {
		
		if(sequences.isEmpty()) {
			throw new IllegalArgumentException("Can not run without sequences");
		}
		
		List<Sequence> bestMotifs = Collections.emptyList();
		int bestScore = Integer.MAX_VALUE;
		
		Sequence firstSequence = sequences.iterator().next();
		for(int index=0;index<firstSequence.length()-k+1;index++) {
			List<Sequence> motifs = new ArrayList<>();
			motifs.add(firstSequence.subSequence(index, k));
			ProfileMatrix profileMatrix = ProfileMatrix.buildWithPseudoCounts(motifs);
			for(int i=1;i<sequences.size();i++) {
				motifs.add(profileMatrix.findMostProbableKmer(sequences.get(i)));
				profileMatrix = ProfileMatrix.buildWithPseudoCounts(motifs);
			}
			int score = Motifs.score(motifs);
			if(score < bestScore) {
				bestScore = score;
				bestMotifs = motifs;
			}
		}
		
		return bestMotifs;
		
	}
	
}
