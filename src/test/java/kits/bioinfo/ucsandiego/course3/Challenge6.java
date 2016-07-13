package kits.bioinfo.ucsandiego.course3;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import kits.bioinfo.alignment.aligner.SequenceAligner;
import kits.bioinfo.alignment.aligner.SequenceAligner.AlignmentResult;
import kits.bioinfo.alignment.scorefunction.ScoreFunction;
import kits.bioinfo.core.AminoAcid;
import kits.bioinfo.core.Peptid;
import kits.bioinfo.core.Sequence;

public class Challenge6 {

	/**
	 * Code Challenge: Solve the Local Alignment Problem.
     * Input: Two protein strings written in the single-letter amino acid alphabet.
     * Output: The maximum score of a local alignment of the strings, followed by a local alignment of these
     * strings achieving the maximum score. Use the PAM250 scoring matrix and indel penalty σ = 5.
	 */
	public static void main(String[] args) throws Exception {
		List<String> lines = Files.readAllLines(Paths.get("input/dataset_247_9.txt"));
		Peptid peptid1 = new Peptid(lines.get(0));
		Peptid peptid2 = new Peptid(lines.get(1));
		
		SequenceAligner<AminoAcid> aligner = new SequenceAligner<AminoAcid>(ScoreFunction.pam250(5));
		AlignmentResult<AminoAcid> alignment = aligner.findOneLocalAlignment(peptid1.toSequence(), peptid2.toSequence());
		
		System.out.println(alignment.score);
		System.out.println(printAlignment(alignment));
	}
	
	private static String printAlignment(AlignmentResult<AminoAcid> alignment){
		return printSequence(alignment.sequence1) + "\n" + printSequence(alignment.sequence2);
	}
	
	private static String printSequence(Sequence<AminoAcid> sequence){
		return sequence.stream().map(n -> n != null ? n.code1.toString() : "-").collect(Collectors.joining());
	}

}
