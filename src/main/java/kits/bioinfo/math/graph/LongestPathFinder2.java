package kits.bioinfo.math.graph;

import static java.util.stream.Collectors.toList;

import java.util.LinkedList;
import java.util.List;

import kits.bioinfo.math.graph.Graph.Edge;
import kits.bioinfo.math.graph.Graph.Path;

/**
 * This implementation is not so memory efficient
 * It stores in each node the of the longest path in the graph from source to that node
 */
public class LongestPathFinder2 {

    @SuppressWarnings("unchecked")
    public static <T> Path<T> findLongestPath(Graph<T> graph, T sourceNode, T sinkNode) {
        removeIrrelevantNodesFormGraph(graph, sourceNode);
        calculateAndMarkGraphNodesWithMaxPaths(graph);
        return (Path<T>)graph.data(sinkNode);//backtrackPath(graph, sourceNode, sinkNode);
    }

    private static <T> void calculateAndMarkGraphNodesWithMaxPaths(Graph<T> graph) {
        List<T> nodes = listNodesInTopologicalOrder(graph);
        for (T node : nodes) {
            if (graph.edgesTo(node).isEmpty()) {
                graph.addData(node, Path.empty());
            } else {
                Path<T> longestPath = Path.empty();
                for (Edge<T> edge : graph.edgesTo(node)) {
                    @SuppressWarnings("unchecked")
                    Path<T> preNodePath = (Path<T>)graph.data(edge.startNode);
                    if (preNodePath.weight() + edge.weight > longestPath.weight()) {
                        longestPath = preNodePath.append(edge);
                    }
                }
                graph.addData(node, longestPath);
            }
        }
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
