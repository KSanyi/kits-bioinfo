package kits.bioinfo.johnshopkinscourse;

import java.util.Random;

import kits.bioinfo.base.Sequence;
import kits.bioinfo.base.generator.RandomSequenceGenerator;
import kits.bioinfo.matcher.BMSubSequenceMatcher;
import kits.bioinfo.matcher.NaiveSubSequenceMatcher;

public class Test2 {

	public static void main(String[] args) {
		Random random = new Random();
		RandomSequenceGenerator generator = new RandomSequenceGenerator();
		int patternLength = random.nextInt(50)+1;
		Sequence pattern = generator.generateRandomSequence(patternLength);
		Sequence text = generator.generateRandomSequence(1000);
		System.out.println("Pattern: " + pattern);
		System.out.println("Test: " + text);
		new NaiveSubSequenceMatcher(pattern).matchStartIndexes(text);
		new BMSubSequenceMatcher(pattern).matchStartIndexes(text);
	}
	
}
