package kits.bioinfo.matcher;

import java.util.List;

import kits.bioinfo.core.DnaSequence;

public interface Matcher {

	boolean matches(DnaSequence sequence);
	
	List<Integer> matchStartIndexes(DnaSequence sequence);
	
	int matchCount(DnaSequence sequence);
	
}
