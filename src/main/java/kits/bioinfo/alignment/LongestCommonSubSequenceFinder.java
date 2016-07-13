package kits.bioinfo.alignment;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import kits.bioinfo.alignment.aligner.SequenceAligner;
import kits.bioinfo.alignment.aligner.SequenceAligner.AlignmentResult;
import kits.bioinfo.alignment.scorefunction.ScoreFunction;
import kits.bioinfo.core.Sequence;

public class LongestCommonSubSequenceFinder {

	private final static <T> ScoreFunction<T> scoreFunctionForEditDistance(){
		return ScoreFunction.basic(1, 0, 0);
	}
	
	public static <T> Sequence<T> findOneSequence(Sequence<T> sequence1, Sequence<T> sequence2) {
		SequenceAligner<T> aligner = new SequenceAligner<T>(scoreFunctionForEditDistance());
		AlignmentResult<T> result = aligner.findOneGlobalAlignment(sequence1.toSequence(), sequence2.toSequence());
		return createCommonSequence(result);
	}

	public static <T> Set<Sequence<T>> findAllSequences(Sequence<T> sequence1, Sequence<T> sequence2) {
		SequenceAligner<T> aligner = new SequenceAligner<T>(scoreFunctionForEditDistance());
		Set<AlignmentResult<T>> results = aligner.findAllGlobalAlignments(sequence1.toSequence(), sequence2.toSequence());
		return results.stream().map(result -> createCommonSequence(result)).collect(Collectors.toSet());
	}
	
	private static <T> Sequence<T> createCommonSequence(AlignmentResult<T> alignmentResult){
		final Sequence<T> sequence1 = alignmentResult.sequence1;
		final Sequence<T> sequence2 = alignmentResult.sequence2;
		Sequence<T> sequence = new Sequence<>();
		for(int i=0;i<sequence1.length();i++){
			if(Objects.equals(sequence1.position(i), sequence2.position(i))){
				sequence = sequence.append(sequence1.position(i));
			}
		}
		return sequence;
	}
	
}
