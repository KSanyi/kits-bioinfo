package kits.bioinfo.ucsandiego.course2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import kits.bioinfo.assembly.KmerCompositioner;
import kits.bioinfo.base.Sequence;
import kits.bioinfo.infrastructure.SequenceReader;

public class Challenge1 {

	/**
	 * CODE CHALLENGE: Solve the String Composition Problem.
     *	Input: An integer k and a string Text.
     *	Output: Compositionk(Text) (the k-mers can be provided in any order).
	 * 
	 */
	public static void main(String[] args) throws IOException {
		Sequence sequence = SequenceReader.readFromFile("input/dataset_197_3.txt");
		List<Sequence> composition = KmerCompositioner.generateCompositions(sequence, 100);
		Files.write(Paths.get("./output/output_197_3.txt"), composition.stream().map(s -> s.toString()).collect(Collectors.toList()));
	}

}
