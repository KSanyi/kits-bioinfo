package kits.bioinfo.matcher;

import java.util.Random;

import kits.bioinfo.base.Sequence;
import kits.bioinfo.base.generator.RandomSequenceGenerator;

import org.junit.Assert;
import org.junit.Test;

public class BMSubSequenceMatcherTest {

	private final Random random = new Random();
	
	private final int TEST_RUNS = 1000;
	
	@Test
	public void test() {
		RandomSequenceGenerator generator = new RandomSequenceGenerator();
		
		for(int i=0;i<TEST_RUNS;i++){
			int patternLength = random.nextInt(50);
			Sequence pattern = generator.generateRandomSequence(patternLength);
			Sequence text = generator.generateRandomSequence(1000);
			
			Assert.assertEquals(new SubSequenceMatcher(pattern).matchStartIndexes(text), new BMSubSequenceMatcher(pattern).matchStartIndexes(text));
		}
	}
	
}
