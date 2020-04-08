package kits.bioinfo.motif;

import static java.util.stream.Collectors.joining;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import kits.bioinfo.core.DnaBase;
import kits.bioinfo.core.DnaSequence;

public class ProfileMatrix {

    private final int k;

    public static ProfileMatrix buildWithPseudoCounts(Collection<DnaSequence> kmers) {
        return build(kmers, true);
    }

    public static ProfileMatrix build(Collection<DnaSequence> kmers) {
        return build(kmers, false);
    }

    private static ProfileMatrix build(Collection<DnaSequence> kmers, Boolean usingPseudoCounts) {
        if (kmers.size() == 0) {
            throw new IllegalArgumentException("Can not build profile matrix without any kmer");
        }

        int k = kmers.iterator().next().length();

        if (!kmers.stream().allMatch(kmer -> kmer.length() == k)) {
            throw new IllegalArgumentException("All kmers must have the same length");
        }

        List<Map<DnaBase, Double>> values = new ArrayList<>();

        for (int index = 0; index < k; index++) {
            Map<DnaBase, Double> distribution = new HashMap<>();
            for (DnaBase base : DnaBase.values()) {
                distribution.put(base, usingPseudoCounts ? 1.0 : 0.0);
            }
            for (DnaSequence kmer : kmers) {
                DnaBase base = kmer.position(index);
                distribution.put(base, distribution.get(base) + 1);
            }
            
            for (DnaBase base : DnaBase.values()) {
                distribution.put(base, distribution.get(base) / kmers.size());
            }
            values.add(distribution);
        }

        return new ProfileMatrix(values);
    }

    private final List<Map<DnaBase, Double>> values;

    private ProfileMatrix(List<Map<DnaBase, Double>> values) {
        this.values = values;
        k = values.size();
    }

    public double calculateProbability(DnaSequence sequence) {
        double product = 1.0;
        for (int index = 0; index < sequence.length(); index++) {
            product = product * values.get(index).get(sequence.position(index));
        }
        return product;
    }

    public DnaSequence findMostProbableKmer(DnaSequence sequence) {
        if (sequence.length() < k) {
            throw new IllegalArgumentException("Sequence is shorter than k");
        }
        DnaSequence mostProbable = sequence.subSequence(0, k);
        double probability = calculateProbability(mostProbable);
        for (int index = 1; index < sequence.length() - k + 1; index++) {
            DnaSequence candidate = sequence.subSequence(index, k);
            double candidateProbability = calculateProbability(candidate);
            if (candidateProbability > probability) {
                probability = candidateProbability;
                mostProbable = candidate;
            }
        }
        return mostProbable;
    }

    @Override
    public String toString() {
        return Stream.of(DnaBase.values())
                .map(base -> values.stream().map(dist -> dist.get(base).toString()).collect(joining("    ")))
                .collect(joining("\n"));
    }

}
