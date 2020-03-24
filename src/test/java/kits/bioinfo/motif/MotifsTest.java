package kits.bioinfo.motif;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import kits.bioinfo.core.DnaSequence;

public class MotifsTest {

    @Test
    public void consensusString() {
        assertEquals(new DnaSequence("AAAA"), Motifs.consensusString(List.of(new DnaSequence("AAAA"))));

        assertEquals(new DnaSequence("ACTA"),
                Motifs.consensusString(List.of(new DnaSequence("ACAC"), new DnaSequence("ACTA"), new DnaSequence("CCTA"))));
    }

    @Test
    public void score() {
        assertEquals(0, Motifs.score(List.of(new DnaSequence("AAAA"))));

        assertEquals(3, Motifs.score(List.of(new DnaSequence("ACAC"), new DnaSequence("ACTA"), new DnaSequence("CCTA"))));
    }

    @Test
    public void score2() {

        List<DnaSequence> motifs = List.of(new DnaSequence("TCTCGGGG"), new DnaSequence("CCAAGGTG"), new DnaSequence("TACAGGCG"),
                new DnaSequence("TTCAGGTG"), new DnaSequence("TCCACGTG"));

        assertEquals(new DnaSequence("TCCAGGTG"), Motifs.consensusString(motifs));
        assertEquals(9, Motifs.score(motifs));
    }

}
