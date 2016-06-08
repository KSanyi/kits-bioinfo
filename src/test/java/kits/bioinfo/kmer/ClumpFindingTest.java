package kits.bioinfo.kmer;

import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;

import java.util.Set;

import kits.bioinfo.clump.ClumpFinder;
import kits.bioinfo.clump.FastClumpFinder;
import kits.bioinfo.core.DnaSequence;

import org.junit.Test;

public class ClumpFindingTest {

	ClumpFinder clumpFinder = new FastClumpFinder();
	
	@Test
	public void clupms() {
		DnaSequence sequence = new DnaSequence("CGGACTCGACAGATGTGAAGAACGACAATGTGAAGACTCGACACGACAGAGTGAAGAGAAGAGGAAACATTGTAA");
		
		assertThat(clumpFinder.findKmersFormingClumps(sequence, 50, 5, 4), contains(new DnaSequence("CGACA"), new DnaSequence("GAAGA")));
	}
	
	@Test
	public void clupmsFast1() {
		DnaSequence sequence = new DnaSequence("ACTTGTACTTCCCTGCT");
		Set<DnaSequence> kmers = clumpFinder.findKmersFormingClumps(sequence, 10, 4, 2);
		assertThat(kmers, contains(new DnaSequence("ACTT")));
	}
	
	@Test
	public void clupmsFast2() {
		DnaSequence sequence = new DnaSequence("ACTGGTACTTGCACTT");
		Set<DnaSequence> kmers = clumpFinder.findKmersFormingClumps(sequence, 10, 4, 2);
		assertThat(kmers, contains(new DnaSequence("ACTT")));
	}
	
	@Test
	public void clupmsFast3() {
		DnaSequence sequence = new DnaSequence("CGCTAAGCGACTTGTACTTCCCTGCT");
		Set<DnaSequence> kmers = clumpFinder.findKmersFormingClumps(sequence, 10, 4, 2);
		assertThat(kmers, contains(new DnaSequence("ACTT")));
	}
	
}
