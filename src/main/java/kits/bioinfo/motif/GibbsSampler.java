package kits.bioinfo.motif;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import kits.bioinfo.base.Sequence;

public class GibbsSampler {

	private Random random = new Random();
	
	final int runs;
	
	public GibbsSampler(int runs) {
		this.runs = runs;
	}
	
	public List<Sequence> findMotifs(List<Sequence> sequences, int k, int n) {
		List<Sequence> bestMotifs = Collections.emptyList();
		int bestScore = Integer.MAX_VALUE;
		for(int i=0;i<runs;i++) {
			List<Sequence> motifs = runFindMotifsOnce(sequences, k, n);
			int score = Motifs.score(motifs);
			System.out.println("Score: " + score);
			if(score < bestScore) {
				bestScore = score;
				bestMotifs = motifs;
			}
		}
		return bestMotifs;
	}
	
	private List<Sequence> runFindMotifsOnce(List<Sequence> sequences, int k, int n) {
		
		if(sequences.isEmpty()) {
			throw new IllegalArgumentException("Can not run without sequences");
		}
		
		List<Sequence> bestMotifs = randomKmers(sequences, k);
		int bestScore = Motifs.score(bestMotifs);
		
		for(int i=0;i<n;i++) {
			int indexToLeftOut = random.nextInt(sequences.size());
			Sequence motifToLeftOut = bestMotifs.get(indexToLeftOut);
			Sequence sequenceToLeftOut = sequences.get(indexToLeftOut);
			
			List<Sequence> motifs = new ArrayList<>();
			motifs.addAll(bestMotifs);
			motifs.remove(motifToLeftOut);
			ProfileMatrix profileMatrix = ProfileMatrix.buildWithPseudoCounts(motifs);
			Sequence newMotif = profileRandomKmer(profileMatrix, sequenceToLeftOut, k);
			motifs.add(indexToLeftOut, newMotif);
			
			int score = Motifs.score(motifs);
			if(score < bestScore) {
				bestScore = score;
				bestMotifs = motifs;
			}
		}
		
		return bestMotifs;
	}
	
	private List<Sequence> randomKmers(List<Sequence> sequences, int k) {
		return sequences.stream().map(sequence -> randomKmer(sequence, k)).collect(Collectors.toList());
	}
	
	private Sequence randomKmer(Sequence sequence, int k) {
		return sequence.subSequence(random.nextInt(sequence.length()-k+1), k);
	}
	
	private Sequence profileRandomKmer(ProfileMatrix profileMatrix, Sequence sequence, int k) {
		List<BigDecimal> probabilities = sequence.allSubSequences(k).stream().map(kmer -> profileMatrix.calculateProbability(kmer)).collect(Collectors.toList());
		return sequence.subSequence(new ProbabilityDistribution(probabilities).randomInt(), k);
	}
	
}
