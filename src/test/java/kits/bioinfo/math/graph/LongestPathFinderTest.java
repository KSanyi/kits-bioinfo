package kits.bioinfo.math.graph;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class LongestPathFinderTest {

	@Test
	public void test1() {
		List<String> adjacencyStrings = Arrays.asList("0->1:7", "0->2:4", "2->3:2", "1->4:1", "3->4:3");
		Graph<Integer> graph = GraphParser.buildWeightedIntGraphFromAdjacencyStrings(adjacencyStrings);
		Integer sourceNode = 0;
		Integer sinkNode = 4;
		List<Integer> longestPath = LongestPathFinder.findLongestPath(graph, sourceNode, sinkNode);
		assertEquals(9, graph.markupNumber(sinkNode).intValue());
		assertEquals(Arrays.asList(0, 2, 3, 4), longestPath);
	}

	@Test
	public void test2() {
		List<String> adjacencyStrings = Arrays.asList("1->11:35", "13->20:19", "1->18:31", "1->13:38", "13->19:22", "2->18:7", "1->19:34", "6->14:30",
				"2->15:18", "0->3:27", "5->15:24", "6->10:33", "4->7:30", "11->13:4", "7->17:39", "2->14:38", "2->16:12", "8->20:13", "5->19:7",
				"12->20:30", "10->12:27", "1->2:9", "0->7:34", "12->16:31", "5->14:35", "3->18:22", "1->5:23", "3->13:9", "14->16:5", "10->17:38",
				"7->8:36", "1->12:9", "18->20:39", "4->13:39", "15->18:8", "1->3:11", "8->15:2", "1->6:23", "1->7:22", "9->19:36", "6->11:3",
				"6->12:37", "12->18:0", "5->12:5", "0->1:5", "11->18:14", "2->8:26", "7->15:14", "10->20:32", "11->17:30", "2->4:27", "6->13:39",
				"6->9:21", "8->19:20", "3->19:9", "8->10:29", "5->6:31", "3->10:13", "7->18:23", "10->18:29", "9->17:33", "6->16:24", "9->18:22",
				"0->12:17", "8->13:16", "6->7:22", "1->8:18", "6->18:26", "14->17:32", "1->15:19", "4->14:19", "0->16:33", "17->19:3", "5->10:4",
				"5->8:27", "3->15:35", "12->15:6", "8->16:29", "3->7:2", "6->19:18", "2->19:1", "10->11:31", "4->16:38", "9->13:7", "3->9:18",
				"4->6:35", "9->20:35", "1->9:27", "5->9:38", "9->16:31", "17->18:11", "14->15:29");
		Graph<Integer> graph = GraphParser.buildWeightedIntGraphFromAdjacencyStrings(adjacencyStrings);
		Integer sourceNode = 5;
		Integer sinkNode = 20;
		List<Integer> longestPath = LongestPathFinder.findLongestPath(graph, sourceNode, sinkNode);
		assertEquals(229, graph.markupNumber(sinkNode).intValue());
		assertEquals(Arrays.asList(5, 6, 7, 8, 10, 11, 17, 18, 20), longestPath);
	}

}
