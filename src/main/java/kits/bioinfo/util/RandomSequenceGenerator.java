package kits.bioinfo.util;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import kits.bioinfo.core.AminoAcid;
import kits.bioinfo.core.DnaBase;
import kits.bioinfo.core.DnaSequence;
import kits.bioinfo.core.Peptid;

public class RandomSequenceGenerator {

	private final Random random;

	public RandomSequenceGenerator(long seed) {
		random = new Random(seed);
	}

	public RandomSequenceGenerator() {
		random = new Random();
	}

	public DnaSequence generateRandomDnaSequence(int length) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			sb.append(DnaBase.bases()[random.nextInt(DnaBase.bases().length)]);
		}
		return new DnaSequence(sb.toString());
	}

	public Peptid generateRandomPeptid(int length) {
		List<AminoAcid> aminoAcids = new LinkedList<>();
		for (int i = 0; i < length; i++) {
			aminoAcids.add(AminoAcid.values()[random.nextInt(AminoAcid.values().length)]);
		}
		return new Peptid(aminoAcids);
	}

}
