package kits.bioinfo.assembly;

import java.util.List;
import static java.util.stream.Collectors.*;
import static java.util.stream.IntStream.*;

import kits.bioinfo.base.Sequence;

public class KmerCompositioner {

	public static List<Sequence> generateCompositions(Sequence sequence, int k) {
		if(sequence.length() < k) throw new IllegalArgumentException("Sequence length must be >= k");
		
		return range(0, sequence.length()-k+1).mapToObj(i -> sequence.subSequence(i, k)).collect(toList());
	}
	
	public static Sequence readSequenceFromComposition(List<Sequence> composition) {
		if(composition.isEmpty()) throw new IllegalArgumentException("Composition can not be empty");
		
		Sequence baseSequence = composition.get(0);
		List<Sequence> furtherSequences = composition.subList(1, composition.size());
		
		int k = baseSequence.length()-1;
		
		for(Sequence sequence : furtherSequences){
			if(sequence.length() != k+1) throw new IllegalArgumentException("All sequences in the composition kust have the same length");
			if(!sequence.subSequence(0, k).equals(baseSequence.subSequence(baseSequence.length()-k, k))) throw new IllegalArgumentException("Sequences in the composition must overlap");
			baseSequence = baseSequence.append(sequence.position(sequence.length()-1));
		}
		
		return baseSequence;
	}
	
}
