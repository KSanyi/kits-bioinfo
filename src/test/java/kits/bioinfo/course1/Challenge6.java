package kits.bioinfo.course1;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import kits.bioinfo.base.Sequence;
import kits.bioinfo.infrastructure.SequenceReader;
import kits.bioinfo.motif.MedianStringFinder;

public class Challenge6 {

	/**
	 * Implement MEDIANSTRING.
     Input: An integer k, followed by a collection of strings Dna.
     Output: A k-mer Pattern that minimizes d(Pattern, Dna) among all k-mers Pattern. (If there are
     multiple such strings Pattern, then you may return any one.)
	 * 
	 */
	public static void main(String[] args) throws IOException {
		Set<Sequence> sequences = new HashSet<>(SequenceReader.readFromLines("input/dataset_158_9.txt"));
		Set<Sequence> medianStrings = new MedianStringFinder().findMedianStrings(sequences, 6);
		System.out.println(medianStrings);
	}

}