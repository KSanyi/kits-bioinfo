package kits.bioinfo.ucsandiego.course2.part1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import kits.bioinfo.assembly.ReadPair;
import kits.bioinfo.assembly.ReadPairAssembler;
import kits.bioinfo.core.DnaSequence;

public class Challenge10 {

	/**
	 * CODE CHALLENGE: Solve the String Reconstruction from Read-Pairs Problem.
     * Input: Integers k and d followed by a collection of paired k-mers PairedReads.
     * Output: A string Text with (k, d)-mer composition equal to PairedReads.
	 */
	public static void main(String[] args) throws IOException {
		int d = 200;
		List<String> lines = Files.readAllLines(Paths.get("input/dataset_204_14.txt"));
		List<ReadPair> readPairs = lines.subList(1, lines.size()).stream()
			.map(line -> new ReadPair(new DnaSequence(line.split("\\|")[0]), new DnaSequence(line.split("\\|")[1]), d)).collect(Collectors.toList());
		DnaSequence sequence = ReadPairAssembler.assembleSequence(readPairs).get();
		Files.write(Paths.get("./output/output_204_14.txt"), Collections.singletonList(sequence.toString()));
	}

}
