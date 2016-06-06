package kits.bioinfo.assembly;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

public class Graph<T> {

	private final Map<T, Set<T>> adjacencyMap;
	
	public final Set<T> nodes;
	public final Set<Edge<T>> edges;
	
	public Graph(Set<Edge<T>> edges) {
		Map<T, Set<T>> adjacencyMap = new HashMap<>();
		Set<T> allNodes = new HashSet<>();
		for(Edge<T> edge :  edges) {
			adjacencyMap.get(edge.startNode);
			Set<T> adjecentNodes = adjacencyMap.getOrDefault(edge.startNode, new HashSet<>());
			adjecentNodes.add(edge.endNode);
			adjacencyMap.put(edge.startNode, adjecentNodes);
			allNodes.add(edge.startNode);
			allNodes.add(edge.endNode);
		}
		this.nodes = Collections.unmodifiableSet(allNodes);
		this.edges = Collections.unmodifiableSet(edges);
		this.adjacencyMap = Collections.unmodifiableMap(adjacencyMap);
	}
	
	Graph(Map<T, Set<T>> adjacencyMap) {
		this.adjacencyMap = Collections.unmodifiableMap(adjacencyMap);
		
		Set<T> allNodes = new HashSet<>(adjacencyMap.keySet());
		Set<Edge<T>> edges = new HashSet<>();
		for(Entry<T, Set<T>> entry : adjacencyMap.entrySet()){
			T node = entry.getKey();
			entry.getValue().stream().forEach(adjacentNode -> edges.add(new Edge<>(node, adjacentNode)));
			entry.getValue().stream().forEach(adjacentNode -> allNodes.add(adjacentNode));
		}
		this.nodes = Collections.unmodifiableSet(allNodes);
		this.edges = Collections.unmodifiableSet(edges);
	}
	
	public Set<T> adjacentNodes(T node) {
		return new HashSet<>(adjacencyMap.getOrDefault(node, Collections.emptySet()));
	}
	
	public Set<Edge<T>> edgesFrom(T node) {
		return edges.stream().filter(edge -> edge.startNode.equals(node)).collect(Collectors.toSet());
	}
	
	public String printEdges() {
		StringBuilder sb = new StringBuilder();
		for(Entry<T, Set<T>> entry : adjacencyMap.entrySet()){
			T node = entry.getKey();
			entry.getValue().stream().forEach(adjacentNode -> sb.append(node + " -> " + adjacentNode + "\n"));
		}
		return sb.toString();
	}
	
	public String print() {
		StringBuilder sb = new StringBuilder();
		for(Entry<T, Set<T>> entry : adjacencyMap.entrySet()){
			sb.append(entry.getKey() + " -> ");
			sb.append(entry.getValue().stream().map(n -> n.toString()).collect(Collectors.joining(", ")) + "\n");
		}
		return sb.toString();
	}
	
	@Override
	public boolean equals(Object other) {
		if(other == null) return false;
		if(!(other instanceof Graph<?>)) return false;
		return adjacencyMap.equals(((Graph<?>)other).adjacencyMap);
	}
	
	public static class Edge<T> {
		public final T startNode;
		public final T endNode;
		public Edge(T startNode, T endNode){
			this.startNode = startNode;
			this.endNode = endNode;
		}
		@Override
		public String toString(){
			return startNode + " -> " + endNode;
		}
		@Override
		public boolean equals(Object other) {
			if(other == null) return false;
			if(!(other instanceof Edge<?>)) return false;
			Edge<?> otherEdge = (Edge<?>)other;
			return otherEdge.startNode.equals(startNode) && otherEdge.endNode.equals(endNode);
		}
		@Override
		public int hashCode() {
			return startNode.hashCode() * 37 + endNode.hashCode();
		}
	}
	
}
