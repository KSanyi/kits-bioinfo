package kits.bioinfo.kmer;

import static java.util.Arrays.asList;
import static java.util.Collections.singleton;

import java.util.HashSet;
import java.util.Set;

import kits.bioinfo.clump.FrequentKMerFinder;
import kits.bioinfo.core.DnaSequence;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class FrequentKMerFinderTest {

    @Test
    public void mostFrequent() {
        DnaSequence sequence = new DnaSequence("ACTGCGTCGACTGCGACTG");

        Set<DnaSequence> result = FrequentKMerFinder.findMostFrequentKmers(sequence, 4);
        assertEquals(singleton(new DnaSequence("ACTG")), result);

        result = FrequentKMerFinder.findMostFrequentKmers(sequence, 3);
        assertEquals(new HashSet<DnaSequence>(asList(new DnaSequence("ACT"), new DnaSequence("CTG"))), result);
    }

    @Test
    public void mostFrequentWithDistance() {
        DnaSequence sequence = new DnaSequence("ACGTTGCATGTCGCATGATGCATGAGAGCT");

        Set<DnaSequence> result = FrequentKMerFinder.findMostFrequentKmersWithDistance(sequence, 4, 1);
        assertEquals(new HashSet<>(asList(new DnaSequence("GATG"), new DnaSequence("ATGC"), new DnaSequence("ATGT"))), result);
    }

}
