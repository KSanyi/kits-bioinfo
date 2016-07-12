package kits.bioinfo.ucsandiego.course3;

import java.util.List;

import kits.bioinfo.alignment.SequenceAligner;
import kits.bioinfo.alignment.SequenceAligner.AlignmentResult;
import kits.bioinfo.alignment.scorefunction.ScoreFunction;
import kits.bioinfo.core.DnaBase;
import kits.bioinfo.core.DnaSequence;
import kits.bioinfo.infrastructure.SequenceReader;

public class Challenge8 {

	/**
	 * Code Challenge: Solve the Fitting Alignment Problem.
     * Input: Two nucleotide strings v and w, where v has length at most 1000 and w has length at most 100.
     * Output: A highest-scoring fitting alignment between v and w. Use the simple scoring method in which
     * matches count +1 and both the mismatch and indel penalties are 1.
	 */
	public static void main(String[] args) throws Exception {
		List<DnaSequence> sequences = SequenceReader.readDnaSequencesPerLine("input/dataset_248_5.txt");
		
		SequenceAligner<DnaBase> aligner = new SequenceAligner<DnaBase>(ScoreFunction.basic(1,  1,  1));
		AlignmentResult<DnaBase> alignment = aligner.findOneFittingAlignment(sequences.get(0).toSequence(), sequences.get(1).toSequence());
		
		System.out.println(alignment.score);
		System.out.println(alignment);
	}

}
