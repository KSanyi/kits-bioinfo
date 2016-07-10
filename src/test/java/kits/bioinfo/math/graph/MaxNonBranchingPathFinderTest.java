package kits.bioinfo.math.graph;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;

import kits.bioinfo.TestUtil.EqualsInAnyOrder;
import kits.bioinfo.assembly.KmerCompositioner;
import kits.bioinfo.assembly.KmerGraph;
import kits.bioinfo.core.DnaSequence;

public class MaxNonBranchingPathFinderTest {

	@Test
	public void test() {
		List<String> adjacencyStrings = Arrays.asList(
				"0 -> 1",
				"1 -> 2,3",
				"2 -> 4",
				"3 -> 5",
				"4 -> 5",
				"5 -> 6");
		
		Graph<Integer> graph = GraphParser.buildIntGraphFromAdjacencyStrings(adjacencyStrings);
		
		List<List<Integer>> paths = MaxNonBranchingPathFinder.findMaxNonBranchingPathValues(graph);
		List<List<Integer>> expectedPaths = Arrays.asList(
				Arrays.asList(0,1),
				Arrays.asList(1,2,4,5),
				Arrays.asList(1,3,5),
				Arrays.asList(5,6));
		
		Assert.assertThat(paths, new EqualsInAnyOrder<>(expectedPaths));
	}
	
	@Test
	public void test2() {
		List<DnaSequence> edgeSequences = Arrays.asList(
				"ATG", "ATG", "TGT", "TGG",
				"CAT", "GGA", "GAT", "AGA").stream().map(s -> new DnaSequence(s)).collect(Collectors.toList());
		
		KmerGraph graph = KmerGraph.buildDeBrujinGraph(edgeSequences);
		List<List<DnaSequence>> maxNonBranchingPaths = MaxNonBranchingPathFinder.findMaxNonBranchingPathValues(graph);
		List<DnaSequence> contigs = maxNonBranchingPaths.stream().map(contig -> KmerCompositioner.readSequenceFromComposition(contig)).collect(Collectors.toList());
		List<DnaSequence> expectedContigs = Arrays.asList(
				"AGA", "ATG", "ATG", "CAT",
				"GAT", "TGGA", "TGT").stream().map(s -> new DnaSequence(s)).collect(Collectors.toList());
		
		Assert.assertThat(contigs, new EqualsInAnyOrder<>(expectedContigs));
	}
	
}
