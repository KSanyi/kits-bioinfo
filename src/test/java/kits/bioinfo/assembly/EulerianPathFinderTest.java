package kits.bioinfo.assembly;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class EulerianPathFinderTest {

	@Test
	public void test() {
		
		List<String> adjacencyStrings = Arrays.asList(
				"0 -> 2",
				"1 -> 3",
				"2 -> 1",
				"3 -> 0,4",
				"6 -> 3,7",
				"7 -> 8",
				"8 -> 9",
				"9 -> 6");
		
		Graph<Integer> graph = GraphBuilder.buildIntGraphFromAdjacencyStrings(adjacencyStrings);
		
		Assert.assertEquals(graph, GraphBuilder.buildIntGraphFromAdjacencyStrings(Arrays.asList(graph.print().split("\n"))));
		
		List<Integer> eulerianPath = EulerianPathFinder.findEulerianPath(graph);
		
		Assert.assertEquals(Arrays.asList(6, 7, 8, 9, 6, 3, 0, 2, 1, 3, 4), eulerianPath);
	}
	
}
