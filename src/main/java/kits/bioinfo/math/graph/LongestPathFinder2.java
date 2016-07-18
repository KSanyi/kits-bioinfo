package kits.bioinfo.math.graph;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import kits.bioinfo.math.graph.Graph.Edge;
import kits.bioinfo.util.Pair;

public class LongestPathFinder2 {

	public static List<Pair<Integer>> findLongestPath(Graph<Pair<Integer>> graph, int m, int n) {
		markupGraphNodeWithMaxPaths(graph, m, n);
		return backtrackPath(graph, m, n);
	}
	
	private static void markupGraphNodeWithMaxPaths(Graph<Pair<Integer>> graph, int m, int n) {
		List<Pair<Integer>> nodes = listNodesInTopologicalOrder(graph, m, n);
		for(Pair<Integer> node : nodes){
			List<Edge<Pair<Integer>>> edgesToNode = graph.edgesTo(node); 
			if(edgesToNode.isEmpty()){
				graph.addMarkupNumber(node, 0);
			} else {
				int max = Integer.MIN_VALUE;
				for(Edge<Pair<Integer>> edge : edgesToNode){
					int preNodePathValue  = graph.markupNumber(edge.startNode);
					int candidate = preNodePathValue + edge.weight;
					if(candidate > max){
						max = candidate;
					}
				}
				graph.addMarkupNumber(node, max);
			}
		}
	}
	
	private static List<Pair<Integer>> backtrackPath(Graph<Pair<Integer>> graph, int m, int n){
		List<Pair<Integer>> path = new LinkedList<>();
		Pair<Integer> startNode = new Pair<>(0, 0);
		Pair<Integer> node = new Pair<>(m, n);
		path.add(node);
		while(!node.equals(startNode)){
			int pathValue = graph.markupNumber(node);
			for(Edge<Pair<Integer>> edge : graph.edgesTo(node)){
				Pair<Integer> preNode = edge.startNode;
				int preNodePathValue = graph.markupNumber(preNode);
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

	private static List<Pair<Integer>> listNodesInTopologicalOrder(Graph<Pair<Integer>> originalGraph, int m, int n) {
		List<Pair<Integer>> nodeList = new LinkedList<>();
		for(int i=0;i<=m;i++){
			for(int j=0;j<=n;j++){
				nodeList.add(new Pair<>(i, j));
			}
		}
		
		return nodeList;
	}
	
}
