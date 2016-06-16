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

public class Challenge4 {

	/**
	 * CODE CHALLENGE: Implement CYCLOPEPTIDESEQUENCING 
	 */
	public static void main(String[] args) throws IOException {
		List<String> lines = Files.readAllLines(Paths.get("input/dataset_100_5.txt"));
		List<Integer> massSpectrum = Arrays.asList(lines.get(0).split(" ")).stream()
				.map(mass -> Integer.parseInt(mass))
				.collect(Collectors.toList());
		List<Peptid> peptids = CycloPeptidSequencer.sequencePeptids(massSpectrum);
		
		Set<String> massSequences = peptids.stream()
			.map(peptid -> peptid.aminoAcids.stream()
					.map(aminoAcid -> String.valueOf(aminoAcid.mass)).collect(Collectors.joining("-")))
					.collect(Collectors.toSet());
		Files.write(Paths.get("./output/output_100_5.txt"), String.join(" ", massSequences).getBytes());
	}

}
