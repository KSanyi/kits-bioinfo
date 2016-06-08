package kits.bioinfo.ucsandiego.course1;

import java.io.IOException;
import java.util.List;

import kits.bioinfo.core.DnaSequence;
import kits.bioinfo.infrastructure.SequenceReader;
import kits.bioinfo.skew.SkewFinder;

public class Challenge3 {

	/**
	 * Minimum Skew Problem: Find a position in a genome where the skew diagram attains a minimum.
     	Input: A DNA string Genome.
     	Output: All integer(s) i minimizing Skewi (Genome) among all values of i (from 0 to |Genome|).
	 * 
	 */
	public static void main(String[] args) throws IOException {
		DnaSequence sequence = SequenceReader.readFromFile("input/dataset_7_6.txt");
		List<Integer> minSkewIndexes = new SkewFinder().calculateSkewMin(sequence);
		//Expected: 69486, 69487, 69489, 69490
		System.out.println(minSkewIndexes);
	}

}
