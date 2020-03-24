package kits.bioinfo.ucsandiego.course2.part1;

import static java.util.stream.Collectors.toList;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import kits.bioinfo.assembly.KmerCompositioner;
import kits.bioinfo.assembly.KmerGraph;
import kits.bioinfo.core.DnaSequence;
import kits.bioinfo.infrastructure.SequenceReader;
import kits.bioinfo.math.graph.MaxNonBranchingPathFinder;

public class Challenge11 {

    /**
     * CODE CHALLENGE: Solve the Contig Generation Problem. Contig Generation
     * Problem: Generate the contigs from a collection of reads (with imperfect
     * coverage). Input: A collection of k-mers Patterns. Output: All contigs in
     * DeBruijn(Patterns).
     */
    public static void main(String[] args) throws IOException {
        List<DnaSequence> edges = SequenceReader.readDnaSequencesPerLine("input/dataset_205_5.txt");
        KmerGraph graph = KmerGraph.buildDeBrujinGraph(edges);
        List<List<DnaSequence>> maxNonBranchingPaths = MaxNonBranchingPathFinder.findMaxNonBranchingPaths(graph);
        List<DnaSequence> contigs = maxNonBranchingPaths.stream().map(path -> KmerCompositioner.readSequenceFromComposition(path))
                .collect(Collectors.toList());
        Files.write(Paths.get("./output/output_205_5.txt"), contigs.stream().map(contig -> contig.toString()).collect(toList()));
    }

}
