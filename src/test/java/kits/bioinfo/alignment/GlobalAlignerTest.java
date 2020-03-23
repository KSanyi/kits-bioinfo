package kits.bioinfo.alignment;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import kits.bioinfo.alignment.aligner.GlobalSequenceAligner;
import kits.bioinfo.alignment.aligner.AlignmentResult;
import kits.bioinfo.alignment.scorefunction.ScoreFunction;
import kits.bioinfo.core.Sequence;

public class GlobalAlignerTest {

    GlobalSequenceAligner<Character> aligner = new GlobalSequenceAligner<>(ScoreFunction.basic(3, 1, 2));

    @Test
    public void test0() {
        AlignmentResult<Character> result = aligner.findOneAlignment(Sequence.of(""), Sequence.of(""));
        assertEquals(0, result.score);
        assertEquals("", result.sequence1.toString());
        assertEquals("", result.sequence2.toString());

        result = aligner.findOneAlignment(Sequence.of("A"), Sequence.of(""));
        assertEquals(-2, result.score);
        assertEquals("A", result.sequence1.toString());
        assertEquals("-", result.sequence2.toString());

        result = aligner.findOneAlignment(Sequence.of("A"), Sequence.of("B"));
        assertEquals(-1, result.score);
        assertEquals("A", result.sequence1.toString());
        assertEquals("B", result.sequence2.toString());

        result = aligner.findOneAlignment(Sequence.of("A"), Sequence.of("A"));
        assertEquals(3, result.score);
        assertEquals("A", result.sequence1.toString());
        assertEquals("A", result.sequence2.toString());

        result = aligner.findOneAlignment(Sequence.of("A"), Sequence.of("AB"));
        assertEquals(1, result.score);
        assertEquals("A-", result.sequence1.toString());
        assertEquals("AB", result.sequence2.toString());

        result = aligner.findOneAlignment(Sequence.of("AB"), Sequence.of("BC"));
        assertEquals(-1, result.score);
        assertEquals("AB-", result.sequence1.toString());
        assertEquals("-BC", result.sequence2.toString());
    }

    @Test
    public void test1() {
        AlignmentResult<Character> result = aligner.findOneAlignment(Sequence.of("APPLE"), Sequence.of("APPE"));
        assertEquals("APPLE", result.sequence1.toString());
        assertEquals("APP-E", result.sequence2.toString());

        result = aligner.findOneAlignment(Sequence.of("APPLE"), Sequence.of("CAPPE"));
        assertEquals("-APPLE", result.sequence1.toString());
        assertEquals("CAPP-E", result.sequence2.toString());
    }

}
