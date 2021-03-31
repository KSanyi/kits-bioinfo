package kits.bioinfo.alignment;

import kits.bioinfo.alignment.aligner.AlignmentResult;
import kits.bioinfo.alignment.aligner.GlobalSequenceAligner;
import kits.bioinfo.alignment.scorefunction.ScoreFunction;
import kits.bioinfo.core.Sequence;

public class LongestCommonSubSequenceFinder {

    public static <T> Sequence<T> findOneSequence(Sequence<T> sequence1, Sequence<T> sequence2) {
        GlobalSequenceAligner<T> aligner = new GlobalSequenceAligner<T>(ScoreFunction.basic(1, 0, 0));
        AlignmentResult<T> result = aligner.findOneAlignment(sequence1.toSequence(), sequence2.toSequence());
        return result.commonSequence();
    }

}
