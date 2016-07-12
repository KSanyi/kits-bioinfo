package kits.bioinfo.ucsandiego.course3;

import java.util.List;

import kits.bioinfo.alignment.LongestCommonSubSequenceFinder;
import kits.bioinfo.core.DnaBase;
import kits.bioinfo.core.DnaSequence;
import kits.bioinfo.core.Sequence;
import kits.bioinfo.infrastructure.SequenceReader;

public class Challenge3 {

	/**
	 * CODE CHALLENGE: Use OUTPUTLCS (reproduced below) to solve the Longest
	 * Common Subsequence Problem. Input: Two strings s and t. Output: A longest
	 * common subsequence of s and t. (Note: more than one solution may exist,
	 * in which case you may output any one.)
	 */
	public static void main(String[] args) throws Exception {
		List<DnaSequence> sequences = SequenceReader.readDnaSequencesPerLine("input/dataset_245_5.txt");
		Sequence<DnaBase> result = LongestCommonSubSequenceFinder.findOneSequence(sequences.get(0), sequences.get(1));

		System.out.println(result);
	}

}
