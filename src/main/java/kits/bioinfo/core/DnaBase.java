package kits.bioinfo.core;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public enum DnaBase {

    A, C, T, G, N;

    public static DnaBase[] bases() {
        return new DnaBase[] { A, C, T, G };
    }

    public static Set<DnaBase> others(DnaBase base) {
        Set<DnaBase> bases = new HashSet<>(Arrays.asList(values()));
        bases.remove(N);
        bases.remove(base);
        return Collections.unmodifiableSet(bases);
    }

    public DnaBase complement() {
        switch (this) {
        case A:
            return T;
        case T:
            return A;
        case C:
            return G;
        case G:
            return C;
        case N:
            return N;
        default:
            throw new RuntimeException();
        }
    }

    public static DnaBase of(char c) {
        switch (Character.toUpperCase(c)) {
        case 'A':
            return A;
        case 'T':
            return T;
        case 'C':
            return C;
        case 'G':
            return G;
        case 'N':
            return N;
        default:
            throw new IllegalArgumentException("Can not create DNA base from character " + c);
        }
    }

}
