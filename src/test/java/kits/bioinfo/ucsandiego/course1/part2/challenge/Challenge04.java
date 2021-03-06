package kits.bioinfo.ucsandiego.course1.part2.challenge;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import kits.bioinfo.core.DnaSequence;
import kits.bioinfo.infrastructure.SequenceReader;
import kits.bioinfo.motif.GreedyMotifFinder;
import kits.bioinfo.util.Timer;

public class Challenge04 {

    /**
     * Implement GREEDYMOTIFSEARCH with pseudocounts. Input: Integers k and t,
     * followed by a collection of strings Dna. Output: A collection of strings
     * BestMotifs resulting from applying GREEDYMOTIFSEARCH(Dna,k,t) with
     * pseudocounts. If at any step you find more than one Profile-most probable
     * k-mer in a given string, use the one occurring first.
     */
    public static void main(String[] args) throws IOException {
        List<DnaSequence> sequences = SequenceReader.readDnaSequencesPerLine("input/dataset_160_9.txt");
        Timer.timed(() -> {
            Set<DnaSequence> motifs = GreedyMotifFinder.findMotifs(sequences, 12);
            System.out.println(motifs);
        });
    }

}
