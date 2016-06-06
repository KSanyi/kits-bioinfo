package kits.bioinfo.assembly;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class EulerianCycleFinderTest {

	@Test
	public void test() {
		
		List<String> adjacencyStrings = Arrays.asList(
				"0 -> 3",
				"1 -> 0",
				"2 -> 1,6",
				"3 -> 2",
				"4 -> 2",
				"5 -> 4",
				"6 -> 5,8",
				"7 -> 9",
				"8 -> 7",
				"9 -> 6");
		
		Graph<Integer> graph = GraphBuilder.buildIntGraphFromAdjacencyStrings(adjacencyStrings);
		
		Assert.assertEquals(graph, GraphBuilder.buildIntGraphFromAdjacencyStrings(Arrays.asList(graph.print().split("\n"))));
		
		List<Integer> eulerianPath = EulerianCycleFinder.findEulerianCycle(graph);
		
		Assert.assertEquals(Arrays.asList(6, 5, 4, 2, 1, 0, 3, 2, 6, 8, 7, 9, 6), eulerianPath);
	}
	
}
