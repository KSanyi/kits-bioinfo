package kits.bioinfo.ucsandiego.course3.part1.challenge;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Change Problem:
 *   Find the minimum number of coins needed to make change.
 *   Input: An integer money and an array COINS of d positive integers.
 *   Output: The minimum number of coins with denominations COINS that changes money.
 */
public class Challenge1_2 {

    public static void main(String[] args) {
        System.out.println(change(19303, List.of(24, 14, 7, 5, 3, 1)));
    }

    // Dynamic programming solution
    private static Integer change(int value, List<Integer> coins) {

        Map<Integer, Integer> changeMap = new HashMap<>();
        changeMap.put(0, 0);
        
        for(int i=1;i<=value;i++) {
            
            int min = Integer.MAX_VALUE;
            for(int coin : coins) {
                if(i >= coin) {
                    int candidate = 1 + changeMap.get(i - coin);
                    if(candidate < min) {
                        min = candidate;
                    }
                }
            }
            changeMap.put(i, min);
        }
        
        return changeMap.get(value);
    }

}
