package kits.bioinfo.math.graph;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import kits.bioinfo.math.graph.Graph.Edge;
import kits.bioinfo.math.graph.Graph.Node;
import kits.bioinfo.util.FrequencyMap;

public class MaxNonBranchingPathFinder {

	public static <T> List<List<T>> findMaxNonBranchingPathValues(Graph<T> graph) {
		return findMaxNonBranchingPaths(graph).stream()
				.map(path -> path.stream()
						.map(node -> node.value).collect(Collectors.toList()))
				.collect(Collectors.toList());
	}

	public static <T> List<List<Node<T>>> findMaxNonBranchingPaths(Graph<T> graph) {

		FrequencyMap<Node<T>> inDegreeMap = new FrequencyMap<>();
		FrequencyMap<Node<T>> outDegreeMap = new FrequencyMap<>();
		for (Edge<T> edge : graph.edges) {
			outDegreeMap.put(edge.startNode);
			inDegreeMap.put(edge.endNode);
		}

		List<List<Node<T>>> paths = new LinkedList<>();

		for (Node<T> node : graph.nodes) {
			if (inDegreeMap.frequency(node) != 1 || outDegreeMap.frequency(node) != 1) {
				for (Edge<T> edge : graph.edgesFrom(node)) {
					List<Node<T>> path = new LinkedList<>();
					path.add(node);
					path.add(edge.endNode);
					paths.add(finishPath(graph, inDegreeMap, outDegreeMap, path));
				}
			}
		}

		return paths;
	}

	private static <T> List<Node<T>> finishPath(Graph<T> graph, FrequencyMap<Node<T>> inDegreeMap, FrequencyMap<Node<T>> outDegreeMap,
			List<Node<T>> path) {
		Node<T> node = path.get(path.size() - 1);
		while (inDegreeMap.frequency(node) == 1 && outDegreeMap.frequency(node) == 1) {
			node = graph.adjacentNodes(node).iterator().next();
			path.add(node);
		}

		return Collections.unmodifiableList(path);
	}

}
