package kits.bioinfo.assembly;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import kits.bioinfo.assembly.Graph.Edge;
import kits.bioinfo.util.FrequencyMap;

public class EulerianPathFinder {

	public static <T> List<T> findEulerianPath(Graph<T> graph) {
		
		Optional<T> startNode = findStartNode(graph);
		Optional<T> endNode = findEndNode(graph);
		
		if(startNode.isPresent()){
			Set<Edge<T>> edges = new HashSet<>(graph.edges);
			edges.add(new Edge<>(endNode.get(), startNode.get()));
			Graph<T> extendedGraph = new Graph<>(edges);
			
			List<T> cycle = EulerianCycleFinder.findEulerianCycle(extendedGraph);
			List<T> shiftedCycle = shiftCycle(cycle, startNode.get(), endNode.get());
			
			List<T> path = shiftedCycle.subList(0, shiftedCycle.size()-1);
			
			return path;
		} else {
			List<T> cycle = EulerianCycleFinder.findEulerianCycle(graph);
			return cycle;
		}
	}
	
	private static <T> List<T> shiftCycle(List<T> cycle, T newStartNode, T newEndNode) {
		int indexOfNewStartNode = cycle.lastIndexOf(newStartNode);
		for(int i=0;i<cycle.size();i++){
			if(cycle.get(i) == newStartNode && cycle.get((i-1) % cycle.size()) == newEndNode){
				indexOfNewStartNode = i;
			}
		}
		List<T> endPart = new LinkedList<>(cycle.subList(0, indexOfNewStartNode+1));
		List<T> startPart = new LinkedList<>(cycle.subList(indexOfNewStartNode, cycle.size()-1));
		startPart.addAll(endPart);
		return startPart;
	}
	
	private static <T> Optional<T> findStartNode(Graph<T> graph) {
		FrequencyMap<T> inDegreeMap = new FrequencyMap<>();
		FrequencyMap<T> outDegreeMap = new FrequencyMap<>();
		for(Edge<T> edge : graph.edges) {
			outDegreeMap.put(edge.startNode);
			inDegreeMap.put(edge.endNode);
		}
		
		Optional<T> startNode = graph.nodes.stream().filter(node -> inDegreeMap.frequency(node) < outDegreeMap.frequency(node)).findAny();
		return startNode;
	}
	
	private static <T> Optional<T> findEndNode(Graph<T> graph) {
		FrequencyMap<T> inDegreeMap = new FrequencyMap<>();
		FrequencyMap<T> outDegreeMap = new FrequencyMap<>();
		for(Edge<T> edge : graph.edges) {
			outDegreeMap.put(edge.startNode);
			inDegreeMap.put(edge.endNode);
		}
		
		Optional<T> endNode = graph.nodes.stream().filter(node -> inDegreeMap.frequency(node) > outDegreeMap.frequency(node)).findAny();
		return endNode;
	}
	
}
