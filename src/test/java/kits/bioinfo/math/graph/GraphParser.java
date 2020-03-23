package kits.bioinfo.math.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
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
                    List<Edge<Integer>> edgesFromNode = Arrays.asList(parts[1].split(",")).stream()
                            .map(s -> new Edge<>(node, Integer.parseInt(s.trim()))).collect(Collectors.toList());
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
                Integer nodeValue = Integer.parseInt(parts[0].trim());
                Set<Integer> nodes = edges.stream().flatMap(edge -> Stream.of(edge.startNode, edge.endNode)).collect(Collectors.toSet());
                Integer newNode = nodes.stream().filter(node -> node.equals(nodeValue)).findFirst().orElseGet(() -> nodeValue);
                List<Edge<Integer>> edgesFromNode = Arrays.asList(parts[1].split(",")).stream().map(s -> parseEdge(newNode, s, nodes))
                        .collect(Collectors.toList());
                edges.addAll(edgesFromNode);
            }
        }
        return new Graph<>(edges);
    }

    private static Edge<Integer> parseEdge(Integer startNode, String edgeString, Set<Integer> nodes) {
        String[] parts = edgeString.split(":");
        int nodeValue = Integer.parseInt(parts[0].trim());
        int weight = Integer.parseInt(parts[1].trim());
        Integer newNode = nodes.stream().filter(node -> node.equals(nodeValue)).findFirst().orElseGet(() -> nodeValue);
        return new Edge<>(startNode, newNode, weight);
    }

}
