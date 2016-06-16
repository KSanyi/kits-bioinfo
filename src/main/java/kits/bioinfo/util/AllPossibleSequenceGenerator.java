package kits.bioinfo.util;

import static java.util.Collections.emptyList;
import static java.util.Collections.singleton;
import static java.util.stream.Collectors.toSet;
import static kits.bioinfo.core.DnaBase.A;
import static kits.bioinfo.core.DnaBase.C;
import static kits.bioinfo.core.DnaBase.G;
import static kits.bioinfo.core.DnaBase.T;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import kits.bioinfo.core.DnaBase;
import kits.bioinfo.core.DnaSequence;

public class AllPossibleSequenceGenerator {

	private static final List<DnaBase> bases = Arrays.asList(A, C, T, G);
	
	public static Set<DnaSequence> generateAllPossibleSequences(int length) {
		if(length == 0) {
			return singleton(new DnaSequence(emptyList()));
		} else {
			Set<DnaSequence> sequences = generateAllPossibleSequences(length-1);
			Set<DnaSequence> appendedSequences = new HashSet<>();
			for(DnaBase base : bases) {
				appendedSequences.addAll(sequences.stream().map(sequence -> sequence.append(base)).collect(toSet()));
			}
			return appendedSequences;
		}
	}
	
}
