package kits.bioinfo.ucsandiego.course2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

import kits.bioinfo.assembly.KmerGraph;
import kits.bioinfo.base.Sequence;
import kits.bioinfo.infrastructure.SequenceReader;

public class Challenge3 {

	/**
	 * CODE CHALLENGE: Solve the Overlap Graph Problem (restated below).
     * Input: A collection Patterns of k-mers.
     * Output: The overlap graph Overlap(Patterns), in the form of an adjacency list. (You may return the edges in any order.)
	 */
	public static void main(String[] args) throws IOException {
		List<Sequence> kmers = SequenceReader.readPerLine("input/dataset_198_9.txt");
		KmerGraph graph = KmerGraph.buildFromKmerNodesList(kmers);
		Files.write(Paths.get("./output/output_198_9.txt"), Collections.singletonList(graph.printEdges()));
	}

}
