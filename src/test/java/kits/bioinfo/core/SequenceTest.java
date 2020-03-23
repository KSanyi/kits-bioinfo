package kits.bioinfo.core;

import java.util.Arrays;
import java.util.HashSet;

import kits.bioinfo.core.DnaSequence;
import kits.bioinfo.util.RandomSequenceGenerator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class SequenceTest {

    @Test
    public void reverseComplementBasecases() {
        assertEquals(new DnaSequence(""), new DnaSequence("").reverseComplement());
        assertEquals(new DnaSequence("A"), new DnaSequence("T").reverseComplement());
        assertEquals(new DnaSequence("C"), new DnaSequence("G").reverseComplement());
        assertEquals(new DnaSequence("TG"), new DnaSequence("CA").reverseComplement());
    }

    @Test
    public void reverseComplement() {
        assertEquals(new DnaSequence("ACCGGGTTTT"), new DnaSequence("AAAACCCGGT").reverseComplement());
    }

    @Test
    public void reverseComplementOfReverseComplementIsTheOriginalSequence() {
        int NR_OF_TESTS = 1000;
        int SEQUENCE_LENGTH = 1000;

        RandomSequenceGenerator generator = new RandomSequenceGenerator();
        for (int i = 0; i < NR_OF_TESTS; i++) {
            DnaSequence sequence = generator.generateRandomDnaSequence(SEQUENCE_LENGTH);
            assertEquals(sequence, sequence.reverseComplement().reverseComplement());
        }
    }

    @Test
    public void subSequence() {
        assertEquals(new DnaSequence("A"), new DnaSequence("ACTGCTGAC").subSequence(0, 1));
        assertEquals(new DnaSequence("ACTG"), new DnaSequence("ACTGCTGAC").subSequence(0, 4));
        assertEquals(new DnaSequence("TGAC"), new DnaSequence("ACTGCTGAC").subSequence(5, 4));
        assertEquals(new DnaSequence("TGCT"), new DnaSequence("ACTGCTGAC").subSequence(2, 4));
    }

    @Test
    public void prefix() {
        assertEquals(new DnaSequence("ACT"), new DnaSequence("ACTGCTGAC").prefix(3));
        assertEquals(new DnaSequence(""), new DnaSequence("ACTGCTGAC").prefix(0));
        assertEquals(new DnaSequence("ACTGCTGAC"), new DnaSequence("ACTGCTGAC").prefix(9));
    }

    @Test
    public void suffix() {
        assertEquals(new DnaSequence("GAC"), new DnaSequence("ACTGCTGAC").suffix(3));
        assertEquals(new DnaSequence(""), new DnaSequence("ACTGCTGAC").suffix(0));
        assertEquals(new DnaSequence("ACTGCTGAC"), new DnaSequence("ACTGCTGAC").suffix(9));
    }

    @Test
    public void distance() {
        assertEquals(0, new DnaSequence("A").hammingDistance(new DnaSequence("A")));
        assertEquals(0, new DnaSequence("ACTG").hammingDistance(new DnaSequence("ACTG")));
        assertEquals(1, new DnaSequence("A").hammingDistance(new DnaSequence("C")));
        assertEquals(1, new DnaSequence("ACTG").hammingDistance(new DnaSequence("ACTT")));
    }

    @Test
    public void neighbours() {
        assertEquals(new HashSet<>(Arrays.asList(new DnaSequence("A"), new DnaSequence("C"), new DnaSequence("T"), new DnaSequence("G"))),
                new DnaSequence("A").neighbours(1));
        assertEquals(13, new DnaSequence("ACCT").neighbours(1).size());
        assertEquals(67, new DnaSequence("ACCT").neighbours(2).size());
    }

}
