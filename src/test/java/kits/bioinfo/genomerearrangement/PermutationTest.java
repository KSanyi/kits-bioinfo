package kits.bioinfo.genomerearrangement;

import org.junit.Assert;
import org.junit.Test;


public class PermutationTest {

	@Test
	public void test1(){
		Permutation permutation = Permutation.parse("(+6 -12 -9 +17 +18 -4 +5 -3 +11 +19 +20 +10 +8 +15 -14 -13 +2 +7 -16 -1)");
		Assert.assertEquals(18, permutation.numberOfBreakPoints());
	}
	
}
