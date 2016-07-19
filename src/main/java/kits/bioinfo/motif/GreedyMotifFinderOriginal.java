package kits.bioinfo.motif;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import kits.bioinfo.core.DnaSequence;

public class GreedyMotifFinderOriginal {

	public List<DnaSequence> findMotifs(List<DnaSequence> sequences, int k) {

		if (sequences.isEmpty()) {
			throw new IllegalArgumentException("Can not run without sequences");
		}

		List<DnaSequence> bestMotifs = Collections.emptyList();
		int bestScore = Integer.MAX_VALUE;

		DnaSequence firstSequence = sequences.iterator().next();
		for (int index = 0; index < firstSequence.length() - k + 1; index++) {
			List<DnaSequence> motifs = new ArrayList<>();
			motifs.add(firstSequence.subSequence(index, k));
			ProfileMatrix profileMatrix = ProfileMatrix.build(motifs);
			for (int i = 1; i < sequences.size(); i++) {
				motifs.add(profileMatrix.findMostProbableKmer(sequences.get(i)));
				profileMatrix = ProfileMatrix.build(motifs);
			}
			int score = Motifs.score(motifs);
			if (score < bestScore) {
				bestScore = score;
				bestMotifs = motifs;
			}
		}

		return bestMotifs;

	}

}
