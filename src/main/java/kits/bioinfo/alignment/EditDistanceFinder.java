package kits.bioinfo.alignment;

import kits.bioinfo.alignment.aligner.AlignmentResult;
import kits.bioinfo.alignment.aligner.GlobalSequenceAligner;
import kits.bioinfo.alignment.scorefunction.ScoreFunction;
import kits.bioinfo.core.Sequence;

public class EditDistanceFinder {

    public static <T> int findEditDistance(Sequence<T> sequence1, Sequence<T> sequence2) {
        GlobalSequenceAligner<T> aligner = new GlobalSequenceAligner<>(ScoreFunction.editDistance());
        AlignmentResult<T> alignment = aligner.findOneAlignment(sequence1, sequence2);

        return -alignment.score;
    }

}
