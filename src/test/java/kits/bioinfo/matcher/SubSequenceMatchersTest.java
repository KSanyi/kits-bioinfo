package kits.bioinfo.matcher;

import java.util.Random;

import kits.bioinfo.core.DnaSequence;
import kits.bioinfo.util.RandomSequenceGenerator;

import org.junit.Assert;
import org.junit.Test;

public class SubSequenceMatchersTest {

	private final Random random = new Random();

	private final int TEST_RUNS = 1000;

	@Test
	public void testBoyerMoore() {
		RandomSequenceGenerator generator = new RandomSequenceGenerator();

		for (int i = 0; i < TEST_RUNS; i++) {
			int patternLength = random.nextInt(50) + 1;
			DnaSequence pattern = generator.generateRandomDnaSequence(patternLength);
			DnaSequence text = generator.generateRandomDnaSequence(1000);

			Assert.assertEquals(new SubSequenceMatcher(pattern).matchStartIndexes(text), new BMSubSequenceMatcher(pattern).matchStartIndexes(text));
		}
	}

	@Test
	public void testIndexBased() {
		RandomSequenceGenerator generator = new RandomSequenceGenerator();

		for (int i = 0; i < TEST_RUNS; i++) {
			int patternLength = random.nextInt(50) + 1;
			DnaSequence pattern = generator.generateRandomDnaSequence(patternLength);
			DnaSequence text = generator.generateRandomDnaSequence(1000);
			int k = patternLength / 2;
			Assert.assertEquals(new SubSequenceMatcher(pattern).matchStartIndexes(text),
					new IndexBasedSubSequenceMatcher(text, k).matchStartIndexes(pattern));
		}
	}

}
