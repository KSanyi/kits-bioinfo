package kits.bioinfo.alignment.aligner;

import kits.bioinfo.core.Sequence;

public class AlignmentResult<T> {
	public final Sequence<T> sequence1;
	public final Sequence<T> sequence2;
	public final int score;

	public AlignmentResult(Sequence<T> sequence1, Sequence<T> sequence2, int score) {
		this.sequence1 = sequence1;
		this.sequence2 = sequence2;
		this.score = score;
	}

	public String toString() {
		return sequence1 + "\n" + sequence2 + "\nScore: " + score;
	}
}
