package kits.bioinfo.ucsandiego.course3;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Challenge1 {

	public static void main(String[] args) {
		System.out.println(change(19303, Arrays.asList(24, 14, 7, 5, 3, 1)));
	}

	private static final Map<Integer, Integer> cache = new HashMap<>();

	private static Integer change(int value, List<Integer> coins) {
		if (value == 0) {
			return 0;
		}

		if (cache.containsKey(value)) {
			return cache.get(value);
		}

		Integer minChange = null;
		for (int coin : coins) {
			if (value >= coin) {
				Integer change = change(value - coin, coins);
				if (change != null) {
					change = change + 1;
					if (minChange == null || change < minChange) {
						minChange = change;
					}
				}
			}
		}
		cache.put(value, minChange);
		return minChange;
	}

}
