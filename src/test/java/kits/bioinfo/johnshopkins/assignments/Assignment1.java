package kits.bioinfo.johnshopkins.assignments;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import kits.bioinfo.core.DnaBase;
import kits.bioinfo.core.DnaSequence;
import kits.bioinfo.infrastructure.SequenceReader;
import kits.bioinfo.matcher.ApproximateSubSequenceMatcher;
import kits.bioinfo.matcher.SubSequenceMatcher;
import kits.bioinfo.util.FrequencyMap;

public class Assignment1 {

	public static void main(String[] args) throws IOException {
		DnaSequence lambdaGenome = SequenceReader.readFromFastaFile("input/lambda_virus.fa");

		question1(lambdaGenome);
		question2(lambdaGenome);
		question3(lambdaGenome);
		question4(lambdaGenome);
		question5(lambdaGenome);
		question6(lambdaGenome);
		question7();
	}

	private static void question1(DnaSequence lambdaGenome) {
		DnaSequence pattern = new DnaSequence("AGGT");
		int count = new SubSequenceMatcher(pattern).matchCount(lambdaGenome);
		int reverseComplementCount = new SubSequenceMatcher(pattern.reverseComplement()).matchCount(lambdaGenome);
		System.out.println("1: " + (count + reverseComplementCount));
	}

	private static void question2(DnaSequence lambdaGenome) {
		DnaSequence pattern = new DnaSequence("TTAA");
		int count = new SubSequenceMatcher(pattern).matchCount(lambdaGenome);
		System.out.println("2: " + count);
	}

	private static void question3(DnaSequence lambdaGenome) {
		DnaSequence pattern = new DnaSequence("ACTAAGT");
		List<Integer> indexes = new SubSequenceMatcher(pattern).matchStartIndexes(lambdaGenome);
		List<Integer> reverseComplementIndexes = new SubSequenceMatcher(pattern.reverseComplement()).matchStartIndexes(lambdaGenome);

		List<Integer> allIndexes = new LinkedList<>();
		allIndexes.addAll(indexes);
		allIndexes.addAll(reverseComplementIndexes);

		System.out.println("3: " + allIndexes.stream().mapToInt(i -> i).min());
	}

	private static void question4(DnaSequence lambdaGenome) {
		DnaSequence pattern = new DnaSequence("AGTCGA");
		List<Integer> indexes = new SubSequenceMatcher(pattern).matchStartIndexes(lambdaGenome);
		List<Integer> reverseComplementIndexes = new SubSequenceMatcher(pattern.reverseComplement()).matchStartIndexes(lambdaGenome);

		List<Integer> allIndexes = new LinkedList<>();
		allIndexes.addAll(indexes);
		allIndexes.addAll(reverseComplementIndexes);

		System.out.println("4: " + allIndexes.stream().mapToInt(i -> i).min());
	}

	private static void question5(DnaSequence lambdaGenome) {
		DnaSequence pattern = new DnaSequence("TTCAAGCC");
		int count = new ApproximateSubSequenceMatcher(pattern, 2).matchCount(lambdaGenome);
		System.out.println("5: " + count);
	}

	private static void question6(DnaSequence lambdaGenome) {
		DnaSequence pattern = new DnaSequence("AGGAGGTT");
		List<Integer> indexes = new ApproximateSubSequenceMatcher(pattern, 2).matchStartIndexes(lambdaGenome);
		System.out.println("5: " + indexes.stream().mapToInt(i -> i).min());
	}

	private static void question7() throws IOException {
		List<DnaSequence> sequences = SequenceReader.readFromFastaQFile("input/ERR037900_1.first1000.fastq");

		int length = sequences.get(0).length();

		for (int index = 0; index < length; index++) {
			FrequencyMap<DnaBase> freqs = new FrequencyMap<>();
			for (DnaSequence sequence : sequences) {
				freqs.put(sequence.position(index));
			}
			System.out.println(index + ": " + freqs);
		}
	}

}
