package kits.bioinfo.alignment.aligner;

import java.util.Optional;
import java.util.stream.IntStream;

import kits.bioinfo.alignment.scorefunction.ScoreFunction;
import kits.bioinfo.core.Sequence;

public class AlignmentUtil {

    public static <T> void checkAlignment(AlignmentResult<T> alignmentResult, ScoreFunction<T> scoreFunction) {
        Sequence<T> sequence1 = alignmentResult.sequence1();
        Sequence<T> sequence2 = alignmentResult.sequence2();
        if (sequence1.length() != sequence2.length()) {
            throw new IllegalArgumentException("Alignments with different lengths");
        }

        int score = IntStream.range(0, sequence1.length())
            .map(i -> scoreFunction.score(Optional.ofNullable(sequence1.position(i)), Optional.ofNullable(sequence2.position(i))))
            .sum();

        if (score != alignmentResult.score()) {
            throw new IllegalArgumentException("Alignments has illegal score: " + score + " instead of " + alignmentResult.score());
        }
    }

}
