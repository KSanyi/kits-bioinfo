package kits.bioinfo.genomerearrangement;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class GreedySorterTest {

	@Test
	public void test1(){
		Permutation permutation = Permutation.parse("(-3 +4 +1 +5 -2)");
		List<Permutation> permutations =  GreedySorter.sort(permutation);
		
		List<Permutation> expectedPermutations = Arrays.asList(
				new Permutation(-1, -4, +3, +5, -2),
				new Permutation(+1, -4, +3, +5, -2),
				new Permutation(+1, +2, -5, -3, +4),
				new Permutation(+1, +2, +3, +5, +4),
				new Permutation(+1, +2, +3, -4, -5),
				new Permutation(+1, +2, +3, +4, -5),
				new Permutation(+1, +2, +3, +4, +5));
		
		Assert.assertEquals(expectedPermutations, permutations);
	}
	
	@Test
	public void test2(){
		Permutation permutation = Permutation.parse("(+20 +7 +10 +9 +11 +13 +18 -8 -6 -14 +2 -4 -16 +15 +1 +17 +12 -5 +3 -19)");
		List<Permutation> permutations =  GreedySorter.sort(permutation);
		
		Assert.assertEquals(24, permutations.size());
	}
	
}
