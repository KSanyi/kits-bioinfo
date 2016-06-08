package kits.bioinfo;

import kits.bioinfo.core.DnaSequence;

public class Main {

	public static void main(String[] args) {

		System.out.println(new DnaSequence("TCTTGATCA").reverseComplement());

	}

}
