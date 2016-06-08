package kits.bioinfo.core;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.Assert;
import org.junit.Test;

import kits.bioinfo.core.DnaSequence;
import kits.bioinfo.util.AllPossibleSequenceGenerator;

public class SequenceGeneratorTest {

	@Test
	public void generateAllPossibleSequences() {
		AllPossibleSequenceGenerator generator = new AllPossibleSequenceGenerator();
		Assert.assertEquals(new HashSet<>(Arrays.asList(new DnaSequence("A"),new DnaSequence("C"), new DnaSequence("T"), new DnaSequence("G"))), generator.generateAllPossibleSequences(1));
		Assert.assertEquals(16, generator.generateAllPossibleSequences(2).size());
		Assert.assertEquals(64, generator.generateAllPossibleSequences(3).size());
	}
	
}
