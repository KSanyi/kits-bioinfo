package kits.bioinfo.math.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import kits.bioinfo.math.graph.Graph.Edge;
import kits.bioinfo.util.FrequencyMap;

public class EulerianCycleFinder {

    private static Random random = new Random();

    private static <T> T pickRandomElementFromList(List<T> list) {
        return list.get(random.nextInt(list.size()));
    }

    public static <T> List<T> findEulerianCycle(Graph<T> graph) {
        checkGraph(graph);

        List<T> cycle = new ArrayList<>();

        List<Edge<T>> edgesNotSeen = new LinkedList<>(graph.edges());
        T node = edgesNotSeen.iterator().next().startNode;
        cycle.add(node);
        while (!edgesNotSeen.isEmpty()) {
            List<T> startNodesFromEdgesNotSeen = edgesNotSeen.stream().map(edge -> edge.startNode).collect(Collectors.toList());
            node = pickRandomElementFromList(intersection(startNodesFromEdgesNotSeen, cycle)); // the
                                                                                                // intersection
                                                                                                // can
                                                                                                // not
                                                                                                // bee
                                                                                                // empty
            cycle = shiftCycle(cycle, node);
            List<T> newCycle = findCycleFromNode(graph, node, edgesNotSeen);
            cycle.addAll(newCycle);
        }
        return cycle;
    }

    private static <T> List<T> findCycleFromNode(final Graph<T> graph, T node, List<Graph.Edge<T>> edgesNotSeen) {
        List<T> cycle = new LinkedList<>();
        List<Edge<T>> edgesFromNodeNotSeen = intersection(graph.edgesFrom(node), edgesNotSeen);
        while (!edgesFromNodeNotSeen.isEmpty()) {
            Edge<T> edgeToGo = pickRandomElementFromList(edgesFromNodeNotSeen);
            edgesNotSeen.remove(edgeToGo);
            node = edgeToGo.endNode;
            cycle.add(node);
            edgesFromNodeNotSeen = intersection(graph.edgesFrom(node), edgesNotSeen);
        }
        return cycle;
    }

    private static <T> List<T> shiftCycle(List<T> cycle, T newStartNode) {
        int indexOfNewStartNode = cycle.lastIndexOf(newStartNode);
        List<T> endPart = new ArrayList<T>(cycle.subList(0, indexOfNewStartNode + 1));
        List<T> startPart = new ArrayList<T>(cycle.subList(indexOfNewStartNode, cycle.size() - 1));
        startPart.addAll(endPart);
        return startPart;
    }

    private static <E> List<E> intersection(List<E> listA, List<E> listB) {
        List<E> interSection = new LinkedList<>(listA);
        interSection.retainAll(listB);
        return interSection;
    }

    private static <T> void checkGraph(Graph<T> graph) {
        if (graph.edges().isEmpty())
            throw new IllegalArgumentException("Can not find an Eulerian cycle in a graph without edges");

        FrequencyMap<T> inDegreeMap = new FrequencyMap<>();
        FrequencyMap<T> outDegreeMap = new FrequencyMap<>();
        for (Edge<T> edge : graph.edges()) {
            outDegreeMap.put(edge.startNode);
            inDegreeMap.put(edge.endNode);
        }
        Set<T> illegalNodes = graph.nodes().stream().filter(node -> inDegreeMap.frequency(node) != outDegreeMap.frequency(node))
                .collect(Collectors.toSet());
        if (!illegalNodes.isEmpty()) {
            throw new IllegalArgumentException(
                    "Can not find an Eulerian cycle in a graph where there is a node with indegree <> outdegree as node: " + illegalNodes);
        }
    }

}
