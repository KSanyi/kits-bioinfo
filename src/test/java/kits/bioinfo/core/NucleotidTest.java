package kits.bioinfo.core;

import static kits.bioinfo.core.DnaBase.*;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import kits.bioinfo.core.DnaBase;

public class NucleotidTest {

    @Test
    public void others() {
        assertEquals(new HashSet<>(Arrays.asList(C, T, G)), DnaBase.others(A));
        assertEquals(new HashSet<>(Arrays.asList(A, T, G)), DnaBase.others(C));
        assertEquals(new HashSet<>(Arrays.asList(A, C, G)), DnaBase.others(T));
        assertEquals(new HashSet<>(Arrays.asList(A, C, T)), DnaBase.others(G));
    }

}
