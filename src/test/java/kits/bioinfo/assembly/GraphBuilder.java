package kits.bioinfo.assembly;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class GraphBuilder {

	public static Graph<Integer> buildIntGraphFromAdjacencyStrings(List<String> adjacencyStrings) {
		Map<Integer, Set<Integer>> adjacencyMap = new HashMap<>();
		for(String aString : adjacencyStrings) {
			if(aString.contains("->")){
				String[] parts = aString.split("->");
				Integer node = Integer.parseInt(parts[0].trim());
				Set<Integer> adjacentNodes = new HashSet<>(Arrays.asList(parts[1].split(",")).stream().map(s -> Integer.parseInt(s.trim())).collect(Collectors.toList()));
				adjacencyMap.put(node, adjacentNodes);
			}
		}
		return new Graph<Integer>(adjacencyMap);
	}
	
}
