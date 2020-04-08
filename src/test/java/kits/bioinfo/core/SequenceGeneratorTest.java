package kits.bioinfo.core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import org.junit.jupiter.api.Test;

import kits.bioinfo.util.AllPossibleSequenceGenerator;

public class SequenceGeneratorTest {

    @Test
    public void generateAllPossibleSequences() {
        assertEquals(Set.of(new DnaSequence("A"), new DnaSequence("C"), new DnaSequence("T"), new DnaSequence("G")),
                AllPossibleSequenceGenerator.generateAllPossibleDnaSequences(1));
        assertEquals(16, AllPossibleSequenceGenerator.generateAllPossibleDnaSequences(2).size());
        assertEquals(64, AllPossibleSequenceGenerator.generateAllPossibleDnaSequences(3).size());
    }

}
