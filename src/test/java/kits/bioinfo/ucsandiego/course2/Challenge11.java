package kits.bioinfo.ucsandiego.course2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import kits.bioinfo.assembly.KmerCompositioner;
import kits.bioinfo.assembly.KmerGraph;
import kits.bioinfo.assembly.graph.MaxNonBranchingPathFinder;
import kits.bioinfo.core.Sequence;
import kits.bioinfo.infrastructure.SequenceReader;

public class Challenge11 {

	/**
	 * CODE CHALLENGE: Solve the Contig Generation Problem.
	 * Contig Generation Problem: Generate the contigs from a collection of reads (with imperfect coverage).
     * Input: A collection of k-mers Patterns. 
     * Output: All contigs in DeBruijn(Patterns).
	 */
	public static void main(String[] args) throws IOException {
		List<Sequence> edges = SequenceReader.readPerLine("input/dataset_205_5.txt");
		KmerGraph graph = KmerGraph.buildDeBrujinGraph(edges);
		List<List<Sequence>> maxNonBranchingPaths = MaxNonBranchingPathFinder.findMaxNonBranchingPaths(graph);
		List<Sequence> contigs = maxNonBranchingPaths.stream().map(path -> KmerCompositioner.readSequenceFromComposition(path)).collect(Collectors.toList());
		Files.write(Paths.get("./output/output_205_5.txt"), contigs.stream().map(contig -> contig.toString()).collect(Collectors.toList()));
	}

}
