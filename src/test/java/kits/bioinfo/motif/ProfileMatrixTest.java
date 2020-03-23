package kits.bioinfo.motif;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import kits.bioinfo.core.DnaSequence;

public class ProfileMatrixTest {

	@Test
	public void probability() {
		Set<DnaSequence> kmers = new HashSet<>(Arrays.asList(new DnaSequence("ATTT"), new DnaSequence("CGTA")));

		ProfileMatrix profileMatrix = ProfileMatrix.build(kmers);

		assertEquals(new BigDecimal("0.125"), profileMatrix.calculateProbability(new DnaSequence("ATTA")));
	}

	@Test
	public void mostProbableSequenceFound() {
		Set<DnaSequence> kmers = new HashSet<>(Arrays.asList(new DnaSequence("ATTT"), new DnaSequence("CGTA")));

		ProfileMatrix profileMatrix = ProfileMatrix.build(kmers);

		assertEquals(new DnaSequence("ATTA"), profileMatrix.findMostProbableKmer(new DnaSequence("GCATTACCCG")));
	}

}
