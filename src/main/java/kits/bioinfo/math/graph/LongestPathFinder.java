package kits.bioinfo.math.graph;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import kits.bioinfo.math.graph.Graph.Edge;
import kits.bioinfo.math.graph.Graph.Node;

public class LongestPathFinder {

	public static <T> List<T> findLongestPathValues(Graph<T> graph, Node<T> sourceNode, Node<T> sinkNode) {
		return findLongestPath(graph, sourceNode, sinkNode).stream().map(node -> node.value).collect(Collectors.toList());
	}
	
	public static <T> List<Node<T>> findLongestPath(Graph<T> graph, Node<T> sourceNode, Node<T> sinkNode) {
		Graph<T> relevantGraph = createGraphWithoutIrrelevantNodes(graph, sourceNode);
		markupGraphNodeWithMaxPaths(relevantGraph);
		return backtrackPath(relevantGraph, sourceNode, sinkNode);
	}
	
	private static <T> void markupGraphNodeWithMaxPaths(Graph<T> graph) {
		List<Node<T>> nodes = listNodesInTopologicalOrder(graph);
		for(Node<T> node : nodes){
			if(graph.edgesTo(node).isEmpty()){
				node.setMarkup(0);	
			} else {
				int max = Integer.MIN_VALUE;
				for(Edge<T> edge : graph.edgesTo(node)){
					int preNodePathValue  = (Integer) edge.startNode.getMarkup();
					int candidate = preNodePathValue + edge.weight;
					if(candidate > max){
						max = candidate;
					}
				}
				node.setMarkup(max);				
			}
		}
	}
	
	private static <T> List<Node<T>> backtrackPath(Graph<T> graph, Node<T> sourceNode, Node<T> sinkNode){
		List<Node<T>> path = new LinkedList<>();
		Node<T> node = sinkNode;
		path.add(node);
		while(node != sourceNode){
			int pathValue = (int)node.getMarkup();
			for(Edge<T> edge : graph.edgesTo(node)){
				Node<T> preNode = edge.startNode;
				int preNodePathValue  = (int)preNode.getMarkup();
				if(preNodePathValue + edge.weight == pathValue){
					node = preNode;
					path.add(node);
					break;
				}
			}
		}
		Collections.reverse(path);
		return path;
	}

	private static <T> List<Node<T>> listNodesInTopologicalOrder(Graph<T> originalGraph) {
		List<Node<T>> nodeList = new LinkedList<>();
		
		Graph<T> graph = originalGraph;
		while(!graph.isEmpty()){
			Node<T> nodeToRemove = findNodeToRemove(graph);
			nodeList.add(nodeToRemove);
			
			List<Edge<T>> edges = new LinkedList<>(graph.edges);
			edges.removeAll(graph.edgesTo(nodeToRemove));
			graph = new Graph<>(edges);
		}
		
		List<Node<T>> remainingNodes = new LinkedList<>(originalGraph.nodes);
		remainingNodes.removeAll(nodeList);
		
		nodeList.addAll(remainingNodes);
		
		Collections.reverse(nodeList);
		
		return nodeList;
	}
	
	private static <T> Node<T> findNodeToRemove(Graph<T> graph){
		return graph.nodes.stream().filter(node -> graph.edgesFrom(node).isEmpty()).findFirst()
		.orElseThrow(() -> new IllegalArgumentException("Graph is not acyclic"));
	}
	
	private static <T> Graph<T> createGraphWithoutIrrelevantNodes(Graph<T> originalGraph, Node<T> sourceNode){
		Graph<T> graph = originalGraph;
		List<Node<T>> irrelevantSources = findIrrelevantSources(originalGraph, sourceNode);
		while(!irrelevantSources.isEmpty()){
			List<Edge<T>> edges = new LinkedList<>(graph.edges);
			for(Node<T> irrelevantSource : irrelevantSources){
				edges.removeAll(graph.edgesFrom(irrelevantSource));	
			}
			graph = new Graph<>(edges);
			irrelevantSources = findIrrelevantSources(graph, sourceNode);
		}
		return graph;
	}
	
	private static <T> List<Node<T>> findIrrelevantSources(Graph<T> graph, Node<T> sourceNode){
		List<Node<T>> sources = graph.nodes.stream().filter(node -> graph.inDegree(node) == 0).collect(Collectors.toList());
		sources.remove(sourceNode);
		return sources;
	}

}
