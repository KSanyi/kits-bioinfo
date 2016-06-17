package kits.bioinfo.ucsandiego.course2.part2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import kits.bioinfo.core.Peptid;
import kits.bioinfo.peptidsequencing.CycloPeptidSequencer;

public class Challenge5 {

	/**
	 * CODE CHALLENGE: Solve the Cyclopeptide Scoring Problem.
	 * Cyclopeptide Scoring Problem: Compute the score of a cyclic peptide against a spectrum.
     * Input: An amino acid string Peptide and a collection of integers Spectrum. 
     * Output: The score of Peptide against Spectrum, Score(Peptide, Spectrum).
	 */
	public static void main(String[] args) throws IOException {
		List<String> lines = Files.readAllLines(Paths.get("input/dataset_102_3.txt"));
		Peptid peptid = new Peptid(lines.get(0));
		List<Integer> experimentalSpectrum = Arrays.asList(lines.get(1).split(" ")).stream()
				.map(mass -> Integer.parseInt(mass))
				.collect(Collectors.toList());
		
		System.out.println("score: " + CycloPeptidSequencer.score(peptid, experimentalSpectrum));
	}

}
