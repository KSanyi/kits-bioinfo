package kits.bioinfo.matcher;

import java.util.Random;

import kits.bioinfo.core.DnaSequence;
import kits.bioinfo.util.RandomSequenceGenerator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ApproximateSubSequenceMatchersTest {

	private final Random random = new Random();

	private final int TEST_RUNS = 1000;

	@Test
	public void testIndexBased() {
		RandomSequenceGenerator generator = new RandomSequenceGenerator();

		for (int i = 0; i < TEST_RUNS; i++) {
			int d = random.nextInt(5) + 1;
			int k = random.nextInt(5) + 2;
			int patternLength = k * (d + 1);
			DnaSequence pattern = generator.generateRandomDnaSequence(patternLength);
			DnaSequence text = generator.generateRandomDnaSequence(1000);
			assertEquals(new ApproximateSubSequenceMatcher(pattern, d).matchStartIndexes(text),
					new IndexBasedApproximateSubSequenceMatcher(text, k, d).matchStartIndexes(pattern));
		}
	}

}
