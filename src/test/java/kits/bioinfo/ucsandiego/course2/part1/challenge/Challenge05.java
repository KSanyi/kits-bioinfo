package kits.bioinfo.ucsandiego.course2.part1.challenge;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import kits.bioinfo.assembly.KmerGraph;
import kits.bioinfo.core.DnaSequence;
import kits.bioinfo.infrastructure.SequenceReader;

public class Challenge05 {

    /**
     * DeBruijn Graph from k-mers Problem: Construct the de Bruijn graph from a
     * set of k-mers. Input: A collection of k-mers Patterns. Output: The
     * adjacency list of the de Bruijn graph DeBruijn(Patterns). CODE CHALLENGE:
     * Solve the de Bruijn Graph from k-mers Problem.
     */
    public static void main(String[] args) throws IOException {
        List<DnaSequence> edges = SequenceReader.readDnaSequencesPerLine("input/dataset_200_7.txt");
        KmerGraph graph = KmerGraph.buildDeBrujinGraph(edges);
        Files.write(Paths.get("./output/output_200_7.txt"), List.of(graph.toString()));
    }

}
