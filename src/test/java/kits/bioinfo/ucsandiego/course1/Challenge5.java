package kits.bioinfo.ucsandiego.course1;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import kits.bioinfo.core.DnaSequence;
import kits.bioinfo.infrastructure.SequenceReader;
import kits.bioinfo.motif.MotifFinder;

public class Challenge5 {

    /**
     * Implement MOTIFENUMERATION (reproduced below). Input: Integers k and d,
     * followed by a collection of strings Dna. Output: All (k, d)-motifs in
     * Dna.
     * 
     */
    public static void main(String[] args) throws IOException {
        List<DnaSequence> sequences = SequenceReader.readDnaSequencesPerLine("input/dataset_156_7.txt");
        Set<DnaSequence> motifs = new MotifFinder().findMotifs(sequences, 5, 1);
        System.out.println(motifs);
    }

}
