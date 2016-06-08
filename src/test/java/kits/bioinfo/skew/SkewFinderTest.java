package kits.bioinfo.skew;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import kits.bioinfo.core.Sequence;

public class SkewFinderTest {

	private SkewFinder skewFinder = new SkewFinder();
	
	@Test
	public void calculateSkew() {
		List<Integer> expectedSkewList = Arrays.asList(0, -1, -1, -1, 0, 1, 2, 1, 1, 1, 0, 1, 2, 1, 0, 0, 0, 0, -1, 0, -1, -2);
		Assert.assertEquals(expectedSkewList, skewFinder.calculateSkew(new Sequence("CATGGGCATCGGCCATACGCC")));
	}
	
	@Test
	public void findMinSkew() {
		Assert.assertEquals(Arrays.asList(11, 24), skewFinder.calculateSkewMin(new Sequence("TAAAGACTGCCGAGAGGCCAACACGAGTGCTAGAACGAGGGGCGTAAACGCGGGTCCGAT")));
	}
	
}
