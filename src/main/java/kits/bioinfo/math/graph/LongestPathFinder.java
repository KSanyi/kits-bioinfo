package kits.bioinfo.math.graph;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import kits.bioinfo.math.graph.Graph.Edge;

public class LongestPathFinder {

    public static <T> List<T> findLongestPath(Graph<T> graph, T sourceNode, T sinkNode) {
        removeIrrelevantNodeFormGraph(graph, sourceNode);
        markupGraphNodeWithMaxPaths(graph);
        return backtrackPath(graph, sourceNode, sinkNode);
    }

    private static <T> void markupGraphNodeWithMaxPaths(Graph<T> graph) {
        List<T> nodes = listNodesInTopologicalOrder(graph);
        for (T node : nodes) {
            if (graph.edgesTo(node).isEmpty()) {
                graph.addMarkupNumber(node, 0);
            } else {
                int max = Integer.MIN_VALUE;
                for (Edge<T> edge : graph.edgesTo(node)) {
                    int preNodePathValue = graph.markupNumber(edge.startNode);
                    int candidate = preNodePathValue + edge.weight;
                    if (candidate > max) {
                        max = candidate;
                    }
                }
                graph.addMarkupNumber(node, max);
            }
        }
    }

    private static <T> List<T> backtrackPath(Graph<T> graph, T sourceNode, T sinkNode) {
        List<T> path = new LinkedList<>();
        T node = sinkNode;
        path.add(node);
        while (!node.equals(sourceNode)) {
            int pathValue = graph.markupNumber(node);
            for (Edge<T> edge : graph.edgesTo(node)) {
                T preNode = edge.startNode;
                int preNodePathValue = graph.markupNumber(preNode);
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

    private static <T> List<T> listNodesInTopologicalOrder(Graph<T> originalGraph) {
        List<T> nodeList = new LinkedList<>();

        Graph<T> graph = originalGraph;
        while (!graph.isEmpty()) {
            T nodeToRemove = findNodeToRemove(graph);
            nodeList.add(nodeToRemove);

            List<Edge<T>> edges = new LinkedList<>(graph.edges());
            edges.removeAll(graph.edgesTo(nodeToRemove));
            graph = new Graph<>(edges);
        }

        List<T> remainingNodes = new LinkedList<>(originalGraph.nodes());
        remainingNodes.removeAll(nodeList);

        nodeList.addAll(remainingNodes);

        Collections.reverse(nodeList);

        return nodeList;
    }

    private static <T> T findNodeToRemove(Graph<T> graph) {
        return graph.nodes().stream().filter(node -> graph.edgesFrom(node).isEmpty()).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Graph is not acyclic"));
    }

    private static <T> void removeIrrelevantNodeFormGraph(Graph<T> graph, T sourceNode) {
        List<T> irrelevantSources = findIrrelevantSources(graph, sourceNode);
        while (!irrelevantSources.isEmpty()) {
            irrelevantSources.stream().forEach(source -> graph.removeNode(source));
            irrelevantSources = findIrrelevantSources(graph, sourceNode);
        }
    }

    private static <T> List<T> findIrrelevantSources(Graph<T> graph, T sourceNode) {
        List<T> sources = graph.nodes().stream().filter(node -> graph.inDegree(node) == 0).collect(Collectors.toList());
        sources.remove(sourceNode);
        return sources;
    }

}
