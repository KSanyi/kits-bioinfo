package kits.bioinfo.ucsandiego.course2.part2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import kits.bioinfo.core.Peptid;
import kits.bioinfo.peptidsequencing.CycloPeptidSequencer;

public class Challenge7 {

	/**
	 * CODE CHALLENGE: Implement ConvolutionCyclopeptideSequencing. Input: An
	 * integer M, an integer N, and a collection of (possibly repeated) integers
	 * Spectrum. Output: A cyclic peptide LeaderPeptide with amino acids taken
	 * only from the top M elements (and ties) of the convolution of Spectrum
	 * that fall between 57 and 200, and where the size of Leaderboard is
	 * restricted to the top N (and ties).
	 */
	public static void main(String[] args) throws IOException {
		List<String> lines = Files.readAllLines(Paths.get("input/dataset_104_7.txt"));
		int aminoAcidCutRank = Integer.parseInt(lines.get(0));
		int peptidCutRank = Integer.parseInt(lines.get(1));
		List<Integer> experimentalSpectrum = Arrays.asList(lines.get(2).split(" ")).stream().map(mass -> Integer.parseInt(mass))
				.collect(Collectors.toList());

		Set<Peptid> peptids = new CycloPeptidSequencer(peptidCutRank, aminoAcidCutRank).sequencePeptids(experimentalSpectrum);

		Set<String> massSequences = peptids.stream()
				.map(peptid -> peptid.stream().map(aminoAcid -> String.valueOf(aminoAcid.mass)).collect(Collectors.joining("-")))
				.collect(Collectors.toSet());
		System.out.println(massSequences);
	}

}
