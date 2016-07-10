package kits.bioinfo.matcher;

import kits.bioinfo.core.DnaSequence;

public class ApproximateSubSequenceMatcher extends SubSequenceMatcher {

	private final int distance;
	
	public ApproximateSubSequenceMatcher(DnaSequence pattern, int distance) {
		super(pattern);
		this.distance = distance;
	}
	
	public ApproximateSubSequenceMatcher(String patternString, int distance) {
		this(new DnaSequence(patternString), distance);
	}

	@Override
	protected boolean matchesSubSequence(DnaSequence subSequence) {
		return pattern.hammingDistance(subSequence) <= distance;
	}

}
