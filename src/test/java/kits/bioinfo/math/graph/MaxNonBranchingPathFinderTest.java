package kits.bioinfo.math.graph;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import kits.bioinfo.assembly.KmerCompositioner;
import kits.bioinfo.assembly.KmerGraph;
import kits.bioinfo.core.DnaSequence;

public class MaxNonBranchingPathFinderTest {

	@Test
	public void test() {
		List<String> adjacencyStrings = Arrays.asList("0 -> 1", "1 -> 2,3", "2 -> 4", "3 -> 5", "4 -> 5", "5 -> 6");

		Graph<Integer> graph = GraphParser.buildIntGraphFromAdjacencyStrings(adjacencyStrings);

		List<List<Integer>> paths = MaxNonBranchingPathFinder.findMaxNonBranchingPaths(graph);
		List<List<Integer>> expectedPaths = Arrays.asList(Arrays.asList(0, 1), Arrays.asList(1, 2, 4, 5), Arrays.asList(1, 3, 5),
				Arrays.asList(5, 6));

		assertEquals(Set.copyOf(paths), Set.copyOf(expectedPaths));
	}

	@Test
	public void test2() {
		List<DnaSequence> edgeSequences = Arrays.asList("ATG", "ATG", "TGT", "TGG", "CAT", "GGA", "GAT", "AGA").stream().map(s -> new DnaSequence(s))
				.collect(toList());

		KmerGraph graph = KmerGraph.buildDeBrujinGraph(edgeSequences);
		List<List<DnaSequence>> maxNonBranchingPaths = MaxNonBranchingPathFinder.findMaxNonBranchingPaths(graph);
		List<DnaSequence> contigs = maxNonBranchingPaths.stream().map(contig -> KmerCompositioner.readSequenceFromComposition(contig))
				.collect(toList());
		List<DnaSequence> expectedContigs = Arrays.asList("AGA", "ATG", "ATG", "CAT", "GAT", "TGGA", "TGT").stream().map(s -> new DnaSequence(s))
				.collect(toList());

		assertEquals(Set.copyOf(contigs), Set.copyOf(expectedContigs));
	}

}
