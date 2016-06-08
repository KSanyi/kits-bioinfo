package kits.bioinfo.kmer;

import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;

import java.util.Set;

import kits.bioinfo.clump.ClumpFinder;
import kits.bioinfo.clump.FastClumpFinder;
import kits.bioinfo.core.Sequence;

import org.junit.Test;

public class ClumpFindingTest {

	ClumpFinder clumpFinder = new FastClumpFinder();
	
	@Test
	public void clupms() {
		Sequence sequence = new Sequence("CGGACTCGACAGATGTGAAGAACGACAATGTGAAGACTCGACACGACAGAGTGAAGAGAAGAGGAAACATTGTAA");
		
		assertThat(clumpFinder.findKmersFormingClumps(sequence, 50, 5, 4), contains(new Sequence("CGACA"), new Sequence("GAAGA")));
	}
	
	@Test
	public void clupmsFast1() {
		Sequence sequence = new Sequence("ACTTGTACTTCCCTGCT");
		Set<Sequence> kmers = clumpFinder.findKmersFormingClumps(sequence, 10, 4, 2);
		assertThat(kmers, contains(new Sequence("ACTT")));
	}
	
	@Test
	public void clupmsFast2() {
		Sequence sequence = new Sequence("ACTGGTACTTGCACTT");
		Set<Sequence> kmers = clumpFinder.findKmersFormingClumps(sequence, 10, 4, 2);
		assertThat(kmers, contains(new Sequence("ACTT")));
	}
	
	@Test
	public void clupmsFast3() {
		Sequence sequence = new Sequence("CGCTAAGCGACTTGTACTTCCCTGCT");
		Set<Sequence> kmers = clumpFinder.findKmersFormingClumps(sequence, 10, 4, 2);
		assertThat(kmers, contains(new Sequence("ACTT")));
	}
	
}
