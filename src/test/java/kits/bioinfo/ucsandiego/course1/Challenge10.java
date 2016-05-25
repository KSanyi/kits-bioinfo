package kits.bioinfo.ucsandiego.course1;

import java.io.IOException;
import java.util.List;

import kits.bioinfo.base.Sequence;
import kits.bioinfo.infrastructure.SequenceReader;
import kits.bioinfo.motif.GibbsSampler;
import kits.bioinfo.motif.Motifs;

public class Challenge10 {

	/**
	 *Implement GIBBSSAMPLER.
     Input: Integers k, t, and N, followed by a collection of strings Dna.
     Output: The strings BestMotifs resulting from running GIBBSSAMPLER(Dna, k, t, N) with
     20 random starts. Remember to use pseudocounts!
	 */
	public static void main(String[] args) throws IOException {
		long start = System.currentTimeMillis();
		List<Sequence> sequences = SequenceReader.readPerLine("input/dataset_163_4.txt");
		
		List<Sequence> motifs = new GibbsSampler(50).findMotifs(sequences, 15, 2000);
		
		long end = System.currentTimeMillis();
		
		System.out.println("Score: " + Motifs.score(motifs));
		System.out.println(motifs);
		System.out.println("Calculation took " + (end-start)/1000 + " seconds");
		
	}
	
}
