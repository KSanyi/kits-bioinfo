package kits.bioinfo.ucsandiego.course1;

import java.io.IOException;
import java.util.List;

import kits.bioinfo.core.DnaSequence;
import kits.bioinfo.infrastructure.SequenceReader;
import kits.bioinfo.matcher.ApproximateSubSequenceMatcher;

public class Challenge4 {

	/**
	 * Approximate Pattern Matching Problem: Find all approximate occurrences of
	 * a pattern in a string. Input: Strings Pattern and Text along with an
	 * integer d. Output: All starting positions where Pattern appears as a
	 * substring of Text with at most d mismatches.
	 * 
	 */
	public static void main(String[] args) throws IOException {
		DnaSequence sequence = SequenceReader.readDnaSequenceFromFile("input/dataset_9_4.txt");
		List<Integer> startIndexes = new ApproximateSubSequenceMatcher("AGCAGAGTC", 4).matchStartIndexes(sequence);
		System.out.println(startIndexes);
	}

}
