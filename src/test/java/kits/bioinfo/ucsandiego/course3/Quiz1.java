package kits.bioinfo.ucsandiego.course3;

import java.util.Collection;

import kits.bioinfo.alignment.LongestCommonSubSequenceFinder;
import kits.bioinfo.core.DnaBase;
import kits.bioinfo.core.DnaSequence;
import kits.bioinfo.core.Sequence;

public class Quiz1 {

	public static void main(String[] args) {
		question1();
		question2();
	}
	
	private static void question1() {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + ": ");
		
		DnaSequence sequence1 = new DnaSequence("GCGATC");
		DnaSequence sequence2 = new DnaSequence("CTGACG");
		Collection<Sequence<DnaBase>> sequences = LongestCommonSubSequenceFinder.findAllSequences(sequence1, sequence2);
		System.out.println(sequences);
	}
	
	private static void question2() {
		System.out.println("\n" + Thread.currentThread().getStackTrace()[1].getMethodName() + ": ");
		
		System.out.println(combinations(23));
	}
	
	private static int combinations(int n){
		if(n < 5) return 1;
		else return combinations(n-2) + combinations(n-3); 
	}

}
