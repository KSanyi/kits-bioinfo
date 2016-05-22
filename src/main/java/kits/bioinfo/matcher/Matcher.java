package kits.bioinfo.matcher;

import java.util.List;

import kits.bioinfo.base.Sequence;

public interface Matcher {

	boolean matches(Sequence sequence);
	
	List<Integer> matchStartIndexes(Sequence sequence);
	
	int matchCount(Sequence sequence);
	
}