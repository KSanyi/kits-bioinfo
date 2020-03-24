package kits.bioinfo.alignment.aligner;

import kits.bioinfo.core.Sequence;

public record AlignmentResult<T>(Sequence<T> sequence1, Sequence<T> sequence2, int score) {

    public String toString() {
        return sequence1 + "\n" + sequence2 + "\nScore: " + score;
    }
}
