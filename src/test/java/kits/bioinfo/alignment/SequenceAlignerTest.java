package kits.bioinfo.alignment;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;

import kits.bioinfo.alignment.SequenceAligner.AlignmentResult;
import kits.bioinfo.alignment.scorefunction.ScoreFunction;
import kits.bioinfo.core.AminoAcid;
import kits.bioinfo.core.Sequence;

public class SequenceAlignerTest {

	@Test
	public void test1() {
		Sequence<Character> sequence1 = new Sequence<>(Arrays.asList('G', 'C', 'G', 'A', 'T', 'C'));
		Sequence<Character> sequence2 = new Sequence<>(Arrays.asList('C', 'T', 'G', 'A', 'C', 'G'));
		SequenceAligner<Character> aligner = new SequenceAligner<>(ScoreFunction.simple());
		Collection<AlignmentResult<Character>> results = aligner.findAllAlignments(sequence1, sequence2);
		Assert.assertTrue(results.size() == 1);
		
		String expectedAlignment = "GC-GATC-" + "\n" + 
				                   "-CTGA-CG";
		
		Assert.assertTrue(results.iterator().next().toString().contains(expectedAlignment));
		
	}
	
	@Test
	public void test2() {
		Sequence<AminoAcid> sequence1 = new Sequence<>("PLEASANTLY".chars().mapToObj(c -> AminoAcid.of((char)c)).collect(Collectors.toList()));
		Sequence<AminoAcid> sequence2 = new Sequence<>("MEANLY".chars().mapToObj(c -> AminoAcid.of((char)c)).collect(Collectors.toList()));
		SequenceAligner<AminoAcid> aligner = new SequenceAligner<AminoAcid>(ScoreFunction.blosum62(5));
		Collection<AlignmentResult<AminoAcid>> results = aligner.findAllAlignments(sequence1, sequence2);

		Assert.assertEquals(8, results.iterator().next().score);
		
		String expectedAlignment = "PLEASANTLY" + "\n" + 
								   "-MEA--N-LY";
		
		Assert.assertTrue(results.stream()
				.map(a -> printAlignment(a))
				.filter(print -> print.equals(expectedAlignment))
				.findAny()
				.isPresent());
		
	}
	
	@Test
	public void test3() {
		Sequence<AminoAcid> sequence1 = new Sequence<>("PENALTY".chars().mapToObj(c -> AminoAcid.of((char)c)).collect(Collectors.toList()));
		Sequence<AminoAcid> sequence2 = new Sequence<>("NAL".chars().mapToObj(c -> AminoAcid.of((char)c)).collect(Collectors.toList()));
		SequenceAligner<AminoAcid> aligner = new SequenceAligner<AminoAcid>(ScoreFunction.basic(10, 2, 5));
		AlignmentResult<AminoAcid> result = aligner.findOneLocalAlignment(sequence1, sequence2);

		System.out.println(printAlignment(result));
	}
	
	private String printAlignment(AlignmentResult<AminoAcid> alignment){
		return printSequence(alignment.sequence1) + "\n" + printSequence(alignment.sequence2);
	}
	
	private String printSequence(Sequence<AminoAcid> sequence){
		return sequence.stream().map(n -> n != null ? n.code1.toString() : "-").collect(Collectors.joining());
	}

}
