package kits.bioinfo.util;

import static kits.bioinfo.core.DnaBase.*;

import java.util.Random;

import kits.bioinfo.core.DnaBase;
import kits.bioinfo.core.DnaSequence;

public class RandomSequenceGenerator {

	private final Random random;
	
	private DnaBase[] bases = new DnaBase[]{A, C, T, G};
	
	public RandomSequenceGenerator(long seed) {
		random = new Random(seed);
	}
	
	public RandomSequenceGenerator() {
		random = new Random();
	}
	
	public DnaSequence generateRandomSequence(int length) {
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<length;i++) {
			sb.append(bases[random.nextInt(4)]);
		}
		return new DnaSequence(sb.toString());
	}
	
}
