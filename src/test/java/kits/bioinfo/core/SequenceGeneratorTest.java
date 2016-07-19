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
		Assert.assertEquals(new HashSet<>(Arrays.asList(new DnaSequence("A"), new DnaSequence("C"), new DnaSequence("T"), new DnaSequence("G"))),
				AllPossibleSequenceGenerator.generateAllPossibleSequences(1));
		Assert.assertEquals(16, AllPossibleSequenceGenerator.generateAllPossibleSequences(2).size());
		Assert.assertEquals(64, AllPossibleSequenceGenerator.generateAllPossibleSequences(3).size());
	}

}
