package kits.bioinfo.assembly;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import kits.bioinfo.base.Sequence;

public class KmerCompositionerTest {

	@Test
	public void compositionGeneration() {
		List<Sequence> composition = KmerCompositioner.generateCompositions(new Sequence("CAATCCAAC"), 5);
		Assert.assertEquals(Arrays.asList(
				new Sequence("CAATC"),
				new Sequence("AATCC"),
				new Sequence("ATCCA"),
				new Sequence("TCCAA"),
				new Sequence("CCAAC")), composition);
	}
	
	@Test
	public void compositionRead() {
		
		List<Sequence> composition = Arrays.asList(
				new Sequence("CAATC"),
				new Sequence("AATCC"),
				new Sequence("ATCCA"),
				new Sequence("TCCAA"),
				new Sequence("CCAAC"));
		
		Sequence sequence = KmerCompositioner.readSequenceFromComposition(composition);
		Assert.assertEquals(new Sequence("CAATCCAAC"), sequence);
	}
	
}
