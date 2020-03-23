package kits.bioinfo.matcher;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import kits.bioinfo.core.DnaSequence;

public class ApproximateSubSequenceMatcherTest {

    @Test
    public void match() {
        Matcher matcher = new ApproximateSubSequenceMatcher("ACT", 1);
        assertTrue(matcher.matches(new DnaSequence("ACT")));
        assertTrue(matcher.matches(new DnaSequence("ACG")));
        assertTrue(matcher.matches(new DnaSequence("CTGATT")));
        assertTrue(matcher.matches(new DnaSequence("CCTC")));

        assertFalse(matcher.matches(new DnaSequence("")));
        assertFalse(matcher.matches(new DnaSequence("AC")));
        assertFalse(matcher.matches(new DnaSequence("ATC")));
    }

    @Test
    public void matchCount() {
        Matcher matcher = new ApproximateSubSequenceMatcher("ACT", 1);
        assertEquals(1, matcher.matchCount(new DnaSequence("ACC")));
        assertEquals(2, matcher.matchCount(new DnaSequence("ATTACC")));
    }

    @Test
    public void matchCount2() {
        Matcher matcher = new ApproximateSubSequenceMatcher("AAAAA", 2);
        assertEquals(11, matcher.matchCount(new DnaSequence("AACAAGCTGATAAACATTTAAAGAG")));
    }

    @Test
    public void matchStartIndex() {
        Matcher matcher = new ApproximateSubSequenceMatcher("ACT", 1);
        assertEquals(Arrays.asList(0), matcher.matchStartIndexes(new DnaSequence("ACT")));
        assertEquals(Arrays.asList(1, 7), matcher.matchStartIndexes(new DnaSequence("TACTTTCACTT")));

        assertTrue(matcher.matchStartIndexes(new DnaSequence("AC")).isEmpty());
    }

    @Test
    public void matchWithOverlapCount() {
        Matcher matcher = new ApproximateSubSequenceMatcher("ACTA", 1);
        assertEquals(2, matcher.matchCount(new DnaSequence("AGTACTA")));
    }

    @Test
    public void matchStartIndex2() {
        DnaSequence sequence = new DnaSequence("CGCCCGAATCCAGAACGCATTCCCATATTTCGGGACCACTGGCCTCCACGGTACGGACGTCAATCAAAT");
        Matcher matcher = new ApproximateSubSequenceMatcher("ATTCTGGA", 3);
        assertEquals(Arrays.asList(6, 7, 26, 27), matcher.matchStartIndexes(sequence));
    }

}
