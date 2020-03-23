package kits.bioinfo.alignment;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import kits.bioinfo.core.DnaBase;
import kits.bioinfo.core.DnaSequence;
import kits.bioinfo.core.Sequence;

public class LongestCommonSubSequenceFinderTest {

    @Test
    public void test() {
        DnaSequence sequence1 = new DnaSequence("GCGATC");
        DnaSequence sequence2 = new DnaSequence("CTGACG");
        Sequence<DnaBase> sequence = LongestCommonSubSequenceFinder.findOneSequence(sequence1, sequence2);
        assertEquals(new DnaSequence("CGAC").toSequence(), sequence);
    }

}
