package kits.bioinfo.ucsandiego.course3.part1.challenge;

import static java.util.stream.Collectors.joining;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import kits.bioinfo.math.graph.Graph;
import kits.bioinfo.math.graph.Graph.Path;
import kits.bioinfo.math.graph.GraphParser;
import kits.bioinfo.math.graph.LongestPathFinder2;

public class Challenge4 {

    /**
     * Code Challenge: Solve the Longest Path in a DAG Problem. 
     * Input:  An integer representing the source node of a graph, followed by an integer
     *         representing the sink node of the graph, followed by a list of edges in
     *         the graph. The edge notation "0->1:7" indicates that an edge connects node 0 to node 1 with weight 7. 
     * 
     * Output: The length of a longest path in the graph, followed by a longest path. 
     *         (If multiple longest paths exist, you may return any one.)
     */
    public static void main(String[] args) throws Exception {
        List<String> lines = Files.readAllLines(Paths.get("input/dataset_245_7.txt"));
        int sourceNode = Integer.parseInt(lines.get(0));
        int sinkNode = Integer.parseInt(lines.get(1));
        Graph<Integer> graph = GraphParser.buildWeightedIntGraphFromAdjacencyStrings(lines.subList(2, lines.size()));

        Path<Integer> longestPath = LongestPathFinder2.findLongestPath(graph, sourceNode, sinkNode);
        System.out.println(longestPath.weight());
        System.out.println(longestPath.nodes().stream().map(v -> v.toString()).collect(joining("->")));
    }

}
