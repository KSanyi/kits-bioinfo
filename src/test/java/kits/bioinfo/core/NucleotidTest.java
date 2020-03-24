package kits.bioinfo.core;

import static kits.bioinfo.core.DnaBase.A;
import static kits.bioinfo.core.DnaBase.C;
import static kits.bioinfo.core.DnaBase.G;
import static kits.bioinfo.core.DnaBase.T;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.jupiter.api.Test;

public class NucleotidTest {

    @Test
    public void others() {
        assertEquals(new HashSet<>(Arrays.asList(C, T, G)), DnaBase.others(A));
        assertEquals(new HashSet<>(Arrays.asList(A, T, G)), DnaBase.others(C));
        assertEquals(new HashSet<>(Arrays.asList(A, C, G)), DnaBase.others(T));
        assertEquals(new HashSet<>(Arrays.asList(A, C, T)), DnaBase.others(G));
    }

}
