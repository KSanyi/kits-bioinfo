package kits.bioinfo.motif;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.Test;

import kits.bioinfo.core.DnaSequence;

public class MedianStringFinderTest {

    @Test
    public void basic() {
        assertTrue(NaiveMedianStringFinder.findMedianStrings(Set.of(new DnaSequence("AA")), 2).contains(new DnaSequence("AA")));
        assertTrue(NaiveMedianStringFinder.findMedianStrings(Set.of(new DnaSequence("AAC"), new DnaSequence("AAG")), 2).contains(new DnaSequence("AA")));
    }

    @Test
    public void test1() {
        Set<DnaSequence> sequences = Set.of(new DnaSequence("AAATTGACGCAT"), new DnaSequence("GACGACCACGTT"),
                new DnaSequence("CGTCAGCGCCTG"), new DnaSequence("GCTGAGCACCGG"), new DnaSequence("AGTTCGGGACAG"));
        Set<DnaSequence> medianStrings = NaiveMedianStringFinder.findMedianStrings(sequences, 3);
        assertTrue(medianStrings.contains(new DnaSequence("GAC")));
    }

    @Test
    public void test2() {
        Set<DnaSequence> sequences = Set.of(new DnaSequence("CTCGATGAGTAGGAAAGTAGTTTCACTGGGCGAACCACCCCGGCGCTAATCCTAGTGCCC"),
                new DnaSequence("GCAATCCTACCCGAGGCCACATATCAGTAGGAACTAGAACCACCACGGGTGGCTAGTTTC"),
                new DnaSequence("GGTGTTGAACCACGGGGTTAGTTTCATCTATTGTAGGAATCGGCTTCAAATCCTACACAG"));
        Set<DnaSequence> medianStrings = NaiveMedianStringFinder.findMedianStrings(sequences, 7);
        assertTrue(medianStrings.containsAll(Set.of(new DnaSequence("GTAGGAA"), new DnaSequence("AATCCTA"), new DnaSequence("TAGTTTC"), new DnaSequence("GAACCAC"))));
    }

}
