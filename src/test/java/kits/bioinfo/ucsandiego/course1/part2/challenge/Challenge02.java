package kits.bioinfo.ucsandiego.course1.part2.challenge;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import kits.bioinfo.core.DnaSequence;
import kits.bioinfo.infrastructure.SequenceReader;
import kits.bioinfo.motif.NaiveMedianStringFinder;

public class Challenge02 {

    /**
     * Implement MEDIANSTRING. Input: An integer k, followed by a collection of
     * strings Dna. Output: A k-mer Pattern that minimizes d(Pattern, Dna) among
     * all k-mers Pattern. (If there are multiple such strings Pattern, then you
     * may return any one.)
     * 
     */
    public static void main(String[] args) throws IOException {
        Set<DnaSequence> sequences = new HashSet<>(SequenceReader.readDnaSequencesPerLine("input/dataset_158_9.txt"));
        Set<DnaSequence> medianStrings = NaiveMedianStringFinder.findMedianStrings(sequences, 6);
        System.out.println(medianStrings);
    }

}
