package kits.bioinfo.util;

import static java.util.Collections.emptyList;
import static java.util.Collections.singleton;
import static java.util.stream.Collectors.toSet;
import static kits.bioinfo.core.Nucleotid.A;
import static kits.bioinfo.core.Nucleotid.C;
import static kits.bioinfo.core.Nucleotid.G;
import static kits.bioinfo.core.Nucleotid.T;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import kits.bioinfo.core.Nucleotid;
import kits.bioinfo.core.Sequence;

public class AllPossibleSequenceGenerator {

	private List<Nucleotid> bases = Arrays.asList(A, C, T, G);
	
	public Set<Sequence> generateAllPossibleSequences(int length) {
		if(length == 0) {
			return singleton(new Sequence(emptyList()));
		} else {
			Set<Sequence> sequences = generateAllPossibleSequences(length-1);
			Set<Sequence> appendedSequences = new HashSet<>();
			for(Nucleotid base : bases) {
				appendedSequences.addAll(sequences.stream().map(sequence -> sequence.append(base)).collect(toSet()));
			}
			return appendedSequences;
		}
	}
	
}
