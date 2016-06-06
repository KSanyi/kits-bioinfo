package kits.bioinfo.assembly;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import kits.bioinfo.assembly.Graph.Edge;
import kits.bioinfo.util.FrequencyMap;

public class EulerianCycleFinder {

	public static <T> List<T> findEulerianCycle(Graph<T> graph) {
		checkGraph(graph);
		
		List<T> cycle = new ArrayList<T>();
		
		Set<Graph.Edge<T>> edgesNotSeen = new HashSet<>(graph.edges);
		T node = edgesNotSeen.iterator().next().startNode;
		cycle.add(node);
		while(!edgesNotSeen.isEmpty()) {
			Set<T> startNodesFromEdgesNotSeen = edgesNotSeen.stream().map(edge -> edge.startNode).collect(Collectors.toSet());
			node = intersection(startNodesFromEdgesNotSeen, new HashSet<>(cycle)).iterator().next(); // the intersection can not bee empty
			cycle = shiftCycle(cycle, node);
			List<T> newCycle = findCycleFromNode(graph, node, edgesNotSeen);
			cycle.addAll(newCycle);
		}
		return cycle;
	}
	
	private static <T> List<T> findCycleFromNode(final Graph<T> graph, T node, Set<Graph.Edge<T>> edgesNotSeen){
		List<T> cycle = new LinkedList<T>();
		Set<Edge<T>> edgesFromNodeNotSeen = intersection(graph.edgesFrom(node), edgesNotSeen);
		while(!edgesFromNodeNotSeen.isEmpty()) {
			Edge<T> edgeToGo = edgesFromNodeNotSeen.iterator().next();
			edgesNotSeen.remove(edgeToGo);
			node = edgeToGo.endNode;
			cycle.add(node);
			edgesFromNodeNotSeen = intersection(graph.edgesFrom(node), edgesNotSeen);
		} 
		return cycle;
	}
	
	private static <T> List<T> shiftCycle(List<T> cycle, T newStartNode) {
		int indexOfNewStartNode = cycle.lastIndexOf(newStartNode);
		List<T> endPart = new ArrayList<T>(cycle.subList(0, indexOfNewStartNode+1));
		List<T> startPart = new ArrayList<T>(cycle.subList(indexOfNewStartNode, cycle.size()-1));
		startPart.addAll(endPart);
		return startPart;
	}
	
	private static <E> Set<E> intersection(Set<E> setA, Set<E> setB) {
		Set<E> interSection = new HashSet<>(setA);
		interSection.retainAll(setB);
		return interSection;
	}
	
	private static <T> void checkGraph(Graph<T> graph){
		if(graph.edges.isEmpty()) throw new IllegalArgumentException("Can not find an Eulerian cycle in a graph without edges");
		
		FrequencyMap<T> inDegreeMap = new FrequencyMap<>();
		FrequencyMap<T> outDegreeMap = new FrequencyMap<>();
		for(Edge<T> edge : graph.edges) {
			outDegreeMap.put(edge.startNode);
			inDegreeMap.put(edge.endNode);
		}
		Set<T> illegalNodes = graph.nodes.stream().filter(node -> inDegreeMap.frequency(node) != outDegreeMap.frequency(node)).collect(Collectors.toSet());
		if(!illegalNodes.isEmpty()){
			throw new IllegalArgumentException("Can not find an Eulerian cycle in a graph where there is a node with indegree <> outdegree as node: " + illegalNodes);
		}
	}
	
}
