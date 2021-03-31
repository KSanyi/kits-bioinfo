package kits.bioinfo.math.graph;

import static java.util.stream.Collectors.toList;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import kits.bioinfo.math.graph.Graph.Edge;

public class GraphParser {

    public static Graph<Integer> buildIntGraphFromAdjacencyStrings(List<String> adjacencyStrings) {
        List<Edge<Integer>> edges = new LinkedList<>();
        for (String aString : adjacencyStrings) {
            if (aString.contains("->")) {
                String[] parts = aString.split("->");
                Integer node = Integer.parseInt(parts[0].trim());
                if (!parts[1].trim().isEmpty()) {
                    List<Edge<Integer>> edgesFromNode = Stream.of(parts[1].split(","))
                            .map(s -> new Edge<>(node, Integer.parseInt(s.trim())))
                            .collect(toList());
                    edges.addAll(edgesFromNode);
                }
            }
        }
        return new Graph<>(edges);
    }

    public static Graph<Integer> buildWeightedIntGraphFromAdjacencyStrings(List<String> adjacencyStrings) {
        List<Edge<Integer>> edges = new LinkedList<>();
        for (String aString : adjacencyStrings) {
            if (aString.contains("->")) {
                String[] parts = aString.split("->");
                String nodeString = parts[0];
                String edgesString = parts[1];
                Integer nodeValue = Integer.parseInt(nodeString.trim());
                List<Edge<Integer>> edgesFromNode = Stream.of(edgesString.split(","))
                        .map(edgeString -> parseEdge(nodeValue, edgeString))
                        .collect(toList());
                edges.addAll(edgesFromNode);
            }
        }
        return new Graph<>(edges);
    }

    private static Edge<Integer> parseEdge(Integer startNode, String edgeString) {
        String[] parts = edgeString.split(":");
        int nodeValue = Integer.parseInt(parts[0].trim());
        int weight = Integer.parseInt(parts[1].trim());
        return new Edge<>(startNode, nodeValue, weight);
    }

}
