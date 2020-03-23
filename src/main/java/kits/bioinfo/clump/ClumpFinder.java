package kits.bioinfo.clump;

import java.util.Set;

import kits.bioinfo.core.DnaSequence;

public interface ClumpFinder {

    Set<DnaSequence> findKmersFormingClumps(DnaSequence sequence, int L, int k, int t);

}
