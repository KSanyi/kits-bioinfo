package kits.bioinfo.alignment;

import org.junit.Assert;
import org.junit.Test;

import kits.bioinfo.alignment.aligner.AlignmentResult;
import kits.bioinfo.alignment.aligner.OverlappingSequenceAligner;
import kits.bioinfo.alignment.scorefunction.ScoreFunction;
import kits.bioinfo.core.Sequence;

public class OverlappingAlignerTest {

	OverlappingSequenceAligner<Character> aligner = new OverlappingSequenceAligner<>(ScoreFunction.basic(3, 1, 2));

	@Test
	public void test0() {
		AlignmentResult<Character> result = aligner.findOneAlignment(Sequence.of(""), Sequence.of(""));
		Assert.assertEquals(0, result.score);
		Assert.assertEquals("", result.sequence1.toString());
		Assert.assertEquals("", result.sequence2.toString());

		result = aligner.findOneAlignment(Sequence.of("A"), Sequence.of(""));
		// Assert.assertEquals(0, result.score);
		// Assert.assertEquals("", result.sequence1.toString());
		// Assert.assertEquals("", result.sequence2.toString());

		result = aligner.findOneAlignment(Sequence.of("A"), Sequence.of("B"));
		// Assert.assertEquals(0, result.score);
		// Assert.assertEquals("", result.sequence1.toString());
		// Assert.assertEquals("", result.sequence2.toString());

		result = aligner.findOneAlignment(Sequence.of("A"), Sequence.of("A"));
		Assert.assertEquals(3, result.score);
		Assert.assertEquals("A", result.sequence1.toString());
		Assert.assertEquals("A", result.sequence2.toString());

		result = aligner.findOneAlignment(Sequence.of("AB"), Sequence.of("A"));
		Assert.assertEquals(1, result.score);
		Assert.assertEquals("AB", result.sequence1.toString());
		Assert.assertEquals("A-", result.sequence2.toString());

		result = aligner.findOneAlignment(Sequence.of("AB"), Sequence.of("B"));
		Assert.assertEquals(3, result.score);
		Assert.assertEquals("B", result.sequence1.toString());
		Assert.assertEquals("B", result.sequence2.toString());

		result = aligner.findOneAlignment(Sequence.of("AB"), Sequence.of("BC"));
		Assert.assertEquals(3, result.score);
		Assert.assertEquals("B", result.sequence1.toString());
		Assert.assertEquals("B", result.sequence2.toString());
	}

	@Test
	public void test1() {
		AlignmentResult<Character> result = aligner.findOneAlignment(Sequence.of("APPLECIDER"), Sequence.of("CIDER"));
		Assert.assertEquals("CIDER", result.sequence1.toString());
		Assert.assertEquals("CIDER", result.sequence2.toString());

		result = aligner.findOneAlignment(Sequence.of("APPLECIDER"), Sequence.of("XXDER"));
		Assert.assertEquals("CIDER", result.sequence1.toString());
		Assert.assertEquals("XXDER", result.sequence2.toString());

		result = aligner.findOneAlignment(Sequence.of("APPLECIDER"), Sequence.of("DERICK"));
		Assert.assertEquals("DER", result.sequence1.toString());
		Assert.assertEquals("DER", result.sequence2.toString());

	}

}
