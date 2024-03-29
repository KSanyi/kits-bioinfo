package kits.bioinfo.ucsandiego.course1.part2.challenge;

import java.io.IOException;
import java.util.List;

import kits.bioinfo.core.DnaSequence;
import kits.bioinfo.infrastructure.SequenceReader;
import kits.bioinfo.motif.Motifs;
import kits.bioinfo.motif.RandomizedMotifFinder;
import kits.bioinfo.util.Timer;

public class Challenge05 {

    /**
     * Implement RANDOMIZEDMOTIFSEARCH. Input: Integers k and t, followed by a
     * collection of strings Dna. Output: A collection BestMotifs resulting from
     * running RANDOMIZEDMOTIFSEARCH(Dna, k, t) 1,000 times. Remember to use
     * pseudocounts!
     */
    public static void main(String[] args) throws IOException {
        List<DnaSequence> sequences = SequenceReader.readDnaSequencesPerLine("input/dataset_163_4.txt");
        Timer.timed(() -> {
            List<DnaSequence> motifs = new RandomizedMotifFinder(20).findMotifs(sequences, 15);
            System.out.println("Score: " + Motifs.score(motifs));
            System.out.println(motifs);
        });
        
    }

}
