package kits.bioinfo.motif;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import kits.bioinfo.core.DnaSequence;
import kits.bioinfo.matcher.ApproximateSubSequenceMatcher;
import kits.bioinfo.matcher.Matcher;

public class MotifFinder {

    public static Set<DnaSequence> findMotifs(Collection<DnaSequence> sequences, int k, int d) {

        if (sequences.isEmpty()) {
            throw new IllegalArgumentException("Empty sequence list");
        }

        DnaSequence firstSequence = sequences.iterator().next();
        Set<DnaSequence> candidateMotifs = generateCandidateMotifs(firstSequence, k);

        Set<DnaSequence> motifs = new HashSet<>();
        for (DnaSequence motif : candidateMotifs) {
            Matcher matcher = new ApproximateSubSequenceMatcher(motif, d);
            if (sequences.stream().allMatch(s -> matcher.matches(s))) {
                motifs.add(motif);
            }
        }

        return motifs;
    }

    private static Set<DnaSequence> generateCandidateMotifs(DnaSequence firstSequence, int k) {
        Set<DnaSequence> candidateMotifs = new HashSet<>();

        for (int index = 0; index < firstSequence.length() - k + 1; index++) {
            DnaSequence kmer = firstSequence.subSequence(index, k);
            candidateMotifs.addAll(kmer.neighbours(k));
        }

        return candidateMotifs;
    }

}
