package kits.bioinfo.johnshopkins.assignments;

import java.io.IOException;
import java.util.List;

import kits.bioinfo.core.DnaSequence;
import kits.bioinfo.infrastructure.SequenceReader;
import kits.bioinfo.matcher.ApproximateSubSequenceMatcher;
import kits.bioinfo.matcher.BMSubSequenceMatcher;
import kits.bioinfo.matcher.IndexBasedApproximateSubSequenceMatcher;
import kits.bioinfo.matcher.NaiveSubSequenceMatcher;

public class Assignment2 {

	public static void main(String[] args) throws IOException {
		DnaSequence humanAluSequence = SequenceReader.readFromFastaFile("input/chr1.GRCh38.excerpt.fasta");
		
		question1And2(humanAluSequence);
		question3(humanAluSequence);
		question4(humanAluSequence);
		question5(humanAluSequence);
		//question6(lambdaGenome);
		//question7();
	}
	
	private static void question1And2(DnaSequence humanAluSequence) {
		System.out.println("1-2: ");
		DnaSequence pattern = new DnaSequence("GGCGCGGTGGCTCACGCCTGTAATCCCAGCACTTTGGGAGGCCGAGG");
		System.out.println(new NaiveSubSequenceMatcher(pattern).matchCount(humanAluSequence));
	}
	
	private static void question3(DnaSequence humanAluSequence) {
		System.out.println("3: ");
		//Sequence pattern = new Sequence("GGCGCGGTGGCTCACGCCTGTAATCCCAGCACTTTGGGAGGCCGAGG");
		//System.out.println(new BMSubSequenceMatcher(pattern).matchCount(humanAluSequence));
		
		DnaSequence pattern = new DnaSequence("ACCTGC");
		//Sequence pattern = new Sequence("needle");
		DnaSequence text = new DnaSequence("ACCTGCNACCTNANNTGCNACCTGC");
		//Sequence text = new Sequence("needle need noodle needle");
		System.out.println(new BMSubSequenceMatcher(pattern).matchStartIndexes(text));
	}
	
	private static void question4(DnaSequence humanAluSequence) {
		DnaSequence pattern = new DnaSequence("GGCGCGGTGGCTCACGCCTGTAAT");
		int occurances = new ApproximateSubSequenceMatcher(pattern, 2).matchCount(humanAluSequence);
		System.out.println("4: " + occurances);
	}
	
	private static void question5(DnaSequence humanAluSequence) {
		DnaSequence pattern = new DnaSequence("GGCGCGGTGGCTCACGCCTGTAAT");
		int count = new IndexBasedApproximateSubSequenceMatcher(humanAluSequence, 8, 2).matchCount(pattern);
		System.out.println("5: " + count);
	}
	
	private static void question6(DnaSequence humanAluSequence) {
		DnaSequence pattern = new DnaSequence("GGCGCGGTGGCTCACGCCTGTAAT");
		List<Integer> indexes = new ApproximateSubSequenceMatcher(pattern, 2).matchStartIndexes(humanAluSequence);
		System.out.println("5: " + indexes.stream().mapToInt(i -> i).min());
	}
	
}
