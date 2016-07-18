package kits.bioinfo.ucsandiego.course3;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import kits.bioinfo.alignment.EditDistanceFinder;
import kits.bioinfo.core.Sequence;

public class Challenge7 {

	/**
	 * Edit Distance Problem: Find the edit distance between two strings.
     * Input: Two strings.
     * Output: The edit distance between these strings.
	 * Code Challenge: Solve the Edit Distance Problem.
	 */
	@Test
	public void test() throws Exception {
		List<String> lines = Files.readAllLines(Paths.get("input/dataset_248_3.txt"));
		
		int editDistance = EditDistanceFinder.findEditDistance(Sequence.of(lines.get(0)), Sequence.of(lines.get(1)));
		
		System.out.println(editDistance);
		
		Assert.assertEquals(335, editDistance);
	}
	
	@Test
	public void test2() throws Exception {
		List<String> lines = Files.readAllLines(Paths.get("input/dataset_248_3.txt"));
		
		int editDistance = EditDistanceFinder.findEditDistance2(Sequence.of(lines.get(0)), Sequence.of(lines.get(1)));
		
		System.out.println(editDistance);
		
		Assert.assertEquals(335, editDistance);
	}
	
}
