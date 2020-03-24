package kits.bioinfo.motif;

import java.util.List;

import org.junit.jupiter.api.Test;

import kits.bioinfo.core.DnaSequence;

public class GreedyMotifFinderTest {

    @Test
    public void test() {
        List<DnaSequence> sequences = List.of(new DnaSequence("GGCGTTCAGGCA"), 
                new DnaSequence("AAGAATCAGTCA"), new DnaSequence("CAAGGAGTTCGC"),
                new DnaSequence("CACGTCAATCAC"), new DnaSequence("CAATAATATTCG"));

        System.out.println(GreedyMotifFinder.findMotifs(sequences, 3));
    }

}
