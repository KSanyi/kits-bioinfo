package kits.bioinfo.assembly;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import kits.bioinfo.core.DnaSequence;

public class KmerGraphTest {

    @Test
    public void buildGraphFromNodes() {
        KmerGraph graph = KmerGraph.buildFromKmerNodesList(Arrays.asList(new DnaSequence("ATGCG"), new DnaSequence("GCATG"), new DnaSequence("CATGC"),
                new DnaSequence("AGGCA"), new DnaSequence("GGCAT")));
        System.out.println(graph.printEdges());
    }

    @Test
    public void buildGraphFromEdges() {
        DnaSequence sequence = new DnaSequence("AAGATTCTCTAAGA");
        KmerGraph graph = KmerGraph.buildDeBrujinGraph(KmerCompositioner.generateCompositions(sequence, 3));
        System.out.println(graph.toString());
    }

}
