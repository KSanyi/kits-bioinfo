package kits.bioinfo.matcher;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import kits.bioinfo.core.DnaSequence;

public class ApproximateSubSequenceMatcherTest {

	@Test
	public void match() {
		Matcher matcher = new ApproximateSubSequenceMatcher("ACT", 1);
		Assert.assertTrue(matcher.matches(new DnaSequence("ACT")));
		Assert.assertTrue(matcher.matches(new DnaSequence("ACG")));
		Assert.assertTrue(matcher.matches(new DnaSequence("CTGATT")));
		Assert.assertTrue(matcher.matches(new DnaSequence("CCTC")));

		Assert.assertFalse(matcher.matches(new DnaSequence("")));
		Assert.assertFalse(matcher.matches(new DnaSequence("AC")));
		Assert.assertFalse(matcher.matches(new DnaSequence("ATC")));
	}

	@Test
	public void matchCount() {
		Matcher matcher = new ApproximateSubSequenceMatcher("ACT", 1);
		Assert.assertEquals(1, matcher.matchCount(new DnaSequence("ACC")));
		Assert.assertEquals(2, matcher.matchCount(new DnaSequence("ATTACC")));
	}

	@Test
	public void matchCount2() {
		Matcher matcher = new ApproximateSubSequenceMatcher("AAAAA", 2);
		Assert.assertEquals(11, matcher.matchCount(new DnaSequence("AACAAGCTGATAAACATTTAAAGAG")));
	}

	@Test
	public void matchStartIndex() {
		Matcher matcher = new ApproximateSubSequenceMatcher("ACT", 1);
		Assert.assertEquals(Arrays.asList(0), matcher.matchStartIndexes(new DnaSequence("ACT")));
		Assert.assertEquals(Arrays.asList(1, 7), matcher.matchStartIndexes(new DnaSequence("TACTTTCACTT")));

		Assert.assertTrue(matcher.matchStartIndexes(new DnaSequence("AC")).isEmpty());
	}

	@Test
	public void matchWithOverlapCount() {
		Matcher matcher = new ApproximateSubSequenceMatcher("ACTA", 1);
		Assert.assertEquals(2, matcher.matchCount(new DnaSequence("AGTACTA")));
	}

	@Test
	public void matchStartIndex2() {
		DnaSequence sequence = new DnaSequence("CGCCCGAATCCAGAACGCATTCCCATATTTCGGGACCACTGGCCTCCACGGTACGGACGTCAATCAAAT");
		Matcher matcher = new ApproximateSubSequenceMatcher("ATTCTGGA", 3);
		Assert.assertEquals(Arrays.asList(6, 7, 26, 27), matcher.matchStartIndexes(sequence));
	}

}
