package kits.bioinfo.assembly;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import kits.bioinfo.assembly.graph.Graph;
import kits.bioinfo.assembly.graph.Graph.Edge;

public class GraphParser {

	public static Graph<Integer> buildIntGraphFromAdjacencyStrings(List<String> adjacencyStrings) {
		List<Edge<Integer>> edges = new LinkedList<>();
		for(String aString : adjacencyStrings) {
			if(aString.contains("->")){
				String[] parts = aString.split("->");
				Integer node = Integer.parseInt(parts[0].trim());
				List<Edge<Integer>> edgesFromNode = Arrays.asList(parts[1].split(",")).stream().map(s -> new Edge<>(node, Integer.parseInt(s.trim()))).collect(Collectors.toList());
				edges.addAll(edgesFromNode);
			}
		}
		return new Graph<>(edges);
	}
	
}
