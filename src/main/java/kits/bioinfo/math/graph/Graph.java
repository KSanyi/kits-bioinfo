package kits.bioinfo.math.graph;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

public class Graph<T> {

    private final Map<T, List<Edge<T>>> adjacencyMap = new HashMap<>();
    private final Map<T, List<Edge<T>>> preAdjacencyMap = new HashMap<>();
    private final Map<T, Object> dataMap = new HashMap<>();

    public Graph() {}

    public Graph(List<Edge<T>> edges) {
        for (Edge<T> edge : edges) {
            addEdge(edge);
        }
    }

    public void addEdge(Edge<T> edge) {
        List<Edge<T>> edgesFromNode = adjacencyMap.get(edge.startNode);
        if (edgesFromNode == null) {
            edgesFromNode = new LinkedList<>();
            adjacencyMap.put(edge.startNode, edgesFromNode);
        }
        edgesFromNode.add(edge);

        if (!containsNode(edge.endNode)) {
            adjacencyMap.put(edge.endNode, new LinkedList<>());
        }

        List<Edge<T>> edgesToNode = preAdjacencyMap.get(edge.endNode);
        if (edgesToNode == null) {
            edgesToNode = new LinkedList<>();
            preAdjacencyMap.put(edge.endNode, edgesToNode);
        }
        edgesToNode.add(edge);

        if (!containsNode(edge.startNode)) {
            preAdjacencyMap.put(edge.startNode, new LinkedList<>());
        }
    }

    public void removeNode(T node) {
        adjacencyMap.remove(node);
        for (List<Edge<T>> edges : adjacencyMap.values()) {
            List<Edge<T>> edgesToRemove = edges.stream().filter(edge -> edge.endNode.equals(node)).collect(Collectors.toList());
            edgesToRemove.forEach(edge -> edges.remove(edge));
        }
        preAdjacencyMap.remove(node);
        for (List<Edge<T>> edges : preAdjacencyMap.values()) {
            List<Edge<T>> edgesToRemove = edges.stream().filter(edge -> edge.startNode.equals(node)).collect(Collectors.toList());
            edgesToRemove.forEach(edge -> edges.remove(edge));
        }
        dataMap.remove(node);
    }

    public void addNode(T node) {
        if (adjacencyMap.containsKey(node))
            throw new IllegalArgumentException("Node is already in graph");
        adjacencyMap.put(node, new LinkedList<>());
    }

    public Set<T> nodes() {
        return Set.copyOf(adjacencyMap.keySet());
    }

    public boolean containsNode(T node) {
        return adjacencyMap.containsKey(node);
    }

    public List<Edge<T>> edges() {
        return adjacencyMap.values().stream().flatMap(List::stream).collect(toList());
    }

    public Set<T> adjacentNodes(T node) {
        return adjacencyMap.getOrDefault(node, List.of()).stream().map(edge -> edge.endNode).collect(toSet());
    }
    
    public Set<T> preAdjacentNodes(T node) {
        return preAdjacencyMap.getOrDefault(node, List.of()).stream().map(edge -> edge.startNode).collect(toSet());
    }

    public List<Edge<T>> edgesFrom(T node) {
        return List.copyOf(adjacencyMap.getOrDefault(node, List.of()));
    }

    public List<Edge<T>> edgesTo(T node) {
        return List.copyOf(preAdjacencyMap.getOrDefault(node, List.of()));
    }

    public int inDegree(T node) {
        return edgesTo(node).size();
    }

    public int outDegree(T node) {
        return edgesFrom(node).size();
    }

    public void addData(T node, Object data) {
        dataMap.put(node, data);
    }

    public Object data(T node) {
        return dataMap.get(node);
    }

    public String printEdges() {
        StringBuilder sb = new StringBuilder();
        for (Entry<T, List<Edge<T>>> entry : adjacencyMap.entrySet()) {
            T node = entry.getKey();
            entry.getValue().stream().forEach(edge -> sb.append(node + " -> " + edge.endNode + "\n"));
        }
        return sb.toString();
    }

    public boolean isEmpty() {
        return adjacencyMap.isEmpty();
    }
    
    public Graph<T> clone() {
        return new Graph<>(edges());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Entry<T, List<Edge<T>>> entry : adjacencyMap.entrySet()) {
            sb.append(entry.getKey() + " -> ");
            sb.append(entry.getValue().stream().map(edge -> edge.endNode.toString()).collect(Collectors.joining(", ")) + "\n");
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == null)
            return false;
        if (!(other instanceof Graph<?>))
            return false;
        return adjacencyMap.equals(((Graph<?>) other).adjacencyMap);
    }

    @Override
    public int hashCode() {
        return adjacencyMap.hashCode();
    }

    public static class Edge<T> {
        
        public final T startNode;
        public final T endNode;
        public final Integer weight;

        public Edge(T startNode, T endNode, Integer weight) {
            this.startNode = startNode;
            this.endNode = endNode;
            this.weight = weight;
        }

        public Edge(T startNode, T endNode) {
            this(startNode, endNode, null);
        }

        @Override
        public String toString() {
            return startNode + " --" + (weight != null ? "(" + weight + ")" : "") + "--> " + endNode;
        }

        @Override
        public boolean equals(Object other) {
            if (other == null)
                return false;
            Edge<?> otherEdge = (Edge<?>) other;
            return otherEdge.startNode.equals(startNode) && otherEdge.endNode.equals(endNode);
        }

        @Override
        public int hashCode() {
            return startNode.hashCode() * 37 + endNode.hashCode();
        }
    }
    
    public static class Path<T> {
        
        private final List<Edge<T>> edges;
        
        public Path(List<Edge<T>> edges) {
            this.edges = List.copyOf(edges);
        }
        
        public int weight() {
            return edges.stream().mapToInt(e -> e.weight).sum();
        }
        
        public Path<T> append(Edge<T> edge) {
            List<Edge<T>> appenededEdges = new ArrayList<>(edges);
            appenededEdges.add(edge);
            return new Path<>(appenededEdges);
        }
        
        public List<T> nodes() {
            List<T> nodes = edges.stream().map(e -> e.startNode).collect(toList());
            nodes.add(edges.get(edges.size()-1).endNode);
            return List.copyOf(nodes);
        }
        
        @Override
        public String toString() {
            return edges.toString();
        }

        public static <T> Path<T> empty() {
            return new Path<>(List.of());
        }
        
    }

}
