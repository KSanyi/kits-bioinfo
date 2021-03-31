package kits.bioinfo.ucsandiego.course3.part1.challenge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Change Problem:
 *   Find the minimum number of coins needed to make change.
 *   Input: An integer money and an array COINS of d positive integers.
 *   Output: The list of coins with denominations COINS that changes money with the minimal number of coins.
 */
public class Challenge1_3 {

    public static void main(String[] args) {
        System.out.println(change(19303, List.of(24, 14, 7, 5, 3, 1)));
    }

    private static final Map<Integer, List<Integer>> cache = new HashMap<>();
    
    private static List<Integer> change(int value, List<Integer> coins) {
        if (value == 0) {
            return List.of();
        }

        if (cache.containsKey(value)) {
            return cache.get(value);
        }

        List<Integer> minSelection = null;
        for (int coin : coins) {
            if (value >= coin) {
                List<Integer> selection = change(value - coin, coins);
                if (selection != null) {
                    List<Integer> appendedSelection = append(selection, coin); 
                    if (minSelection == null || appendedSelection.size() < minSelection.size()) {
                        minSelection = appendedSelection;
                    }
                }
            }
        }
        cache.put(value, minSelection);
        return minSelection;
    }
    
    private static List<Integer> append(List<Integer> coins, Integer coin) {
        List<Integer> appended = new ArrayList<>(coins);
        appended.add(coin);
        return List.copyOf(appended);
    }

}
