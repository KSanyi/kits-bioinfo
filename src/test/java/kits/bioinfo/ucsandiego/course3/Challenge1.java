package kits.bioinfo.ucsandiego.course3;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Challenge1 {

	public static void main(String[] args) {
		System.out.println(change(19303, Arrays.asList(24,14,7,5,3,1)));
	}
	
	private static final Map<Integer, Integer> cache = new HashMap<>();
	
	private static int change(int value, List<Integer> coins){
		if(value == 0){
			return 0;
		}
		
		if(cache.containsKey(value)){
			return cache.get(value);
		}
		
		int minChange = Integer.MAX_VALUE;
		for(int coin : coins){
			if(value >= coin){
				int change = change(value - coin, coins) + 1;
				if(change < minChange){
					minChange = change;
				}
			}
		}
		cache.put(value, value);
		return minChange;
	}
	
}
