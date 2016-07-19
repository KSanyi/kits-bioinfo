package kits.bioinfo.alignment;

import org.junit.Assert;
import org.junit.Test;

import kits.bioinfo.alignment.aligner.FittingSequenceAligner;
import kits.bioinfo.alignment.aligner.AlignmentResult;
import kits.bioinfo.alignment.scorefunction.ScoreFunction;
import kits.bioinfo.core.Sequence;

public class FittingAlignerTest {

	FittingSequenceAligner<Character> aligner = new FittingSequenceAligner<>(ScoreFunction.basic(3, 1, 2));

	@Test
	public void test0() {
		AlignmentResult<Character> result = aligner.findOneAlignment(Sequence.of(""), Sequence.of(""));
		Assert.assertEquals(0, result.score);
		Assert.assertEquals("", result.sequence1.toString());
		Assert.assertEquals("", result.sequence2.toString());

		result = aligner.findOneAlignment(Sequence.of("A"), Sequence.of(""));
		Assert.assertEquals(0, result.score);
		Assert.assertEquals("", result.sequence1.toString());
		Assert.assertEquals("", result.sequence2.toString());

		result = aligner.findOneAlignment(Sequence.of("A"), Sequence.of("B"));
		Assert.assertEquals(-1, result.score);
		Assert.assertEquals("A", result.sequence1.toString());
		Assert.assertEquals("B", result.sequence2.toString());

		result = aligner.findOneAlignment(Sequence.of("A"), Sequence.of("A"));
		Assert.assertEquals(3, result.score);
		Assert.assertEquals("A", result.sequence1.toString());
		Assert.assertEquals("A", result.sequence2.toString());

		result = aligner.findOneAlignment(Sequence.of("AB"), Sequence.of("A"));
		Assert.assertEquals(3, result.score);
		Assert.assertEquals("A", result.sequence1.toString());
		Assert.assertEquals("A", result.sequence2.toString());

		result = aligner.findOneAlignment(Sequence.of("AB"), Sequence.of("BC"));
		Assert.assertEquals(1, result.score);
		Assert.assertEquals("B-", result.sequence1.toString());
		Assert.assertEquals("BC", result.sequence2.toString());
	}

	@Test
	public void test1() {
		AlignmentResult<Character> result = aligner.findOneAlignment(Sequence.of("APPLECIDER"), Sequence.of("CIDER"));
		Assert.assertEquals("CIDER", result.sequence1.toString());
		Assert.assertEquals("CIDER", result.sequence2.toString());

		result = aligner.findOneAlignment(Sequence.of("APPLECIDER"), Sequence.of("APPLE"));
		Assert.assertEquals("APPLE", result.sequence1.toString());
		Assert.assertEquals("APPLE", result.sequence2.toString());

		result = aligner.findOneAlignment(Sequence.of("APPLECIDER"), Sequence.of("CIDWIC"));
		Assert.assertEquals("CID-ER", result.sequence1.toString());
		Assert.assertEquals("CIDWIC", result.sequence2.toString());

		result = aligner.findOneAlignment(Sequence.of("APPLECIDER"), Sequence.of("LEID"));
		Assert.assertEquals("LECID", result.sequence1.toString());
		Assert.assertEquals("LE-ID", result.sequence2.toString());
	}

}
