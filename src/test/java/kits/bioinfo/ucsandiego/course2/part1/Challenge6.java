package kits.bioinfo.ucsandiego.course2.part1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import kits.bioinfo.assembly.GraphParser;
import kits.bioinfo.assembly.graph.EulerianCycleFinder;
import kits.bioinfo.assembly.graph.Graph;
import kits.bioinfo.assembly.graph.Graph.Edge;

public class Challenge6 {

	/**
	 * CODE CHALLENGE: Solve the Eulerian Cycle Problem.
     * Input: The adjacency list of an Eulerian directed graph.
     * Output: An Eulerian cycle in this graph.
	 */
	public static void main(String[] args) throws IOException {
		Graph<Integer> graph = GraphParser.buildIntGraphFromAdjacencyStrings(Files.readAllLines(Paths.get("input/dataset_203_2.txt")));
		List<Integer> cycle = EulerianCycleFinder.findEulerianCycle(graph);
		String cycleString = cycle.stream().map(i -> i.toString()).collect(Collectors.joining("->"));
		
		// check
		List<Integer> nodes = Arrays.asList(cycleString.split("->")).stream().map(s -> Integer.valueOf(s)).collect(Collectors.toList());
		Set<Edge<Integer>> set = new HashSet<>();
		
		for(int i=0;i<nodes.size()-1;i++) {
			Graph.Edge<Integer> edge = new Graph.Edge<>(nodes.get(i),nodes.get(i+1));
			if(!graph.edges.contains(edge)) {
				System.out.println(edge);
			};
			if(set.contains(edge)){
				System.out.println(edge);
			}
			set.add(edge);
		}
		
		Files.write(Paths.get("./output/output_203_2.txt"), Collections.singletonList(cycleString));
	}

}
