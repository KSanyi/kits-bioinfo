package kits.bioinfo.ucsandiego.course1;

import java.io.IOException;
import java.util.List;

import kits.bioinfo.core.Sequence;
import kits.bioinfo.infrastructure.SequenceReader;
import kits.bioinfo.motif.GreedyMotifFinder;

public class Challenge8 {

	/**
	 * Implement GREEDYMOTIFSEARCH with pseudocounts.
     Input: Integers k and t, followed by a collection of strings Dna.
     Output: A collection of strings BestMotifs resulting from applying GREEDYMOTIFSEARCH(Dna,k,t) with
     pseudocounts. If at any step you find more than one Profile-most probable k-mer in a given string,
     use the one occurring first.
	 */
	public static void main(String[] args) throws IOException {
		List<Sequence> sequences = SequenceReader.readPerLine("input/dataset_160_9.txt");
		List<Sequence> motifs = new GreedyMotifFinder().findMotifs(sequences, 12);
		System.out.println(motifs);
	}
	
}
