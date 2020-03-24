package kits.bioinfo.motif;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import org.junit.jupiter.api.Test;

import kits.bioinfo.core.DnaSequence;

public class MotifFinderTest {

    @Test
    public void test1() {
        Set<DnaSequence> sequences = Set.of(new DnaSequence("ATTTGGC"), new DnaSequence("TGCCTTA"), new DnaSequence("CGGTATC"), new DnaSequence("GAAAATT"));

        Set<DnaSequence> motifs = MotifFinder.findMotifs(sequences, 3, 1);

        assertEquals(Set.of(new DnaSequence("ATA"), new DnaSequence("ATT"), new DnaSequence("GTT"), new DnaSequence("TTT")), motifs);
    }

}
