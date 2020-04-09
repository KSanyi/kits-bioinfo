package kits.bioinfo.ucsandiego.course1.part2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import kits.bioinfo.core.DnaSequence;
import kits.bioinfo.motif.GibbsSampler;
import kits.bioinfo.motif.GreedyMotifFinder;
import kits.bioinfo.motif.Motifs;
import kits.bioinfo.motif.NaiveMedianStringFinder;
import kits.bioinfo.motif.NaiveMotifFinder;
import kits.bioinfo.util.RandomSequenceGenerator;
import kits.bioinfo.util.Timer;

@SuppressWarnings("unused")
public class SubtleMotifProblem {

    private static final int SEQ_LENGTH = 600;
    
    public static void main(String[] args) {

        DnaSequence motif = new DnaSequence("AAAAAAAACCCCCCCC");
        List<DnaSequence> sequences = createSequences(motif);
        
        // runNaiveMedianStringFinder(sequences, motif.length());
        runGreedyMotifFinder(sequences, motif.length());
        // runNaiveMotifFinder(sequences, motif.length());
        runGibbsSampler(sequences, motif.length());
    }
    
    private static void runNaiveMedianStringFinder(List<DnaSequence> sequences, int k) {
        System.out.println("Naive median string finder");
        Timer.timed(() -> {
            Set<DnaSequence> motifs = NaiveMedianStringFinder.findMedianStrings(sequences, k);
            System.out.println("Score: " + Motifs.score(motifs));
            System.out.println(motifs);
        });
    }
    
    private static void runGreedyMotifFinder(List<DnaSequence> sequences, int k) {
        System.out.println("Greedy Motif Finder");
        Timer.timed(() -> {
            Set<DnaSequence> motifs = GreedyMotifFinder.findMotifs(sequences, k);
            System.out.println("Score: " + Motifs.score(motifs));
            System.out.println(motifs);
        });
    }
    
    private static void runNaiveMotifFinder(List<DnaSequence> sequences, int k) {
        System.out.println("Naive Motif Finder");
        Timer.timed(() -> {
            Set<DnaSequence> motifs = NaiveMotifFinder.findMotifs(sequences, k, 1);
            System.out.println("Score: " + Motifs.score(motifs));
            System.out.println(motifs);
        });
    }
    
    private static void runGibbsSampler(List<DnaSequence> sequences, int k) {
        System.out.println("Gibbs Sampler");
        Timer.timed(() -> {
            Set<DnaSequence> motifs = new GibbsSampler(50).findMotifs(sequences, k, 2000);
            System.out.println("Score: " + Motifs.score(motifs));
            System.out.println(motifs);
        });
    }
    
    private static List<DnaSequence> createSequences(DnaSequence motif) {
        RandomSequenceGenerator randomSequenceGenerator = new RandomSequenceGenerator();
        Random random = new Random();
        
        List<DnaSequence> sequences = new ArrayList<>();
        for(int i=0;i<10;i++) {
            DnaSequence sequence = randomSequenceGenerator.generateRandomDnaSequence(SEQ_LENGTH);
            int position = random.nextInt(SEQ_LENGTH - motif.length());
            sequence = sequence.implantSequence(motif, position);
            sequences.add(sequence);
        }
        
        return sequences;
    }

}
