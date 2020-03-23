package kits.bioinfo.kmer;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.Test;

import kits.bioinfo.clump.ClumpFinder;
import kits.bioinfo.clump.FastClumpFinder;
import kits.bioinfo.core.DnaSequence;

public class ClumpFindingTest {

	ClumpFinder clumpFinder = new FastClumpFinder();

	@Test
	public void clupms() {
		DnaSequence sequence = new DnaSequence("CGGACTCGACAGATGTGAAGAACGACAATGTGAAGACTCGACACGACAGAGTGAAGAGAAGAGGAAACATTGTAA");

		assertTrue(clumpFinder.findKmersFormingClumps(sequence, 50, 5, 4).containsAll(Set.of(new DnaSequence("CGACA"), new DnaSequence("GAAGA"))));
	}

	@Test
	public void clupmsFast1() {
		DnaSequence sequence = new DnaSequence("ACTTGTACTTCCCTGCT");
		Set<DnaSequence> kmers = clumpFinder.findKmersFormingClumps(sequence, 10, 4, 2);
		assertTrue(kmers.contains(new DnaSequence("ACTT")));
	}

	@Test
	public void clupmsFast2() {
		DnaSequence sequence = new DnaSequence("ACTGGTACTTGCACTT");
		Set<DnaSequence> kmers = clumpFinder.findKmersFormingClumps(sequence, 10, 4, 2);
		assertTrue(kmers.contains(new DnaSequence("ACTT")));
	}

	@Test
	public void clupmsFast3() {
		DnaSequence sequence = new DnaSequence("CGCTAAGCGACTTGTACTTCCCTGCT");
		Set<DnaSequence> kmers = clumpFinder.findKmersFormingClumps(sequence, 10, 4, 2);
		assertTrue(kmers.contains(new DnaSequence("ACTT")));
	}

}
