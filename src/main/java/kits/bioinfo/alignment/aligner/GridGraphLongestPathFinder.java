package kits.bioinfo.alignment.aligner;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import kits.bioinfo.alignment.aligner.GridGraph.Edge;
import kits.bioinfo.util.IntPair;

class GridGraphLongestPathFinder {

    public static List<IntPair> findLongestPath(GridGraph graph) {
        fillGrid(graph);

        return backtrackPath(graph);
    }

    private static void fillGrid(GridGraph graph) {

        for (int i=0;i<graph.m;i++) {
            for (int j=0;j<graph.n;j++) {
                List<Edge> edgesTo = graph.edgesTo(i, j);
                int value = calculateValue(graph, edgesTo);
                graph.set(i, j, value);
            }
        }

        int endValue = calculateValue(graph, graph.edgesTo(graph.endNode));
        graph.setValue(endValue);
    }

    private static int calculateValue(GridGraph graph, List<Edge> edgesTo) {
        if (edgesTo.isEmpty()) {
            return 0;
        } else {
            int max = Integer.MIN_VALUE;
            for (Edge edge : edgesTo) {
                int preNodePathValue = graph.get(edge.sourceIndex());
                int candidate = preNodePathValue + edge.weight();
                if (candidate > max) {
                    max = candidate;
                }
            }
            return max;
        }
    }

    private static List<IntPair> backtrackPath(GridGraph graph) {
        List<IntPair> path = new LinkedList<>();
        IntPair node = graph.endNode;
        while (!node.equals(graph.startNode)) {
            int pathValue = graph.get(node);
            List<Edge> edgesTo = graph.edgesTo(node);
            for (Edge edge : edgesTo) {
                int preNodePathValue = graph.get(edge.sourceIndex());
                if (preNodePathValue + edge.weight() == pathValue) {
                    node = edge.sourceIndex();
                    path.add(node);
                    break;
                }
            }
        }
        Collections.reverse(path);
        return path;
    }

}
