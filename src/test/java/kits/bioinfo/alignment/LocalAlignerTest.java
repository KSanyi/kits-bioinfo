package kits.bioinfo.alignment;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import kits.bioinfo.alignment.aligner.AlignmentResult;
import kits.bioinfo.alignment.aligner.LocalSequenceAligner;
import kits.bioinfo.alignment.scorefunction.ScoreFunction;
import kits.bioinfo.core.Sequence;

public class LocalAlignerTest {

    LocalSequenceAligner<Character> aligner = new LocalSequenceAligner<>(ScoreFunction.basic(3, 1, 2));

    @Test
    public void test0() {
        AlignmentResult<Character> result = aligner.findOneAlignment(Sequence.of(""), Sequence.of(""));
        assertEquals(0, result.score);
        assertEquals("", result.sequence1.toString());
        assertEquals("", result.sequence2.toString());

        result = aligner.findOneAlignment(Sequence.of("A"), Sequence.of(""));
        assertEquals(0, result.score);
        assertEquals("", result.sequence1.toString());
        assertEquals("", result.sequence2.toString());

        result = aligner.findOneAlignment(Sequence.of("A"), Sequence.of("B"));
        assertEquals(0, result.score);
        assertEquals("", result.sequence1.toString());
        assertEquals("", result.sequence2.toString());

        result = aligner.findOneAlignment(Sequence.of("A"), Sequence.of("A"));
        assertEquals(3, result.score);
        assertEquals("A", result.sequence1.toString());
        assertEquals("A", result.sequence2.toString());

        result = aligner.findOneAlignment(Sequence.of("AB"), Sequence.of("A"));
        assertEquals(3, result.score);
        assertEquals("A", result.sequence1.toString());
        assertEquals("A", result.sequence2.toString());

        result = aligner.findOneAlignment(Sequence.of("AB"), Sequence.of("BC"));
        assertEquals(3, result.score);
        assertEquals("B", result.sequence1.toString());
        assertEquals("B", result.sequence2.toString());
    }

    @Test
    public void test1() {
        AlignmentResult<Character> result = aligner.findOneAlignment(Sequence.of("APPLECIDER"), Sequence.of("CIDER"));
        assertEquals("CIDER", result.sequence1.toString());
        assertEquals("CIDER", result.sequence2.toString());

        result = aligner.findOneAlignment(Sequence.of("APPLECIDER"), Sequence.of("APPLE"));
        assertEquals("APPLE", result.sequence1.toString());
        assertEquals("APPLE", result.sequence2.toString());

        result = aligner.findOneAlignment(Sequence.of("APPLECIDER"), Sequence.of("CIDWIC"));
        assertEquals("CID", result.sequence1.toString());
        assertEquals("CID", result.sequence2.toString());

        result = aligner.findOneAlignment(Sequence.of("APPLECIDER"), Sequence.of("LEID"));
        assertEquals("LECID", result.sequence1.toString());
        assertEquals("LE-ID", result.sequence2.toString());
    }

}
