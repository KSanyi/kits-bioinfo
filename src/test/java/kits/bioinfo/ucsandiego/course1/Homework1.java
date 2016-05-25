package kits.bioinfo.ucsandiego.course1;

import kits.bioinfo.base.Sequence;
import kits.bioinfo.kmer.FrequentKMerFinder;
import kits.bioinfo.matcher.Matcher;
import kits.bioinfo.matcher.SubSequenceMatcher;

public class Homework1 {

	public static void main(String[] args) {
		question1();
		question2();
		question3();
	}
	
	private static void question1() {
		Sequence sequence = new Sequence("CGCGATACGTTACATACATGATAGACCGCGCGCGATCATATCGCGATTATC");
		String pattern = "CGCG";
		Matcher macher = new SubSequenceMatcher(pattern);
		System.out.println("Pattern " + pattern + "appers in " + sequence + " " + macher.matchCount(sequence) + " times");
	}
	
	private static void question2() {
		Sequence sequence = new Sequence("CGGAGGACTCTAGGTAACGCTTATCAGGTCCATAGGACATTCA");
		System.out.println("Most frequent 3-mer in " + sequence + "\n:" + new FrequentKMerFinder().findMostFrequentKmers(sequence, 3));
	}
	
	private static void question3() {
		Sequence sequence = new Sequence("CCAGATC");
		System.out.println("Reverse complement of " + sequence + " is: " + sequence.reverseComplement());
	}

}
