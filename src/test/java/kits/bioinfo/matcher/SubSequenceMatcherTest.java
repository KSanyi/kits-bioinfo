package kits.bioinfo.matcher;

import java.util.Arrays;

import kits.bioinfo.base.Sequence;

import org.junit.Assert;
import org.junit.Test;

public class SubSequenceMatcherTest {

	@Test
	public void match(){
		Matcher matcher = new SubSequenceMatcher("ACT");
		Assert.assertTrue(matcher.matches(new Sequence("ACT")));
		Assert.assertTrue(matcher.matches(new Sequence("CTACTG")));
		Assert.assertTrue(matcher.matches(new Sequence("CTGACT")));
		Assert.assertTrue(matcher.matches(new Sequence("ACTC")));
		
		Assert.assertFalse(matcher.matches(new Sequence("")));
		Assert.assertFalse(matcher.matches(new Sequence("AC")));
	}
	
	@Test
	public void matchCount(){
		Matcher matcher = new SubSequenceMatcher("ACT");
		Assert.assertEquals(1, matcher.matchCount(new Sequence("ACT")));
		Assert.assertEquals(1, matcher.matchCount(new Sequence("CTACTGC")));
		Assert.assertEquals(2, matcher.matchCount(new Sequence("TACTAAACT")));
		
		Assert.assertEquals(0, matcher.matchCount(new Sequence("AC")));
	}
	
	@Test
	public void matchStartIndex(){
		Matcher matcher = new SubSequenceMatcher("ACT");
		Assert.assertEquals(Arrays.asList(0), matcher.matchStartIndexes(new Sequence("ACT")));
		Assert.assertEquals(Arrays.asList(1, 7), matcher.matchStartIndexes(new Sequence("TACTTTCACTT")));
		
		Assert.assertTrue(matcher.matchStartIndexes(new Sequence("AC")).isEmpty());
	}
	
	@Test
	public void matchWithOverlapCount(){
		Matcher matcher = new SubSequenceMatcher("ACTA");
		Assert.assertEquals(2, matcher.matchCount(new Sequence("ACTACTA")));
	}
	
}
