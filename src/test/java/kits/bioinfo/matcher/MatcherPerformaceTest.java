package kits.bioinfo.matcher;

import java.util.Random;

import kits.bioinfo.base.Sequence;
import kits.bioinfo.base.generator.RandomSequenceGenerator;

public class MatcherPerformaceTest {

	public static void main(String[] args) {
		Random random = new Random();
		int patternLength = random.nextInt(50) + 5;
		RandomSequenceGenerator generator = new RandomSequenceGenerator();
		Sequence pattern = generator.generateRandomSequence(patternLength);
		Sequence text = generator.generateRandomSequence(1000000);
		
		System.out.println("Pattern: " + pattern);
		
		runBase(text, pattern);
		runNaive(text, pattern);
		runBoyerMoore(text, pattern);
		runIndexBased(text, pattern);
	}
	
	private static void runBase(Sequence text, Sequence pattern) {
		long start = System.currentTimeMillis();
		new SubSequenceMatcher(pattern).matchCount(text);
		long end = System.currentTimeMillis();
		System.out.println("Base run: " + (end-start) + " millis");
	}
	
	private static void runNaive(Sequence text, Sequence pattern) {
		long start = System.currentTimeMillis();
		new NaiveSubSequenceMatcher(pattern).matchCount(text);
		long end = System.currentTimeMillis();
		System.out.println("Naive run: " + (end-start) + " millis");
	}
	
	private static void runBoyerMoore(Sequence text, Sequence pattern) {
		long start = System.currentTimeMillis();
		new BMSubSequenceMatcher(pattern).matchCount(text);
		long end = System.currentTimeMillis();
		System.out.println("Boyer Moore run: " + (end-start) + " millis");
	}
	
	private static void runIndexBased(Sequence text, Sequence pattern) {
		IndexBasedSubSequenceMatcher indexBasedSubSequenceMatcher = new IndexBasedSubSequenceMatcher(text, 10);
		long start = System.currentTimeMillis();
		indexBasedSubSequenceMatcher.matchCount(text);
		long end = System.currentTimeMillis();
		System.out.println("Index based run: " + (end-start) + " millis");
	}

}
