package kits.bioinfo.matcher;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import kits.bioinfo.core.Sequence;

public class SubSequenceMatcher implements Matcher {

	protected final Sequence pattern;
	
	public SubSequenceMatcher(Sequence pattern) {
		this.pattern = pattern;
	}
	
	public SubSequenceMatcher(String patternString) {
		this.pattern = new Sequence(patternString);
	}

	@Override
	public boolean matches(Sequence sequence) {
		for(int index=0;index<sequence.length()-pattern.length()+1;index++) {
			if(matchesSubSequence(sequence.subSequence(index, pattern.length()))) return true;
		}
		return false;
	}

	@Override
	public List<Integer> matchStartIndexes(Sequence sequence) {
		List<Integer> matchStartIndexes = new LinkedList<>();
		for(int index=0;index<sequence.length()-pattern.length()+1;index++) {
			if(matchesSubSequence(sequence.subSequence(index, pattern.length()))) matchStartIndexes.add(index);
		}
		return Collections.unmodifiableList(matchStartIndexes);
	}

	protected boolean matchesSubSequence(Sequence subSequence) {
		return pattern.equals(subSequence);
	}
	
	@Override
	public int matchCount(Sequence sequence) {
		return matchStartIndexes(sequence).size();
	}
	
}
