package kits.bioinfo.base.generator;

import static java.util.Collections.emptyList;
import static java.util.Collections.singleton;
import static java.util.stream.Collectors.toSet;
import static kits.bioinfo.base.Nucleotid.A;
import static kits.bioinfo.base.Nucleotid.C;
import static kits.bioinfo.base.Nucleotid.G;
import static kits.bioinfo.base.Nucleotid.T;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import kits.bioinfo.base.Nucleotid;
import kits.bioinfo.base.Sequence;

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
