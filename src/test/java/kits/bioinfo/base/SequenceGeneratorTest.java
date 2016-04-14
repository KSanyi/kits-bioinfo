package kits.bioinfo.base;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.Assert;
import org.junit.Test;

import kits.bioinfo.base.generator.AllPossibleSequenceGenerator;

public class SequenceGeneratorTest {

	@Test
	public void generateAllPossibleSequences() {
		AllPossibleSequenceGenerator generator = new AllPossibleSequenceGenerator();
		Assert.assertEquals(new HashSet<>(Arrays.asList(new Sequence("A"),new Sequence("C"), new Sequence("T"), new Sequence("G"))), generator.generateAllPossibleSequences(1));
		Assert.assertEquals(16, generator.generateAllPossibleSequences(2).size());
		Assert.assertEquals(64, generator.generateAllPossibleSequences(3).size());
	}
	
}
