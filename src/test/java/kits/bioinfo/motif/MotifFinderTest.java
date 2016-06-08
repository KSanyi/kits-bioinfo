package kits.bioinfo.motif;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static java.util.Arrays.*;
import static org.junit.Assert.*;
import org.junit.Test;

import kits.bioinfo.core.Sequence;

public class MotifFinderTest {

	@Test
	public void test1() {
		Set<Sequence> sequences = new HashSet<>(Arrays.asList(
				new Sequence("ATTTGGC"),
				new Sequence("TGCCTTA"), 
				new Sequence("CGGTATC"), 
				new Sequence("GAAAATT")));
		
		Set<Sequence> motifs = new MotifFinder().findMotifs(sequences, 3, 1);
		
		assertEquals(new HashSet<>(asList(new Sequence("ATA"), new Sequence("ATT"), new Sequence("GTT"), new Sequence("TTT"))), motifs);
	}
	
}
