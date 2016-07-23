package kits.bioinfo.ucsandiego.course3.part2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

/*
 * Code Challenge: Implement GreedySorting.
 *    Input: A permutation P.
 *    Output: The sequence of permutations corresponding to applying GreedySorting to P,
 *    ending with the identity permutation.
 */
public class Challenge1 {

	@Test
	public void test() {
		Permutation permutation = new Permutation(Arrays.asList(-3, +4, +1, +5, -2));
		System.out.println(permutation);
		for(int i=0;i<permutation.size();i++){
			int k = i+1;
			if(!permutation.isKSorted(k)){
				permutation.doReversal(k-1, permutation.indexOf(k)+1);
			}
			if(permutation.isKReversed(k)){
				permutation.doReversal(k-1, k+1);
			}
			System.out.println(permutation);
		}
	}

}

class Permutation {

	private final List<Integer> values;

	public Permutation(List<Integer> values) {
		this.values = new ArrayList<>(values);
	}

	public int size() {
		return values.size();
	}

	@Override
	public String toString() {
		return values.stream()
				.map(v -> v > 0 ? "+" + v.toString() : v.toString())
				.collect(Collectors.joining(" ", "(", ")"));
	}

	public void doReversal(int breakPoint1, int breakPoint2) {
		List<Integer> part1 = new ArrayList<>(values.subList(0, breakPoint1));
		List<Integer> part2 = new ArrayList<>(values.subList(breakPoint1, breakPoint2));
		List<Integer> part3 = new ArrayList<>(values.subList(breakPoint2, values.size()));
		values.clear();
		Collections.reverse(part2);
		values.addAll(part1);
		values.addAll(part2);
		values.addAll(part3);
	}
	
	public boolean isSorted(){
		for(int i=0;i<size();i++){
			if(values.get(i) != i+1){
				return false;
			}
		}
		return true;
	}
	
	public boolean isKSorted(int k){
		return values.get(k) == k+1;
	}
	
	public boolean isKReversed(int k){
		return values.get(k) == -(k+1);
	}
	
	public int indexOf(int k){
		int index = values.indexOf(k);
		if(index > -1){
			return index;
		} else {
			return values.indexOf(-k);
		}
	}

}
