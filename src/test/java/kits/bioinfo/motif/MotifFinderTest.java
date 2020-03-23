package kits.bioinfo.motif;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static java.util.Arrays.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import kits.bioinfo.core.DnaSequence;

public class MotifFinderTest {

	@Test
	public void test1() {
		Set<DnaSequence> sequences = new HashSet<>(
				Arrays.asList(new DnaSequence("ATTTGGC"), new DnaSequence("TGCCTTA"), new DnaSequence("CGGTATC"), new DnaSequence("GAAAATT")));

		Set<DnaSequence> motifs = new MotifFinder().findMotifs(sequences, 3, 1);

		assertEquals(new HashSet<>(asList(new DnaSequence("ATA"), new DnaSequence("ATT"), new DnaSequence("GTT"), new DnaSequence("TTT"))), motifs);
	}

}
