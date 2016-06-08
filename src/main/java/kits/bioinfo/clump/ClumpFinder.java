package kits.bioinfo.clump;

import java.util.Set;

import kits.bioinfo.core.Sequence;

public interface ClumpFinder {

	Set<Sequence> findKmersFormingClumps(Sequence sequence, int L, int k, int t);
	
}
