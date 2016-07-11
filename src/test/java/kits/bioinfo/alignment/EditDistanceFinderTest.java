package kits.bioinfo.alignment;

import org.junit.Assert;
import org.junit.Test;

import kits.bioinfo.core.Sequence;

public class EditDistanceFinderTest {

	@Test
	public void test() {
		int editDistance = EditDistanceFinder.findEditDistance(Sequence.of("PLEASANTLY"), Sequence.of("MEANLY"));
		System.out.println(editDistance);
		
		Assert.assertEquals(5, editDistance);
	}
	
}
