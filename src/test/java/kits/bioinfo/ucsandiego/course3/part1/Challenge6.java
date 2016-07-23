package kits.bioinfo.ucsandiego.course3.part1;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;

import kits.bioinfo.alignment.aligner.AlignmentUtil;
import kits.bioinfo.alignment.aligner.LocalSequenceAligner;
import kits.bioinfo.alignment.aligner.AlignmentResult;
import kits.bioinfo.alignment.scorefunction.ScoreFunction;
import kits.bioinfo.core.AminoAcid;
import kits.bioinfo.core.Peptid;
import kits.bioinfo.core.Sequence;

public class Challenge6 {

	/**
	 * Code Challenge: Solve the Local Alignment Problem. Input: Two protein
	 * strings written in the single-letter amino acid alphabet. Output: The
	 * maximum score of a local alignment of the strings, followed by a local
	 * alignment of these strings achieving the maximum score. Use the PAM250
	 * scoring matrix and indel penalty = 5.
	 */
	@Test
	public void test() throws Exception {
		List<String> lines = Files.readAllLines(Paths.get("input/dataset_247_9.txt"));
		Peptid peptid1 = new Peptid(lines.get(0));
		Peptid peptid2 = new Peptid(lines.get(1));

		LocalSequenceAligner<AminoAcid> aligner = new LocalSequenceAligner<AminoAcid>(ScoreFunction.pam250(5));
		AlignmentResult<AminoAcid> alignment = aligner.findOneAlignment(peptid1.toSequence(), peptid2.toSequence());

		System.out.println(alignment.score);
		System.out.println(printSequence(alignment.sequence1));
		System.out.println(printSequence(alignment.sequence2));

		AlignmentUtil.checkAlignment(alignment, ScoreFunction.pam250(5));

		List<String> resultLines = Files.readAllLines(Paths.get("output/output_247_9.txt"));
		Assert.assertEquals(resultLines.get(0), String.valueOf(alignment.score));
		Assert.assertEquals(resultLines.get(1), printSequence(alignment.sequence1));
		Assert.assertEquals(resultLines.get(2), printSequence(alignment.sequence2));
	}

	private static String printSequence(Sequence<AminoAcid> sequence) {
		return sequence.stream().map(n -> n != null ? n.code1.toString() : "-").collect(Collectors.joining());
	}

}
