package kits.bioinfo.core;

import java.util.LinkedList;
import java.util.List;

public class RnaPolymerase {

	public RnaSequence transcribeDnaSequence(DnaSequence dnaSequence) {
		List<RnaBase> rnaBases = new LinkedList<>();
		for(int i=0;i<dnaSequence.length();i++){
			DnaBase dnaBase = dnaSequence.position(i);
			RnaBase rnaBase = transcribeBase(dnaBase);
			rnaBases.add(rnaBase);
		}
		return new RnaSequence(rnaBases);
	}
	
	private RnaBase transcribeBase(DnaBase dnaBase) {
		switch(dnaBase) {
			case A: return RnaBase.A;
			case C: return RnaBase.C;
			case T: return RnaBase.U;
			case G: return RnaBase.G;
			case N: return RnaBase.N;
			default: throw new RuntimeException("Illegal DNA base: " + dnaBase);
		}
	}
	
}
