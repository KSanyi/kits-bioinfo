package kits.bioinfo.alignment.aligner;

import java.util.Optional;

import kits.bioinfo.alignment.scorefunction.ScoreFunction;
import kits.bioinfo.core.Sequence;

public class AlignmentUtil {

    public static <T> void checkAlignment(AlignmentResult<T> alignmentResult, ScoreFunction<T> scoreFunction) {
        Sequence<T> sequence1 = alignmentResult.sequence1;
        Sequence<T> sequence2 = alignmentResult.sequence2;
        if (sequence1.length() != sequence2.length()) {
            throw new IllegalArgumentException("Alignments with diffrent length");
        }

        int length = sequence1.length();
        int score = 0;
        for (int i = 0; i < length; i++) {
            score += scoreFunction.score(Optional.ofNullable(sequence1.position(i)), Optional.ofNullable(sequence2.position(i)));
        }

        if (score != alignmentResult.score) {
            throw new IllegalArgumentException("Alignments has illegal score: " + score + " instead of " + alignmentResult.score);
        }
    }

}
