package kits.bioinfo.motif;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import kits.bioinfo.core.DnaSequence;

public class GreedyMotifFinder {

    public static Set<DnaSequence> findMotifs(List<DnaSequence> sequences, int k) {

        if (sequences.isEmpty()) {
            throw new IllegalArgumentException("Can not run without sequences");
        }

        Set<DnaSequence> bestMotifs = Set.of();
        int bestScore = Integer.MAX_VALUE;

        DnaSequence firstSequence = sequences.iterator().next();
        for (int index = 0; index < firstSequence.length() - k + 1; index++) {
            Set<DnaSequence> motifs = new HashSet<>();
            motifs.add(firstSequence.subSequence(index, k));
            ProfileMatrix profileMatrix = ProfileMatrix.buildWithPseudoCounts(motifs);
            for (int i = 1; i < sequences.size(); i++) {
                motifs.add(profileMatrix.findMostProbableKmer(sequences.get(i)));
                profileMatrix = ProfileMatrix.buildWithPseudoCounts(motifs);
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
