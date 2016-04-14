package kits.bioinfo.clump;

import java.util.Set;

import kits.bioinfo.base.Sequence;

public interface ClumpFinder {

	Set<Sequence> findKmersFormingClumps(Sequence sequence, int L, int k, int t);
	
}
