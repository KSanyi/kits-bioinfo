package kits.bioinfo.math.graph;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class EulerianPathFinderTest {

    @Test
    public void test() {

        List<String> adjacencyStrings = Arrays.asList("0 -> 2", "1 -> 3", "2 -> 1", "3 -> 0,4", "6 -> 3,7", "7 -> 8", "8 -> 9", "9 -> 6");

        Graph<Integer> graph = GraphParser.buildIntGraphFromAdjacencyStrings(adjacencyStrings);

        assertEquals(graph, GraphParser.buildIntGraphFromAdjacencyStrings(Arrays.asList(graph.toString().split("\n"))));

        List<Integer> eulerianPath = EulerianPathFinder.findEulerianPath(graph);

        assertEquals(Arrays.asList(6, 7, 8, 9, 6, 3, 0, 2, 1, 3, 4), eulerianPath);
    }

}
