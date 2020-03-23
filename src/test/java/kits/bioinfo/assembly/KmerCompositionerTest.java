package kits.bioinfo.assembly;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import kits.bioinfo.core.DnaSequence;

public class KmerCompositionerTest {

	@Test
	public void compositionGeneration() {
		List<DnaSequence> composition = KmerCompositioner.generateCompositions(new DnaSequence("CAATCCAAC"), 5);
		assertEquals(Arrays.asList(new DnaSequence("CAATC"), new DnaSequence("AATCC"), new DnaSequence("ATCCA"), new DnaSequence("TCCAA"),
				new DnaSequence("CCAAC")), composition);
	}

	@Test
	public void compositionRead() {

		List<DnaSequence> composition = Arrays.asList(new DnaSequence("CAATC"), new DnaSequence("AATCC"), new DnaSequence("ATCCA"),
				new DnaSequence("TCCAA"), new DnaSequence("CCAAC"));

		DnaSequence sequence = KmerCompositioner.readSequenceFromComposition(composition);
		assertEquals(new DnaSequence("CAATCCAAC"), sequence);
	}

}
