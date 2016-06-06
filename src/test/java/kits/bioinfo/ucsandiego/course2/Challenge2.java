package kits.bioinfo.ucsandiego.course2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

import kits.bioinfo.assembly.KmerCompositioner;
import kits.bioinfo.base.Sequence;
import kits.bioinfo.infrastructure.SequenceReader;

public class Challenge2 {

	/**
	 * CODE CHALLENGE: Solve the String Spelled by a Genome Path Problem.
	 */
	public static void main(String[] args) throws IOException {
		List<Sequence> composition = SequenceReader.readPerLine("input/dataset_198_3.txt");
		Sequence sequence = KmerCompositioner.readSequenceFromComposition(composition);
		Files.write(Paths.get("./output/output_198_3.txt"), Collections.singletonList(sequence.toString()));
	}

}
