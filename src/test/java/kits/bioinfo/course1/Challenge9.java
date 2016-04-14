package kits.bioinfo.course1;

import java.io.IOException;
import java.util.List;

import kits.bioinfo.base.Sequence;
import kits.bioinfo.infrastructure.SequenceReader;
import kits.bioinfo.motif.Motifs;
import kits.bioinfo.motif.RandomizedMotifFinder;

public class Challenge9 {

	/**
	 *Implement RANDOMIZEDMOTIFSEARCH.
     Input: Integers k and t, followed by a collection of strings Dna.
     Output: A collection BestMotifs resulting from running RANDOMIZEDMOTIFSEARCH(Dna, k, t) 1,000
     times. Remember to use pseudocounts!
	 */
	public static void main(String[] args) throws IOException {
		//List<Sequence> sequences = SequenceReader.readFromLines("input/dataset_161_5.txt");
		List<Sequence> sequences = SequenceReader.readFromLines("input/dataset_163_4.txt");
		
		List<Sequence> motifs = new RandomizedMotifFinder(20).findMotifs(sequences, 15);
		System.out.println("Score: " + Motifs.score(motifs));
		System.out.println(motifs);
	}
	
}
