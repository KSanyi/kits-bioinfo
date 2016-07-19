package kits.bioinfo.ucsandiego.course1;

import java.io.IOException;
import java.util.Set;

import kits.bioinfo.clump.FastClumpFinder;
import kits.bioinfo.core.DnaSequence;
import kits.bioinfo.infrastructure.SequenceReader;

public class Challenge1 {

	/**
	 * CODE CHALLENGE: Solve the Clump Finding Problem (restated below). You
	 * will need to make sure that your algorithm is efficient enough to handle
	 * a large dataset.
	 * 
	 * Clump Finding Problem: Find patterns forming clumps in a string. Input: A
	 * string Genome, and integers k, L, and t. Output: All distinct k-mers
	 * forming (L, t)-clumps in Genome.
	 * 
	 * 
	 */
	public static void main(String[] args) throws IOException {
		DnaSequence sequence = SequenceReader.readDnaSequenceFromFile("input/dataset_4_5.txt");
		Set<DnaSequence> kmersFormingClump = new FastClumpFinder().findKmersFormingClumps(sequence, 557, 10, 20);
		// Expected: ACTATGACTT, AGCCCGACAC, CAAAGATCGG, CACAAAATTC, CAGATATCCA,
		// CTCGGCCTTT, GTACTGCGAA, TACAAACGCC, TTCATCTGAA, TTGGCAAACC
		System.out.println(kmersFormingClump);
	}

}
