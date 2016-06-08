package kits.bioinfo.motif;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import kits.bioinfo.core.Sequence;

public class RandomizedMotifFinder {

	private Random random = new Random();
	
	private final int runs;
	
	public RandomizedMotifFinder(int runs) {
		this.runs = runs;
	}
	
	public List<Sequence> findMotifs(List<Sequence> sequences, int k) {
		List<Sequence> bestMotifs = Collections.emptyList();
		int bestScore = Integer.MAX_VALUE;
		for(int i=0;i<runs;i++) {
			List<Sequence> motifs = runFindMotifsOnce(sequences, k);
			int score = Motifs.score(motifs);
			if(score < bestScore) {
				bestScore = score;
				bestMotifs = motifs;
			}
		}
		return bestMotifs;
	}
	
	private List<Sequence> runFindMotifsOnce(List<Sequence> sequences, int k) {
		
		if(sequences.isEmpty()) {
			throw new IllegalArgumentException("Can not run without sequences");
		}
		
		List<Sequence> bestMotifs = randomKmers(sequences, k);
		int bestScore = Motifs.score(bestMotifs);
		
		while(true) {
			ProfileMatrix profileMatrix = ProfileMatrix.buildWithPseudoCounts(bestMotifs);
			List<Sequence> motifs = sequences.stream().map(sequence -> profileMatrix.findMostProbableKmer(sequence)).collect(Collectors.toList());
			
			int score = Motifs.score(motifs);
			if(score < bestScore) {
				bestScore = score;
				bestMotifs = motifs;
			} else {
				return bestMotifs;
			}
		}
	}
	
	private List<Sequence> randomKmers(List<Sequence> sequences, int k) {
		return sequences.stream().map(sequence -> randomKmer(sequence, k)).collect(Collectors.toList());
	}
	
	private Sequence randomKmer(Sequence sequence, int k) {
		return sequence.subSequence(random.nextInt(sequence.length()-k+1), k);
	}
	
}
