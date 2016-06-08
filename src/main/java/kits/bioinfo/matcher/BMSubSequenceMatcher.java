package kits.bioinfo.matcher;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import kits.bioinfo.core.Nucleotid;
import kits.bioinfo.core.Sequence;

/**
 * Boyer-Moore algorithm
 */
public class BMSubSequenceMatcher implements Matcher {

	protected final Sequence pattern;
	
	private final BadCharacterRuleTable badCharacterRuleTable;
	private final GoodSuffixRuleTable goodSuffixRuleTable;
	
	public BMSubSequenceMatcher(Sequence pattern) {
		this.pattern = pattern;
		badCharacterRuleTable = new BadCharacterRuleTable(pattern);
		goodSuffixRuleTable = new GoodSuffixRuleTable(pattern);
	}
	
	public BMSubSequenceMatcher(String patternString) {
		this(new Sequence(patternString));
	}

	@Override
	public boolean matches(Sequence sequence) {
		outer:
		for(int index=0;index<sequence.length()-pattern.length()+1;index++) {
			for(int j=0;j<pattern.length();j++){
				if(sequence.position(index + j) != pattern.position(j)) {
					index += badCharacterRuleTable.skipLength(sequence.position(index + j), j);
					continue outer;
				}
			}
			return true;
		}
		return false;
	}

	@Override
	public List<Integer> matchStartIndexes(Sequence sequence) {
		int alignments = 0;
		int comparisons = 0;
		
		List<Integer> matchStartIndexes = new LinkedList<>();
		outer:
		for(int index=0;index<sequence.length()-pattern.length()+1;index++) {
			alignments++;
			for(int j=pattern.length()-1;j>=0;j--){
				comparisons++;
				if(sequence.position(index + j) != pattern.position(j)) {
					int goodSufSkip = goodSuffixRuleTable.skipLength(j);
					int badCharSkip = badCharacterRuleTable.skipLength(sequence.position(index + j), j);
					index += Math.max(goodSufSkip, badCharSkip);
					continue outer;
				}
			}
			matchStartIndexes.add(index);
			index += goodSuffixRuleTable.skipAfterMatch;
		}
		System.out.println("Alignments: " + alignments);
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

class BadCharacterRuleTable {
	
	final int[][] table;
	
	BadCharacterRuleTable(Sequence pattern) {
		table = new int[5][pattern.length()];
		for(Nucleotid base : Nucleotid.values()) {
			for(int index=0;index<pattern.length();index++){
				table[base.ordinal()][index] = calculateSkipLength(pattern, base, index);
			}
		}
	}
	
	private int calculateSkipLength(Sequence pattern, Nucleotid base, int position) {
		int counter = 0;
		for(int i=position-1;i>=0;i--){
			if(pattern.position(i) == base) {
				break;
			}
			counter++;
		}
		return counter;
	}
	
	int skipLength(Nucleotid base, int position) {
		return table[base.ordinal()][position];
	}
}

class GoodSuffixRuleTable {
	
	final int[] table;
	
	final int skipAfterMatch;
	
	GoodSuffixRuleTable(Sequence pattern) {
		if(pattern.length() == 0) throw new IllegalArgumentException("Empty pattern");
		table = new int[pattern.length()];
		for(int index=0;index<pattern.length();index++){
			table[index] = calculateSkipLength(pattern, index);
		}
		skipAfterMatch = calculateSkipAfterMatch(pattern);
	}
	
	private int calculateSkipAfterMatch(Sequence pattern) {
		for(int k=pattern.length()-1;k>=1;k--) {
			Sequence suffix = pattern.subSequence(pattern.length()-k, k);
			Sequence postfix = pattern.subSequence(0, k);
			if(suffix.equals(postfix)){
				return pattern.length() - k - 1;
			}
		}
		return pattern.length()-1;
	}
	
	private int calculateSkipLength(Sequence pattern, int position) {
		int counter = 0;
		int length = pattern.length() - position - 1;
		Sequence suffix = pattern.subSequence(position+1, length);
		for(int i=position;i>=0;i--){
			if(suffix.equals(pattern.subSequence(i, length))) {
				break;
			}
			counter++;
		}
		return counter;
	}
	
	int skipLength(int position) {
		return table[position];
	}
	
}