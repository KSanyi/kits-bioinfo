package kits.bioinfo.clump;

import static java.util.Comparator.naturalOrder;
import static java.util.stream.Collectors.toSet;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import kits.bioinfo.core.DnaSequence;

class KmerFrequencyMap {

    private Map<DnaSequence, Integer> map = new HashMap<>();

    public Set<DnaSequence> kmersAppersAtLeast(int t) {
        return map.keySet().stream()
                .filter(sequence -> map.get(sequence) >= t)
                .collect(toSet());
    }

    public Set<DnaSequence> sequences() {
        return map.keySet();
    }

    public int frequency(DnaSequence sequence) {
        return map.getOrDefault(sequence, 0);
    }

    public void put(DnaSequence kmer) {
        put(kmer, 1);
    }

    public void put(DnaSequence kmer, int frequency) {
        int newFrequency = map.getOrDefault(kmer, 0) + frequency;
        map.put(kmer, newFrequency);
    }

    public void remove(DnaSequence kmer) {
        int frequency = map.get(kmer);
        map.put(kmer, frequency - 1);
    }

    public Set<DnaSequence> mostFrequentKmers() {
        final int max = map.values().stream().max(naturalOrder()).orElse(-1);
        return map.keySet().stream()
                .filter(sequence -> map.get(sequence) == max)
                .collect(toSet());
    }

    @Override
    public String toString() {
        return map.toString();
    }

}
