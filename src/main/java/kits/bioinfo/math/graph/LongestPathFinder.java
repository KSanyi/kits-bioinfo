package kits.bioinfo.math.graph;

import static java.util.stream.Collectors.toList;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import kits.bioinfo.math.graph.Graph.Edge;

/**
 * This implementation is memory efficient
 * It stores in each node the length (weight) of the longest path in the graph from source to that node
 */
public class LongestPathFinder {

    public static <T> List<T> findLongestPath(Graph<T> graph, T sourceNode, T sinkNode) {
        removeIrrelevantNodesFormGraph(graph, sourceNode);
        calculateAndMarkupGraphNodesWithMaxPaths(graph);
        return backtrackPath(graph, sourceNode, sinkNode);
    }

    private static <T> void calculateAndMarkupGraphNodesWithMaxPaths(Graph<T> graph) {
        List<T> nodes = listNodesInTopologicalOrder(graph);
        for (T node : nodes) {
            if (graph.edgesTo(node).isEmpty()) {
                graph.addData(node, 0);
            } else {
                int max = Integer.MIN_VALUE;
                for (Edge<T> edge : graph.edgesTo(node)) {
                    int preNodePathValue = (Integer)graph.data(edge.startNode);
                    int candidate = preNodePathValue + edge.weight;
                    if (candidate > max) {
                        max = candidate;
                    }
                }
                graph.addData(node, max);
            }
        }
    }

    private static <T> List<T> backtrackPath(Graph<T> graph, T sourceNode, T sinkNode) {
        List<T> path = new LinkedList<>();
        T node = sinkNode;
        path.add(node);
        while (!node.equals(sourceNode)) {
            int pathValue = (Integer)graph.data(node);
            for (Edge<T> edge : graph.edgesTo(node)) {
                T preNode = edge.startNode;
                int preNodePathValue = (Integer)graph.data(preNode);
                if (preNodePathValue + edge.weight == pathValue) {
                    node = preNode;
                    path.add(node);
                    break;
                }
            }
        }
        Collections.reverse(path);
        return path;
    }

    private static <T> List<T> listNodesInTopologicalOrder(Graph<T> graph) {
       
        List<T> nodesInTopologicalOrder = new LinkedList<>();
        
        int numberOfNodes = graph.nodes().size();
        boolean foundNewNode = true;
        while(nodesInTopologicalOrder.size() < numberOfNodes && foundNewNode) {
            foundNewNode = false;
            for(T node : graph.nodes()) {
                if(nodesInTopologicalOrder.containsAll(graph.preAdjacentNodes(node)) && !nodesInTopologicalOrder.contains(node)) {
                    nodesInTopologicalOrder.add(node);
                    foundNewNode = true;
                }
            }
        }
        
        if(!foundNewNode) {
            throw new IllegalArgumentException("Graph is not acyclic");
        } else {
            return nodesInTopologicalOrder;            
        }
    }
    
    private static <T> void removeIrrelevantNodesFormGraph(Graph<T> graph, T sourceNode) {
        List<T> irrelevantSources = findIrrelevantSources(graph, sourceNode);
        while (!irrelevantSources.isEmpty()) {
            irrelevantSources.stream().forEach(source -> graph.removeNode(source));
            irrelevantSources = findIrrelevantSources(graph, sourceNode);
        }
    }

    private static <T> List<T> findIrrelevantSources(Graph<T> graph, T sourceNode) {
        return graph.nodes().stream()
                .filter(node -> !node.equals(sourceNode))
                .filter(node -> graph.inDegree(node) == 0)
                .collect(toList());
    }

}
