package kits.bioinfo.alignment;

import kits.bioinfo.alignment.aligner.SequenceAligner;
import kits.bioinfo.alignment.aligner.SequenceAligner.AlignmentResult;
import kits.bioinfo.alignment.scorefunction.ScoreFunction;
import kits.bioinfo.core.Sequence;

public class EditDistanceFinder {

	public static <T> int findEditDistance(Sequence<T> sequence1, Sequence<T> sequence2){
		SequenceAligner<T> aligner = new SequenceAligner<>(ScoreFunction.editDistance());
		AlignmentResult<T> alignment = aligner.findOneGlobalAlignment(sequence1, sequence2);
		
		return -alignment.score;
	}
	
}
