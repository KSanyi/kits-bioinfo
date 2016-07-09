package kits.bioinfo.math.graph;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import kits.bioinfo.math.graph.Graph.Edge;
import kits.bioinfo.math.graph.Graph.Node;
import kits.bioinfo.util.FrequencyMap;

public class EulerianPathFinder {

	public static <T> List<T> findEulerianPathValues(Graph<T> graph) {
		return findEulerianPath(graph).stream().map(node -> node.value).collect(Collectors.toList());
	}
	
	public static <T> List<Node<T>> findEulerianPath(Graph<T> graph) {
		
		checkGraph(graph);
		
		Optional<Node<T>> startNode = findStartNode(graph);
		Optional<Node<T>> endNode = findEndNode(graph);
		
		if(startNode.isPresent()){
			List<Edge<T>> edges = new LinkedList<>(graph.edges);
			edges.add(new Edge<>(endNode.get(), startNode.get()));
			Graph<T> extendedGraph = new Graph<>(edges);
			
			List<Node<T>> cycle = EulerianCycleFinder.findEulerianCycle(extendedGraph);
			List<Node<T>> shiftedCycle = shiftCycle(cycle, startNode.get(), endNode.get());
			
			List<Node<T>> path = shiftedCycle.subList(0, shiftedCycle.size()-1);
			
			return path;
		} else {
			List<Node<T>> cycle = EulerianCycleFinder.findEulerianCycle(graph);
			return cycle;
		}
	}
	
	private static <T> List<Node<T>> shiftCycle(List<Node<T>> cycle, Node<T> newStartNode, Node<T> newEndNode) {
		int indexOfNewStartNode = cycle.lastIndexOf(newStartNode);
		for(int i=0;i<cycle.size();i++){
			if(cycle.get(i) == newStartNode && cycle.get((i+cycle.size()-1) % cycle.size()) == newEndNode){
				indexOfNewStartNode = i;
			}
		}
		List<Node<T>> endPart = new LinkedList<>(cycle.subList(0, indexOfNewStartNode+1));
		List<Node<T>> startPart = new LinkedList<>(cycle.subList(indexOfNewStartNode, cycle.size()-1));
		startPart.addAll(endPart);
		return startPart;
	}
	
	private static <T> Optional<Node<T>> findStartNode(Graph<T> graph) {
		FrequencyMap<Node<T>> inDegreeMap = new FrequencyMap<>();
		FrequencyMap<Node<T>> outDegreeMap = new FrequencyMap<>();
		for(Edge<T> edge : graph.edges) {
			outDegreeMap.put(edge.startNode);
			inDegreeMap.put(edge.endNode);
		}
		
		Optional<Node<T>> startNode = graph.nodes.stream().filter(node -> inDegreeMap.frequency(node) < outDegreeMap.frequency(node)).findAny();
		return startNode;
	}
	
	private static <T> Optional<Node<T>> findEndNode(Graph<T> graph) {
		FrequencyMap<Node<T>> inDegreeMap = new FrequencyMap<>();
		FrequencyMap<Node<T>> outDegreeMap = new FrequencyMap<>();
		for(Edge<T> edge : graph.edges) {
			outDegreeMap.put(edge.startNode);
			inDegreeMap.put(edge.endNode);
		}
		
		Optional<Node<T>> endNode = graph.nodes.stream().filter(node -> inDegreeMap.frequency(node) > outDegreeMap.frequency(node)).findAny();
		return endNode;
	}
	
	private static <T> void checkGraph(Graph<T> graph){
		if(graph.edges.isEmpty()) throw new IllegalArgumentException("Can not find an Eulerian path in a graph without edges");
		
		FrequencyMap<Node<T>> inDegreeMap = new FrequencyMap<>();
		FrequencyMap<Node<T>> outDegreeMap = new FrequencyMap<>();
		for(Edge<T> edge : graph.edges) {
			outDegreeMap.put(edge.startNode);
			inDegreeMap.put(edge.endNode);
		}
		Set<Node<T>> startNodes = graph.nodes.stream().filter(node -> inDegreeMap.frequency(node) < outDegreeMap.frequency(node)).collect(Collectors.toSet());
		if(startNodes.size() > 1){
			throw new IllegalArgumentException("Can not find an Eulerian path in a graph where there is more than one node with indegree < outdegree as nodes: " + startNodes);
		}
		
		Set<Node<T>> endNodes = graph.nodes.stream().filter(node -> inDegreeMap.frequency(node) > outDegreeMap.frequency(node)).collect(Collectors.toSet());
		if(endNodes.size() > 1){
			throw new IllegalArgumentException("Can not find an Eulerian path in a graph where there is more than one node with indegree > outdegree as nodes: " + endNodes);
		}
	}
}
