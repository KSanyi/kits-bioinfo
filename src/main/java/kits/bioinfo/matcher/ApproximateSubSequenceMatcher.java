package kits.bioinfo.matcher;

import kits.bioinfo.base.Sequence;

public class ApproximateSubSequenceMatcher extends SubSequenceMatcher {

	private final int distance;
	
	public ApproximateSubSequenceMatcher(Sequence pattern, int distance) {
		super(pattern);
		this.distance = distance;
	}
	
	public ApproximateSubSequenceMatcher(String patternString, int distance) {
		this(new Sequence(patternString), distance);
	}

	@Override
	protected boolean matchesSubSequence(Sequence subSequence) {
		return pattern.distance(subSequence) <= distance;
	}

}
