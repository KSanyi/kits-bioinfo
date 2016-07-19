package kits.bioinfo.matcher;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import kits.bioinfo.core.DnaSequence;

public class SubSequenceMatcherTest {

	@Test
	public void match() {
		Matcher matcher = new SubSequenceMatcher("ACT");
		Assert.assertTrue(matcher.matches(new DnaSequence("ACT")));
		Assert.assertTrue(matcher.matches(new DnaSequence("CTACTG")));
		Assert.assertTrue(matcher.matches(new DnaSequence("CTGACT")));
		Assert.assertTrue(matcher.matches(new DnaSequence("ACTC")));

		Assert.assertFalse(matcher.matches(new DnaSequence("")));
		Assert.assertFalse(matcher.matches(new DnaSequence("AC")));
	}

	@Test
	public void matchCount() {
		Matcher matcher = new SubSequenceMatcher("ACT");
		Assert.assertEquals(1, matcher.matchCount(new DnaSequence("ACT")));
		Assert.assertEquals(1, matcher.matchCount(new DnaSequence("CTACTGC")));
		Assert.assertEquals(2, matcher.matchCount(new DnaSequence("TACTAAACT")));

		Assert.assertEquals(0, matcher.matchCount(new DnaSequence("AC")));
	}

	@Test
	public void matchStartIndex() {
		Matcher matcher = new SubSequenceMatcher("ACT");
		Assert.assertEquals(Arrays.asList(0), matcher.matchStartIndexes(new DnaSequence("ACT")));
		Assert.assertEquals(Arrays.asList(1, 7), matcher.matchStartIndexes(new DnaSequence("TACTTTCACTT")));

		Assert.assertTrue(matcher.matchStartIndexes(new DnaSequence("AC")).isEmpty());
	}

	@Test
	public void matchWithOverlapCount() {
		Matcher matcher = new SubSequenceMatcher("ACTA");
		Assert.assertEquals(2, matcher.matchCount(new DnaSequence("ACTACTA")));
	}

}
