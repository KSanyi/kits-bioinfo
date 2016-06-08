package kits.bioinfo.core;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;

public class RibosomeTest {

	@Test
	public void test() {
		RnaSequence rnaSequence = new RnaSequence("AUGGCCAUGGCGCCCAGAACUGAGAUCAAUAGUACCCGUAUUAACGGGUGA");
		
		List<AminoAcid> aminoAcids = new Ribosome().translateToAminoAcidSequence(rnaSequence);
		
		Assert.assertEquals("MAMAPRTEINSTRING", aminoAcids.stream().map(aminoAcid -> aminoAcid.code1.toString()).collect(Collectors.joining()));
	}
	
}
