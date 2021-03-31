package kits.bioinfo.alignment.aligner;

import java.util.Objects;

import kits.bioinfo.core.Sequence;

public record AlignmentResult<T>(Sequence<T> sequence1, Sequence<T> sequence2, int score) {

    public String toString() {
        return sequence1 + "\n" + sequence2 + "\nScore: " + score;
    }
    
    public Sequence<T> commonSequence() {
        Sequence<T> sequence = new Sequence<>();
        for (int i = 0; i < sequence1.length(); i++) {
            if (Objects.equals(sequence1.position(i), sequence2.position(i))) {
                sequence = sequence.append(sequence1.position(i));
            }
        }
        return sequence;
    }
}
