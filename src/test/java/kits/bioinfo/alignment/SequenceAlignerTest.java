package kits.bioinfo.alignment;

import java.util.Collection;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;

import kits.bioinfo.alignment.aligner.SequenceAligner;
import kits.bioinfo.alignment.aligner.SequenceAligner.AlignmentResult;
import kits.bioinfo.alignment.scorefunction.ScoreFunction;
import kits.bioinfo.core.AminoAcid;
import kits.bioinfo.core.Peptid;
import kits.bioinfo.core.Sequence;

public class SequenceAlignerTest {

	@Test
	public void test1() {
		Sequence<Character> sequence1 = Sequence.of("GCGATC");
		Sequence<Character> sequence2 = Sequence.of("CTGACG");
		SequenceAligner<Character> aligner = new SequenceAligner<>(ScoreFunction.simple());
		Collection<AlignmentResult<Character>> results = aligner.findAllGlobalAlignments(sequence1, sequence2);
		Assert.assertTrue(results.size() == 1);
		
		String expectedAlignment = "GC-GATC-" + "\n" + 
				                   "-CTGA-CG";
		
		Assert.assertTrue(results.iterator().next().toString().contains(expectedAlignment));
	}
	
	@Test
	public void test2() {
		Sequence<AminoAcid> sequence1 = new Peptid("PLEASANTLY").toSequence();
		Sequence<AminoAcid> sequence2 = new Peptid("MEANLY").toSequence();
		SequenceAligner<AminoAcid> aligner = new SequenceAligner<AminoAcid>(ScoreFunction.blosum62(5));
		Collection<AlignmentResult<AminoAcid>> results = aligner.findAllGlobalAlignments(sequence1, sequence2);

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
		Sequence<AminoAcid> sequence1 = new Peptid("PENALTY").toSequence();
		Sequence<AminoAcid> sequence2 = new Peptid("NAL").toSequence();
		SequenceAligner<AminoAcid> aligner = new SequenceAligner<AminoAcid>(ScoreFunction.basic(10, 2, 5));
		AlignmentResult<AminoAcid> result = aligner.findOneLocalAlignment(sequence1, sequence2);
		Assert.assertEquals("NAL\nNAL", printAlignment(result));
	}
	
	@Test
	public void test4() {
		Sequence<AminoAcid> sequence1 = new Peptid("GTAGGCTTAAGGTTAC").toSequence();
		Sequence<AminoAcid> sequence2 = new Peptid("TAGATA").toSequence();
		SequenceAligner<AminoAcid> aligner = new SequenceAligner<AminoAcid>(ScoreFunction.basic(1, 1, 1));
		AlignmentResult<AminoAcid> result = aligner.findOneFittingAlignment(sequence1, sequence2);
		System.out.println(result.score);
		System.out.println(printAlignment(result));
	}

	@Test
	public void test5() {
		Sequence<AminoAcid> sequence1 = new Peptid("ALMAPC").toSequence();
		Sequence<AminoAcid> sequence2 = new Peptid("MAP").toSequence();
		SequenceAligner<AminoAcid> aligner = new SequenceAligner<AminoAcid>(ScoreFunction.basic(1, 1, 1));
		AlignmentResult<AminoAcid> result = aligner.findOneFittingAlignment(sequence1, sequence2);
		System.out.println(result.score);
		System.out.println(printAlignment(result));
	}
	
	@Test
	public void test6() {
		Sequence<AminoAcid> sequence1 = new Peptid("APAPAPAWHEAE").toSequence();
		Sequence<AminoAcid> sequence2 = new Peptid("HEAGAWGHEE").toSequence();
		SequenceAligner<AminoAcid> aligner = new SequenceAligner<AminoAcid>(ScoreFunction.basic(1, 2, 2));
		AlignmentResult<AminoAcid> result = aligner.findOneOverlappingAlignment(sequence1, sequence2);
		System.out.println(result.score);
		System.out.println(printAlignment(result));
	}
	
	private String printAlignment(AlignmentResult<AminoAcid> alignment){
		return printSequence(alignment.sequence1) + "\n" + printSequence(alignment.sequence2);
	}
	
	private String printSequence(Sequence<AminoAcid> sequence){
		return sequence.stream().map(n -> n != null ? n.code1.toString() : "-").collect(Collectors.joining());
	}

}
