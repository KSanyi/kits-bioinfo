package kits.bioinfo.motif;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import kits.bioinfo.core.DnaSequence;

public class MedianStringFinderTest {

    private MedianStringFinder medianStringFinder = new MedianStringFinder();

    @Test
    public void basic() {
        assertTrue(medianStringFinder.findMedianStrings(new HashSet<>(asList(new DnaSequence("AA"))), 2).contains(new DnaSequence("AA")));
        assertTrue(medianStringFinder.findMedianStrings(new HashSet<>(asList(new DnaSequence("AAC"), new DnaSequence("AAG"))), 2).contains(new DnaSequence("AA")));
    }

    @Test
    public void test1() {
        Set<DnaSequence> sequences = new HashSet<>(asList(new DnaSequence("AAATTGACGCAT"), new DnaSequence("GACGACCACGTT"),
                new DnaSequence("CGTCAGCGCCTG"), new DnaSequence("GCTGAGCACCGG"), new DnaSequence("AGTTCGGGACAG")));
        Set<DnaSequence> medianStrings = new MedianStringFinder().findMedianStrings(sequences, 3);
        assertTrue(medianStrings.contains(new DnaSequence("GAC")));
    }

    @Test
    public void test2() {
        Set<DnaSequence> sequences = new HashSet<>(asList(new DnaSequence("CTCGATGAGTAGGAAAGTAGTTTCACTGGGCGAACCACCCCGGCGCTAATCCTAGTGCCC"),
                new DnaSequence("GCAATCCTACCCGAGGCCACATATCAGTAGGAACTAGAACCACCACGGGTGGCTAGTTTC"),
                new DnaSequence("GGTGTTGAACCACGGGGTTAGTTTCATCTATTGTAGGAATCGGCTTCAAATCCTACACAG")));
        Set<DnaSequence> medianStrings = new MedianStringFinder().findMedianStrings(sequences, 7);
        assertTrue(medianStrings.containsAll(Set.of(new DnaSequence("GTAGGAA"), new DnaSequence("AATCCTA"), new DnaSequence("TAGTTTC"), new DnaSequence("GAACCAC"))));
    }

}
