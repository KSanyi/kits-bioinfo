package kits.bioinfo.genomerearrangement;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class GreedySorterTest {

	@Test
	public void test(){
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
	
}
