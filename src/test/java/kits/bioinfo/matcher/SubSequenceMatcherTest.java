package kits.bioinfo.matcher;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import kits.bioinfo.core.DnaSequence;

public class SubSequenceMatcherTest {

    @Test
    public void match() {
        Matcher matcher = new SubSequenceMatcher("ACT");
        assertTrue(matcher.matches(new DnaSequence("ACT")));
        assertTrue(matcher.matches(new DnaSequence("CTACTG")));
        assertTrue(matcher.matches(new DnaSequence("CTGACT")));
        assertTrue(matcher.matches(new DnaSequence("ACTC")));

        assertFalse(matcher.matches(new DnaSequence("")));
        assertFalse(matcher.matches(new DnaSequence("AC")));
    }

    @Test
    public void matchCount() {
        Matcher matcher = new SubSequenceMatcher("ACT");
        assertEquals(1, matcher.matchCount(new DnaSequence("ACT")));
        assertEquals(1, matcher.matchCount(new DnaSequence("CTACTGC")));
        assertEquals(2, matcher.matchCount(new DnaSequence("TACTAAACT")));

        assertEquals(0, matcher.matchCount(new DnaSequence("AC")));
    }

    @Test
    public void matchStartIndex() {
        Matcher matcher = new SubSequenceMatcher("ACT");
        assertEquals(Arrays.asList(0), matcher.matchStartIndexes(new DnaSequence("ACT")));
        assertEquals(Arrays.asList(1, 7), matcher.matchStartIndexes(new DnaSequence("TACTTTCACTT")));

        assertTrue(matcher.matchStartIndexes(new DnaSequence("AC")).isEmpty());
    }

    @Test
    public void matchWithOverlapCount() {
        Matcher matcher = new SubSequenceMatcher("ACTA");
        assertEquals(2, matcher.matchCount(new DnaSequence("ACTACTA")));
    }

}
