package kits.bioinfo.ucsandiego.course1.part2.challenge;

import java.io.IOException;
import java.util.List;

import kits.bioinfo.core.DnaSequence;
import kits.bioinfo.infrastructure.SequenceReader;
import kits.bioinfo.motif.GibbsSampler;
import kits.bioinfo.motif.Motifs;
import kits.bioinfo.util.Timer;

public class Challenge06 {

    /**
     * Implement GIBBSSAMPLER. Input: Integers k, t, and N, followed by a
     * collection of strings Dna. Output: The strings BestMotifs resulting from
     * running GIBBSSAMPLER(Dna, k, t, N) with 20 random starts. Remember to use
     * pseudocounts!
     */
    public static void main(String[] args) throws IOException {
        List<DnaSequence> sequences = SequenceReader.readDnaSequencesPerLine("input/dataset_163_4.txt");
        Timer.timed(() -> {
            List<DnaSequence> motifs = new GibbsSampler(50).findMotifs(sequences, 15, 2000);
            System.out.println("Score: " + Motifs.score(motifs));
            System.out.println(motifs);
        });
    }

}
