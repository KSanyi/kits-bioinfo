package kits.bioinfo.math.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import kits.bioinfo.math.graph.EulerianCycleFinder;
import kits.bioinfo.math.graph.Graph;

public class EulerianCycleFinderTest {

    @Test
    public void test() {

        List<String> adjacencyStrings = Arrays.asList("0 -> 3", "1 -> 0", "2 -> 1,6", "3 -> 2", "4 -> 2", "5 -> 4", "6 -> 5,8", "7 -> 9", "8 -> 7",
                "9 -> 6");

        Graph<Integer> graph = GraphParser.buildIntGraphFromAdjacencyStrings(adjacencyStrings);

        assertEquals(graph, GraphParser.buildIntGraphFromAdjacencyStrings(Arrays.asList(graph.toString().split("\n"))));

        List<Integer> eulerianCycle = EulerianCycleFinder.findEulerianCycle(graph);

        List<Integer> expectedCycle = Arrays.asList(1, 0, 3, 2, 6, 8, 7, 9, 6, 5, 4, 2, 1);

        assertTrue(cyclesEqual(expectedCycle, eulerianCycle));
    }

    private static boolean cyclesEqual(List<Integer> cycle1, List<Integer> cycle2) {
        List<Integer> list1 = new LinkedList<>();
        list1.addAll(cycle1);
        list1.addAll(cycle1);

        List<Integer> list2 = new LinkedList<>();
        list2.addAll(cycle2);
        list2.addAll(cycle2);

        return list1.containsAll(cycle2) && list2.containsAll(cycle1);
    }

}
