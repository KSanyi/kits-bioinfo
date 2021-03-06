package kits.bioinfo.skew;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import kits.bioinfo.core.DnaSequence;

public class SkewFinderTest {

    @Test
    public void calculateSkew() {
        List<Integer> expectedSkewList = List.of(0, -1, -1, -1, 0, 1, 2, 1, 1, 1, 0, 1, 2, 1, 0, 0, 0, 0, -1, 0, -1, -2);
        assertEquals(expectedSkewList, SkewFinder.calculateSkew(new DnaSequence("CATGGGCATCGGCCATACGCC")));
    }

    @Test
    public void findMinSkew() {
        assertEquals(List.of(11, 24),
                SkewFinder.calculateSkewMin(new DnaSequence("TAAAGACTGCCGAGAGGCCAACACGAGTGCTAGAACGAGGGGCGTAAACGCGGGTCCGAT")));
    }

}
