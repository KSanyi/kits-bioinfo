package kits.bioinfo.matcher;

import java.util.Random;

import kits.bioinfo.base.Sequence;
import kits.bioinfo.base.generator.RandomSequenceGenerator;

import org.junit.Assert;
import org.junit.Test;

public class ApproximateSubSequenceMatchersTest {

	private final Random random = new Random();
	
	private final int TEST_RUNS = 1000;
	
	@Test
	public void testIndexBased() {
		RandomSequenceGenerator generator = new RandomSequenceGenerator();
		
		for(int i=0;i<TEST_RUNS;i++){
			int d = random.nextInt(5) + 1;
			int k = random.nextInt(5) + 2;
			int patternLength = k * (d + 1);
			Sequence pattern = generator.generateRandomSequence(patternLength);
			Sequence text = generator.generateRandomSequence(1000);
			Assert.assertEquals(new ApproximateSubSequenceMatcher(pattern, d).matchStartIndexes(text),
					            new IndexBasedApproximateSubSequenceMatcher(text, k, d).matchStartIndexes(pattern));
		}
	}
	
}
