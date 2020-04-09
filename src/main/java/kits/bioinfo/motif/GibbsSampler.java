package kits.bioinfo.motif;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import kits.bioinfo.core.DnaSequence;
import kits.bioinfo.math.ProbabilityDistribution;

public class GibbsSampler {

    private Random random = new Random();

    final int runs;

    public GibbsSampler(int runs) {
        this.runs = runs;
    }

    public Set<DnaSequence> findMotifs(List<DnaSequence> sequences, int k, int n) {
        Set<DnaSequence> bestMotifs = Set.of();
        int bestScore = Integer.MAX_VALUE;
        for (int i = 0; i < runs; i++) {
            Set<DnaSequence> motifs = runFindMotifsOnce(sequences, k, n);
            int score = Motifs.score(motifs);
            System.out.println("Score: " + score);
            if (score < bestScore) {
                bestScore = score;
                bestMotifs = motifs;
                if(score == 0) {
                    return bestMotifs;
                }
            }
        }
        return bestMotifs;
    }

    private Set<DnaSequence> runFindMotifsOnce(List<DnaSequence> sequences, int k, int n) {

        if (sequences.isEmpty()) {
            throw new IllegalArgumentException("Can not run without sequences");
        }

        List<DnaSequence> bestMotifs = randomKmers(sequences, k);
        int bestScore = Motifs.score(bestMotifs);

        for (int i = 0; i < n; i++) {
            int indexToLeftOut = random.nextInt(sequences.size());
            DnaSequence motifToLeftOut = bestMotifs.get(indexToLeftOut);
            DnaSequence sequenceToLeftOut = sequences.get(indexToLeftOut);

            List<DnaSequence> motifs = new ArrayList<>(bestMotifs);
            motifs.remove(motifToLeftOut);
            ProfileMatrix profileMatrix = ProfileMatrix.buildWithPseudoCounts(motifs);
            DnaSequence newMotif = profileRandomKmer(profileMatrix, sequenceToLeftOut, k);
            motifs.add(indexToLeftOut, newMotif);

            int score = Motifs.score(motifs);
            if (score < bestScore) {
                bestScore = score;
                bestMotifs = motifs;
            }
        }

        return Set.copyOf(bestMotifs);
    }

    private List<DnaSequence> randomKmers(List<DnaSequence> sequences, int k) {
        return sequences.stream().map(sequence -> randomKmer(sequence, k)).collect(toList());
    }

    private DnaSequence randomKmer(DnaSequence sequence, int k) {
        return sequence.subSequence(random.nextInt(sequence.length() - k + 1), k);
    }

    private static DnaSequence profileRandomKmer(ProfileMatrix profileMatrix, DnaSequence sequence, int k) {
        List<Double> probabilities = sequence.allSubSequences(k).stream()
                .map(kmer -> profileMatrix.calculateProbability(kmer))
                .collect(toList());
        return sequence.subSequence(new ProbabilityDistribution(probabilities).randomInt(), k);
    }

}
