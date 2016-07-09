package kits.bioinfo.math.graph;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class Graph<T> {

	private final Map<Node<T>, Set<Node<T>>> adjacencyMap;
	
	public final Set<Node<T>> nodes;
	public final List<Edge<T>> edges;
	
	public Graph(List<Edge<T>> edges) {
		Map<Node<T>, Set<Node<T>>> adjacencyMap = new HashMap<>();
		Set<Node<T>> allNodes = new HashSet<>();
		for(Edge<T> edge :  edges) {
			adjacencyMap.get(edge.startNode);
			Set<Node<T>> adjecentNodes = adjacencyMap.getOrDefault(edge.startNode, new HashSet<>());
			adjecentNodes.add(edge.endNode);
			adjacencyMap.put(edge.startNode, adjecentNodes);
			allNodes.add(edge.startNode);
			allNodes.add(edge.endNode);
		}
		this.nodes = Collections.unmodifiableSet(allNodes);
		this.edges = Collections.unmodifiableList(edges);
		this.adjacencyMap = Collections.unmodifiableMap(adjacencyMap);
	}
	
	public Set<Node<T>> adjacentNodes(Node<T> node) {
		return new HashSet<>(adjacencyMap.getOrDefault(node, Collections.emptySet()));
	}
	
	public List<Edge<T>> edgesFrom(Node<T> node) {
		return edges.stream().filter(edge -> edge.startNode.equals(node)).collect(Collectors.toList());
	}
	
	public List<Edge<T>> edgesTo(Node<T> node) {
		return edges.stream().filter(edge -> edge.endNode.equals(node)).collect(Collectors.toList());
	}
	
	public Optional<Node<T>> nodeWithValue(T value){
		return nodes.stream().filter(node -> node.value.equals(value)).findFirst();
	}
	
	public int inDegree(Node<T> node){
		return edgesTo(node).size();
	}
	
	public int outDegree(Node<T> node){
		return edgesFrom(node).size();
	}
	
	public String printEdges() {
		StringBuilder sb = new StringBuilder();
		for(Entry<Node<T>, Set<Node<T>>> entry : adjacencyMap.entrySet()){
			Node<T> node = entry.getKey();
			entry.getValue().stream().forEach(adjacentNode -> sb.append(node + " -> " + adjacentNode + "\n"));
		}
		return sb.toString();
	}
	
	public boolean isEmpty(){
		return nodes.isEmpty();
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(Entry<Node<T>, Set<Node<T>>> entry : adjacencyMap.entrySet()){
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
	
	public static class Node<T> {
		public final T value;
		private Object markup;
		
		public Node(T value){
			this.value = value;
		}
		
		public void setMarkup(Object markup) {
			this.markup = markup;
		}
		
		public Object getMarkup() {
			return markup;
		}
		
		@Override
		public String toString(){
			return value.toString() + (markup != null ? "(" + markup + ")" : "");
		}
		
		@Override
		public boolean equals(Object other) {
			if(other == null) return false;
			if(!(other instanceof Node<?>)) return false;
			Node<?> otherNode = (Node<?>)other;
			return otherNode.value.equals(value);
		}
		
		@Override
		public int hashCode() {
			return value.hashCode();
		}
	}
	
	public static class Edge<T> {
		public final Node<T> startNode;
		public final Node<T> endNode;
		public final Integer weight;
		
		public Edge(Node<T> startNode, Node<T> endNode, Integer weight){
			this.startNode = startNode;
			this.endNode = endNode;
			this.weight = weight;
		}
		
		public Edge(Node<T> startNode, Node<T> endNode){
			this(startNode, endNode, null);
		}
		
		public Edge(T startNodeValue, T endNodeValue, Integer weight){
			this(new Node<>(startNodeValue), new Node<>(endNodeValue), weight);
		}
		
		public Edge(T startNode, T endNode){
			this(startNode, endNode, null);
		}
		
		@Override
		public String toString(){
			return startNode +  " --" + (weight != null ? "(" + weight + ")" : "") + "--> " + endNode;
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
