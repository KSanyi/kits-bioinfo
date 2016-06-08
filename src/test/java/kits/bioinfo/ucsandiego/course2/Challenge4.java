package kits.bioinfo.ucsandiego.course2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

import kits.bioinfo.assembly.KmerCompositioner;
import kits.bioinfo.assembly.KmerGraph;
import kits.bioinfo.core.Sequence;
import kits.bioinfo.infrastructure.SequenceReader;

public class Challenge4 {

	/**
	 * CODE CHALLENGE: Solve the Overlap Graph Problem (restated below).
     * Input: A collection Patterns of k-mers.
     * Output: The overlap graph Overlap(Patterns), in the form of an adjacency list. (You may return the edges in any order.)
	 */
	public static void main(String[] args) throws IOException {
		Sequence sequence = SequenceReader.readFromFile("input/dataset_199_6.txt");
		int k = 12;
		List<Sequence> kmers = KmerCompositioner.generateCompositions(sequence, k);
		KmerGraph graph = KmerGraph.buildDeBrujinGraph(kmers);
		Files.write(Paths.get("./output/output_199_6.txt"), Collections.singletonList(graph.toString()));
	}

}
