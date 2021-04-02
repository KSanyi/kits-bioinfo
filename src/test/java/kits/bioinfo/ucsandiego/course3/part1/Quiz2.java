package kits.bioinfo.ucsandiego.course3.part1;

import kits.bioinfo.alignment.aligner.FittingSequenceAligner;
import kits.bioinfo.alignment.scorefunction.ScoreFunction;
import kits.bioinfo.core.DnaBase;
import kits.bioinfo.core.DnaSequence;

public class Quiz2 {

    public static void main(String[] args) {
        question3();
    }

    private static void question3() {
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + ": ");

        DnaSequence sequence1 = new DnaSequence("ACGACCACAGATACCGCTATTCACTATATCGTT");
        DnaSequence sequence2 = new DnaSequence("GATACACT");
        FittingSequenceAligner<DnaBase> aligner = new FittingSequenceAligner<>(ScoreFunction.basic(1, 1, 1));
        var alignmentResult = aligner.findOneAlignment(sequence1, sequence2);
        System.out.println(alignmentResult);
    }

}
