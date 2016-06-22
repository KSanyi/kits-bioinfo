package kits.bioinfo.ucsandiego.course2.part1;

import java.io.IOException;

import kits.bioinfo.math.UniversalStringFinder;

public class Challenge9 {

	/**
	 * CODE CHALLENGE: Solve the k-Universal Circular String Problem.
     * Input: An integer k.
     * Output: A k-universal circular string.
	 */
	public static void main(String[] args) throws IOException {
		int k = 8;
		System.out.println(UniversalStringFinder.findUniversalCircularString(k));
	}

}
