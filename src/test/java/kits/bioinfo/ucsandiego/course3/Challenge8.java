package kits.bioinfo.ucsandiego.course3;

import java.util.List;

import org.junit.Assert;

import kits.bioinfo.alignment.aligner.SequenceAligner;
import kits.bioinfo.alignment.aligner.SequenceAligner.AlignmentResult;
import kits.bioinfo.alignment.scorefunction.ScoreFunction;
import kits.bioinfo.core.DnaBase;
import kits.bioinfo.core.DnaSequence;
import kits.bioinfo.infrastructure.SequenceReader;

public class Challenge8 {

	/**
	 * Code Challenge: Solve the Fitting Alignment Problem.
     * Input: Two nucleotide strings v and w, where v has length at most 1000 and w has length at most 100.
     * Output: A highest-scoring fitting alignment between v and w. Use the simple scoring method in which
     * matches count +1 and both the mismatch and indel penalties are 1.
	 */
	public static void main(String[] args) throws Exception {
		List<DnaSequence> sequences = SequenceReader.readDnaSequencesPerLine("input/dataset_248_5.txt");
		
		SequenceAligner<DnaBase> aligner = new SequenceAligner<DnaBase>(ScoreFunction.basic(1,  1,  1));
		AlignmentResult<DnaBase> alignment = aligner.findOneFittingAlignment(sequences.get(0).toSequence(), sequences.get(1).toSequence());
		
		System.out.println(alignment.score);
		System.out.println(alignment);
		
		Assert.assertEquals(18, alignment.score);
		Assert.assertEquals("CC-CTA-G-TCAC---GTCTTGAGCAAAA-TAAT--CCCAAGGGTATGT-A-AAC-AA-AC-CCACA-AGTCA-A-CGAAT-GCAT-CTCTGCTAA--TGT--T",
				alignment.sequence1.toString());
		Assert.assertEquals("CCTCTACGTTCGCCAAG-CTT-ACCAATACTTATGACCCAAGATT-TGTTATAACGAATACTTGGCATA-TCATATCGAAAAGGAGAC-C-GTTAGGGTGTCAT",
				alignment.sequence2.toString());
	}

}
