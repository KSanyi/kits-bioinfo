package kits.bioinfo.matcher;

import kits.bioinfo.core.DnaSequence;
import kits.bioinfo.util.RandomSequenceGenerator;
import kits.bioinfo.util.Timer;

public class SubSequentMatchersPerformanceTest {

    public static void main(String[] args) {

        RandomSequenceGenerator generator = new RandomSequenceGenerator();
        
        var sequence = generator.generateRandomDnaSequence(10_000_000);
        DnaSequence pattern = generator.generateRandomDnaSequence(15);
        
        Timer.timed(() -> {
            //System.out.println(new SubSequenceMatcher(pattern).matchStartIndexes(sequence));
        });
        
        Timer.timed(() -> {
            //System.out.println(new QuickSubSequenceMatcher(pattern).matchStartIndexes(sequence));
        });
        
        Timer.timed(() -> {
            //System.out.println(new BMSubSequenceMatcher(pattern).matchStartIndexes(sequence));
        });
        
        Timer.timed(() -> {
            System.out.println(new ApproximateSubSequenceMatcher(pattern, 2).matchStartIndexes(sequence));
        });
        
        Timer.timed(() -> {
            System.out.println(new QuickApproximateSubSequenceMatcher(pattern, 2).matchStartIndexes(sequence));
        });

    }

}
