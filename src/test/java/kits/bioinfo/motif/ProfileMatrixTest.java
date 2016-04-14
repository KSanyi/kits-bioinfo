package kits.bioinfo.motif;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import kits.bioinfo.base.Sequence;

public class ProfileMatrixTest {

	@Test
	public void probability() {
		Set<Sequence> kmers = new HashSet<>(Arrays.asList(
				new Sequence("ATTT"),
				new Sequence("CGTA")));
		
		ProfileMatrix profileMatrix = ProfileMatrix.build(kmers);
		
		Assert.assertEquals(new BigDecimal("0.125"), profileMatrix.calculateProbability(new Sequence("ATTA")));
	}
	
	@Test
	public void mostProbableSequenceFound() {
		Set<Sequence> kmers = new HashSet<>(Arrays.asList(
				new Sequence("ATTT"),
				new Sequence("CGTA")));
		
		ProfileMatrix profileMatrix = ProfileMatrix.build(kmers);
		
		Assert.assertEquals(new Sequence("ATTA"), profileMatrix.findMostProbableKmer(new Sequence("GCATTACCCG")));
	}
	
}
