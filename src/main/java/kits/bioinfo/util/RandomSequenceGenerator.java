package kits.bioinfo.util;

import static kits.bioinfo.core.Nucleotid.*;

import java.util.Random;

import kits.bioinfo.core.Nucleotid;
import kits.bioinfo.core.Sequence;

public class RandomSequenceGenerator {

	private final Random random;
	
	private Nucleotid[] bases = new Nucleotid[]{A, C, T, G};
	
	public RandomSequenceGenerator(long seed) {
		random = new Random(seed);
	}
	
	public RandomSequenceGenerator() {
		random = new Random();
	}
	
	public Sequence generateRandomSequence(int length) {
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<length;i++) {
			sb.append(bases[random.nextInt(4)]);
		}
		return new Sequence(sb.toString());
	}
	
}
