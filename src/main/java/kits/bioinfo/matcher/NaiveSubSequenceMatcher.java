package kits.bioinfo.matcher;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import kits.bioinfo.base.Sequence;

public class NaiveSubSequenceMatcher implements Matcher {

	protected final Sequence pattern;
	
	public NaiveSubSequenceMatcher(Sequence pattern) {
		this.pattern = pattern;
	}
	
	public NaiveSubSequenceMatcher(String patternString) {
		this.pattern = new Sequence(patternString);
	}

	@Override
	public boolean matches(Sequence sequence) {
		outer:
		for(int index=0;index<sequence.length()-pattern.length()+1;index++) {
			for(int j=0;j<pattern.length();j++){
				if(sequence.position(index + j) != pattern.position(j)) {
					continue outer;
				}
			}
			return true;
		}
		return false;
	}

	@Override
	public List<Integer> matchStartIndexes(Sequence sequence) {
		int comparisons = 0;
		
		List<Integer> matchStartIndexes = new LinkedList<>();
		outer:
		for(int index=0;index<sequence.length()-pattern.length()+1;index++) {
			for(int j=0;j<pattern.length();j++){
				comparisons++;
				if(sequence.position(index + j) != pattern.position(j)) {
					continue outer;
				}
			}
			matchStartIndexes.add(index);
		}
		System.out.println("Comparisons: " + comparisons);
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
