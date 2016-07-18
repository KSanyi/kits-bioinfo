package kits.bioinfo.ucsandiego.course3;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;

import kits.bioinfo.alignment.aligner.FittingSequenceAligner;
import kits.bioinfo.alignment.aligner.SequenceAligner;
import kits.bioinfo.alignment.aligner.SequenceAligner.AlignmentResult;
import kits.bioinfo.alignment.scorefunction.ScoreFunction;
import kits.bioinfo.core.DnaBase;
import kits.bioinfo.core.DnaSequence;
import kits.bioinfo.core.Sequence;
import kits.bioinfo.infrastructure.SequenceReader;

public class Challenge8 {

	/**
	 * Code Challenge: Solve the Fitting Alignment Problem.
     * Input: Two nucleotide strings v and w, where v has length at most 1000 and w has length at most 100.
     * Output: A highest-scoring fitting alignment between v and w. Use the simple scoring method in which
     * matches count +1 and both the mismatch and indel penalties are 1.
	 */
	@Test
	public void test() throws Exception {
		List<DnaSequence> sequences = SequenceReader.readDnaSequencesPerLine("input/dataset_248_5.txt");
		
		SequenceAligner<DnaBase> aligner = new SequenceAligner<DnaBase>(ScoreFunction.basic(1,  1,  1));
		AlignmentResult<DnaBase> alignment = aligner.findOneFittingAlignment(sequences.get(0).toSequence(), sequences.get(1).toSequence());
		
		System.out.println(alignment.score);
		System.out.println(printSequence(alignment.sequence1));
		System.out.println(printSequence(alignment.sequence2));
		
		Assert.assertEquals(22, alignment.score);
		Assert.assertEquals("TTTTCAGACTCGATGTCTGC-ATTTCTCGGATAAGTTAC-ACTCA--TGG-AT-GGATGAGCTCTTATA-GGCTGGTTG-ACTTGAAGA-CTAGTCCG",
				printSequence(alignment.sequence1));
		Assert.assertEquals("CTCTCA-CCTC-TTTTCT-CTATTACT--AATTAG-GACGAC-CACCTGGTCTCGG--GAG-GC-TA-AGGGCTCGTGGTA-ATGAGGACCTAGT-CG",
				printSequence(alignment.sequence2));
	}
	
	@Test
	public void test2() throws Exception {
		List<DnaSequence> sequences = SequenceReader.readDnaSequencesPerLine("input/dataset_248_5.txt");
		
		FittingSequenceAligner<DnaBase> aligner = new FittingSequenceAligner<DnaBase>(ScoreFunction.basic(1,  1,  1));
		AlignmentResult<DnaBase> alignment = aligner.findOneAlignment(sequences.get(0).toSequence(), sequences.get(1).toSequence());
		
		System.out.println(alignment.score);
		System.out.println(printSequence(alignment.sequence1));
		System.out.println(printSequence(alignment.sequence2));
		
		Assert.assertEquals(22, alignment.score);
		Assert.assertEquals("TTTTCAGACTCGATGTCTGC-ATTTCTCGGATAAGTTAC-ACTCA--TGG-AT-GGATGAGCTCTTATA-GGCTGGTTG-ACTTGAAGA-CTAGTCCG",
				printSequence(alignment.sequence1));
		Assert.assertEquals("CTCTCA-CCTC-TTTTCT-CTATTACT--AATTAG-GACGAC-CACCTGGTCTCGG--GAG-GC-TA-AGGGCTCGTGGTA-ATGAGGACCTAGT-CG",
				printSequence(alignment.sequence2));
	}
	
	private static String printSequence(Sequence<DnaBase> sequence){
		return sequence.stream().map(n -> n != null ? n.toString() : "-").collect(Collectors.joining());
	}

}
