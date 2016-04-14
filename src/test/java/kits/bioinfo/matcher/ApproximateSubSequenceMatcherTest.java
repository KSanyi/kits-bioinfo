package kits.bioinfo.matcher;

import java.util.Arrays;

import kits.bioinfo.base.Sequence;

import org.junit.Assert;
import org.junit.Test;

public class ApproximateSubSequenceMatcherTest {

	@Test
	public void match(){
		Matcher matcher = new ApproximateSubSequenceMatcher("ACT", 1);
		Assert.assertTrue(matcher.matches(new Sequence("ACT")));
		Assert.assertTrue(matcher.matches(new Sequence("ACG")));
		Assert.assertTrue(matcher.matches(new Sequence("CTGATT")));
		Assert.assertTrue(matcher.matches(new Sequence("CCTC")));
		
		Assert.assertFalse(matcher.matches(new Sequence("")));
		Assert.assertFalse(matcher.matches(new Sequence("AC")));
		Assert.assertFalse(matcher.matches(new Sequence("ATC")));
	}
	
	@Test
	public void matchCount(){
		Matcher matcher = new ApproximateSubSequenceMatcher("ACT", 1);
		Assert.assertEquals(1, matcher.matchCount(new Sequence("ACC")));
		Assert.assertEquals(2, matcher.matchCount(new Sequence("ATTACC")));
	}
	
	@Test
	public void matchCount2(){
		Matcher matcher = new ApproximateSubSequenceMatcher("AAAAA", 2);
		Assert.assertEquals(11, matcher.matchCount(new Sequence("AACAAGCTGATAAACATTTAAAGAG")));
	}
	
	@Test
	public void matchStartIndex(){
		Matcher matcher = new ApproximateSubSequenceMatcher("ACT", 1);
		Assert.assertEquals(Arrays.asList(0), matcher.matchStartIndexes(new Sequence("ACT")));
		Assert.assertEquals(Arrays.asList(1, 7), matcher.matchStartIndexes(new Sequence("TACTTTCACTT")));
		
		Assert.assertTrue(matcher.matchStartIndexes(new Sequence("AC")).isEmpty());
	}
	
	@Test
	public void matchWithOverlapCount(){
		Matcher matcher = new ApproximateSubSequenceMatcher("ACTA", 1);
		Assert.assertEquals(2, matcher.matchCount(new Sequence("AGTACTA")));
	}
	
	@Test
	public void matchStartIndex2(){
		Sequence sequence = new Sequence("CGCCCGAATCCAGAACGCATTCCCATATTTCGGGACCACTGGCCTCCACGGTACGGACGTCAATCAAAT");
		Matcher matcher = new ApproximateSubSequenceMatcher("ATTCTGGA", 3);
		Assert.assertEquals(Arrays.asList(6, 7, 26, 27), matcher.matchStartIndexes(sequence));
	}
	
}
