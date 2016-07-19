package kits.bioinfo.ucsandiego.course1;

import java.io.IOException;
import java.util.Set;

import kits.bioinfo.clump.FastClumpFinder;
import kits.bioinfo.core.DnaSequence;
import kits.bioinfo.infrastructure.SequenceReader;

public class Challenge2 {

	/**
	 * How many different 9-mers form (500,3)-clumps in the E. coli genome? (In
	 * other words, do not count a 9-mer more than once.)
	 * 
	 */
	public static void main(String[] args) throws IOException {
		DnaSequence sequence = SequenceReader.readDnaSequenceFromFile("input/E-coli.txt");
		Set<DnaSequence> kmersFormingClump = new FastClumpFinder().findKmersFormingClumps(sequence, 500, 9, 3);
		// Expected size: 1904
		System.out.println(kmersFormingClump);
	}

}
