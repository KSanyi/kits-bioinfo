package kits.bioinfo.ucsandiego.course2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

import kits.bioinfo.assembly.EulerianPathFinder;
import kits.bioinfo.assembly.KmerCompositioner;
import kits.bioinfo.assembly.KmerGraph;
import kits.bioinfo.base.Sequence;
import kits.bioinfo.infrastructure.SequenceReader;

public class Challenge8 {

	/**
	 * CODE CHALLENGE: Solve the String Reconstruction Problem.
     * Input: An integer k followed by a list of k-mers Patterns.
     * Output: A string Text with k-mer composition equal to Patterns. (If multiple answers exist, you may
     * return any one.)
	 */
	public static void main(String[] args) throws IOException {
		KmerGraph graph = KmerGraph.buildDeBrujinGraph(SequenceReader.readPerLine("input/dataset_203_6.txt"));
		List<Sequence> path = EulerianPathFinder.findEulerianPath(graph);
		Sequence sequence = KmerCompositioner.readSequenceFromComposition(path);
		Files.write(Paths.get("./output/output_203_6.txt"), Collections.singletonList(sequence.toString()));
	}

}