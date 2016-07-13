package kits.bioinfo.ucsandiego.course3;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.junit.Assert;

import kits.bioinfo.alignment.aligner.SequenceAligner;
import kits.bioinfo.alignment.aligner.SequenceAligner.AlignmentResult;
import kits.bioinfo.alignment.scorefunction.ScoreFunction;
import kits.bioinfo.core.Sequence;

public class Challenge9 {

	/**
	 * Code Challenge: Solve the Overlap Alignment Problem.
     * Input: Two strings v and w, each of length at most 1000.
     * Output: The score of an optimal overlap alignment of v and w, followed by an alignment of a suffix v' of
     * v and a prefix w' of w achieving this maximum score. Use an alignment score in which matches count
     * +1 and both the mismatch and indel penalties are 2.
	 */
	public static void main(String[] args) throws Exception {
		List<String> lines = Files.readAllLines(Paths.get("input/dataset_248_7.txt"));
		
		SequenceAligner<Character> aligner = new SequenceAligner<Character>(ScoreFunction.basic(1,  2,  2));
		AlignmentResult<Character> alignment = aligner.findOneOverlappingAlignment(Sequence.of(lines.get(0)), Sequence.of(lines.get(1)));
		
		System.out.println(alignment.score);
		System.out.println(alignment);
		
		Assert.assertEquals(21, alignment.score);
		Assert.assertEquals("CAT-GC-AAGCTATC-CGTCTTCTTTAGGGTCTGCATTAAGCACCTCATAT-AGTTGAAGCCTCAGGGCGGGTCTTGG-AA-GCAAATTTGGCGTGA-ATTCT---AATCAGCCCGTGTCATGCCGATG-AAGTCCAGAACTCAGAGATGA-ATACGT-TGTTTGCGGTTTCAA-TTCTTTGAAGCATATAGAATGATGGCGA-TTAAGGGGCCCTCTAC-GGGCGGCTCT-GTGATTCTGGACAAAGAAACCTACACATC",
				alignment.sequence1.toString());
		Assert.assertEquals("CATCGGTAA-TTACCTCGT-TTCTTCGGGGT-TCCA-AAAGCATC-CATATTAGTT-AAGCATCGGGGTGAGT-TCGGTAAGGCAAAGTTGG--TGACATGATGGGAATCAGCCCATGTGAGGC-GAAGCAAA-CTAGAACCCCGGGGTGAAATACGTCTGTTCG-GGT-TCTACTT-TTT-AAGCA-ATCGACTGATAGCGACTTACGGGACTCTC-ACCGGTCAG-TCTTGTGGCCCTGGACAAAGGCAC--ACCCA-C",
				alignment.sequence2.toString());
	}

}
