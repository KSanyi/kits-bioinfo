package kits.bioinfo.core;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public enum RnaBase {

	A, C, U, G, N;
	
	public static RnaBase[] bases() {
		return new RnaBase[]{A, C, U, G};
	}
	
	public static Set<RnaBase> others(RnaBase base) {
		Set<RnaBase> bases = new HashSet<>(Arrays.asList(values()));
		bases.remove(N);
		bases.remove(base);
		return Collections.unmodifiableSet(bases);
	}
	
	public static RnaBase of(char c) {
		switch(Character.toUpperCase(c)) {
			case 'A': return A;
			case 'U': return U;
			case 'C': return C;
			case 'G': return G;
			case 'N': return N;
			default: throw new IllegalArgumentException("Can not create RNA base from character " + c);
		}
	}
	
}
