package kits.bioinfo.ucsandiego.course1.part2.challenge;

import java.io.IOException;
import java.util.List;

import kits.bioinfo.core.DnaSequence;
import kits.bioinfo.infrastructure.SequenceReader;
import kits.bioinfo.motif.GreedyMotifFinderOriginal;
import kits.bioinfo.util.Timer;

public class Challenge03 {

    /**
     * Implement GREEDYMOTIFSEARCH. Input: Integers k and t, followed by a
     * collection of strings Dna. Output: A collection of strings BestMotifs
     * resulting from applying GREEDYMOTIFSEARCH(Dna,k,t). If at any step you
     * find more than one Profile-most probable k-mer in a given string, use the
     * one occurring first.
     */
    public static void main(String[] args) throws IOException {
        List<DnaSequence> sequences = SequenceReader.readDnaSequencesPerLine("input/dataset_159_5.txt");
        Timer.timed(() -> {
            List<DnaSequence> motifs = GreedyMotifFinderOriginal.findMotifs(sequences, 12);
            System.out.println(motifs);
        });
        
    }

}
