package kits.bioinfo.assembly.graph;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import kits.bioinfo.assembly.graph.Graph.Edge;
import kits.bioinfo.util.FrequencyMap;

public class MaxNonBranchingPathFinder {

	public static <T> List<List<T>> findMaxNonBranchingPaths(Graph<T> graph) {
		
		FrequencyMap<T> inDegreeMap = new FrequencyMap<>();
		FrequencyMap<T> outDegreeMap = new FrequencyMap<>();
		for(Edge<T> edge : graph.edges) {
			outDegreeMap.put(edge.startNode);
			inDegreeMap.put(edge.endNode);
		}
		
		List<List<T>> paths = new LinkedList<>();
		
		for(T node : graph.nodes) {
			if(inDegreeMap.frequency(node) !=  1 || outDegreeMap.frequency(node) != 1){
				for(Edge<T> edge : graph.edgesFrom(node)) {
					List<T> path = new LinkedList<>();
					path.add(node);
					path.add(edge.endNode);
					paths.add(finishPath(graph, inDegreeMap, outDegreeMap, path));
				}
			}
		}
		
		return paths;
	}
	
	private static <T> List<T> finishPath(Graph<T> graph, FrequencyMap<T> inDegreeMap, FrequencyMap<T> outDegreeMap, List<T> path){
		T node = path.get(path.size()-1);
		while(inDegreeMap.frequency(node) == 1 && outDegreeMap.frequency(node) == 1){
			node = graph.adjacentNodes(node).iterator().next();
			path.add(node);
		}
		
		return Collections.unmodifiableList(path);
	}
	
}
