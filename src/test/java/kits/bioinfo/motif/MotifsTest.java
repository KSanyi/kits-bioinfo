package kits.bioinfo.motif;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import kits.bioinfo.core.DnaSequence;

public class MotifsTest {

    @Test
    public void consensusString() {
        assertEquals(new DnaSequence("AAAA"), Motifs.consensusString(Arrays.asList(new DnaSequence("AAAA"))));

        assertEquals(new DnaSequence("ACTA"),
                Motifs.consensusString(Arrays.asList(new DnaSequence("ACAC"), new DnaSequence("ACTA"), new DnaSequence("CCTA"))));
    }

    @Test
    public void score() {
        assertEquals(0, Motifs.score(Arrays.asList(new DnaSequence("AAAA"))));

        assertEquals(3, Motifs.score(Arrays.asList(new DnaSequence("ACAC"), new DnaSequence("ACTA"), new DnaSequence("CCTA"))));
    }

    @Test
    public void score2() {

        List<DnaSequence> motifs = Arrays.asList(new DnaSequence("TCTCGGGG"), new DnaSequence("CCAAGGTG"), new DnaSequence("TACAGGCG"),
                new DnaSequence("TTCAGGTG"), new DnaSequence("TCCACGTG"));

        assertEquals(new DnaSequence("TCCAGGTG"), Motifs.consensusString(motifs));
        assertEquals(9, Motifs.score(motifs));
    }

}
