package kits.bioinfo.ucsandiego.course2.part1.challenge;

import static java.util.stream.Collectors.joining;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import kits.bioinfo.math.graph.EulerianPathFinder;
import kits.bioinfo.math.graph.Graph;
import kits.bioinfo.math.graph.GraphParser;

public class Challenge07 {

    /**
     * CODE CHALLENGE: Solve the Eulerian Path Problem. Input: The adjacency
     * list of a directed graph that has an Eulerian path. Output: An Eulerian
     * path in this graph.
     */
    public static void main(String[] args) throws IOException {
        Graph<Integer> graph = GraphParser.buildIntGraphFromAdjacencyStrings(Files.readAllLines(Paths.get("input/dataset_203_5.txt")));
        List<Integer> path = EulerianPathFinder.findEulerianPath(graph);
        String pathString = path.stream().map(n -> n.toString()).collect(joining("->"));
        Files.write(Paths.get("./output/output_203_5.txt"), List.of(pathString));
    }

}
