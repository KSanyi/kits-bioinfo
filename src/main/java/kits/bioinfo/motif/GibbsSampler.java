package kits.bioinfo.motif;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import kits.bioinfo.core.DnaSequence;

public class GibbsSampler {

	private Random random = new Random();
	
	final int runs;
	
	public GibbsSampler(int runs) {
		this.runs = runs;
	}
	
	public List<DnaSequence> findMotifs(List<DnaSequence> sequences, int k, int n) {
		List<DnaSequence> bestMotifs = Collections.emptyList();
		int bestScore = Integer.MAX_VALUE;
		for(int i=0;i<runs;i++) {
			List<DnaSequence> motifs = runFindMotifsOnce(sequences, k, n);
			int score = Motifs.score(motifs);
			System.out.println("Score: " + score);
			if(score < bestScore) {
				bestScore = score;
				bestMotifs = motifs;
			}
		}
		return bestMotifs;
	}
	
	private List<DnaSequence> runFindMotifsOnce(List<DnaSequence> sequences, int k, int n) {
		
		if(sequences.isEmpty()) {
			throw new IllegalArgumentException("Can not run without sequences");
		}
		
		List<DnaSequence> bestMotifs = randomKmers(sequences, k);
		int bestScore = Motifs.score(bestMotifs);
		
		for(int i=0;i<n;i++) {
			int indexToLeftOut = random.nextInt(sequences.size());
			DnaSequence motifToLeftOut = bestMotifs.get(indexToLeftOut);
			DnaSequence sequenceToLeftOut = sequences.get(indexToLeftOut);
			
			List<DnaSequence> motifs = new ArrayList<>();
			motifs.addAll(bestMotifs);
			motifs.remove(motifToLeftOut);
			ProfileMatrix profileMatrix = ProfileMatrix.buildWithPseudoCounts(motifs);
			DnaSequence newMotif = profileRandomKmer(profileMatrix, sequenceToLeftOut, k);
			motifs.add(indexToLeftOut, newMotif);
			
			int score = Motifs.score(motifs);
			if(score < bestScore) {
				bestScore = score;
				bestMotifs = motifs;
			}
		}
		
		return bestMotifs;
	}
	
	private List<DnaSequence> randomKmers(List<DnaSequence> sequences, int k) {
		return sequences.stream().map(sequence -> randomKmer(sequence, k)).collect(Collectors.toList());
	}
	
	private DnaSequence randomKmer(DnaSequence sequence, int k) {
		return sequence.subSequence(random.nextInt(sequence.length()-k+1), k);
	}
	
	private DnaSequence profileRandomKmer(ProfileMatrix profileMatrix, DnaSequence sequence, int k) {
		List<BigDecimal> probabilities = sequence.allSubSequences(k).stream().map(kmer -> profileMatrix.calculateProbability(kmer)).collect(Collectors.toList());
		return sequence.subSequence(new ProbabilityDistribution(probabilities).randomInt(), k);
	}
	
}
