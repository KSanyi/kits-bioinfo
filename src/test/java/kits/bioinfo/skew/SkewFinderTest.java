package kits.bioinfo.skew;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import kits.bioinfo.core.DnaSequence;

public class SkewFinderTest {

    private SkewFinder skewFinder = new SkewFinder();

    @Test
    public void calculateSkew() {
        List<Integer> expectedSkewList = Arrays.asList(0, -1, -1, -1, 0, 1, 2, 1, 1, 1, 0, 1, 2, 1, 0, 0, 0, 0, -1, 0, -1, -2);
        assertEquals(expectedSkewList, skewFinder.calculateSkew(new DnaSequence("CATGGGCATCGGCCATACGCC")));
    }

    @Test
    public void findMinSkew() {
        assertEquals(Arrays.asList(11, 24),
                skewFinder.calculateSkewMin(new DnaSequence("TAAAGACTGCCGAGAGGCCAACACGAGTGCTAGAACGAGGGGCGTAAACGCGGGTCCGAT")));
    }

}
