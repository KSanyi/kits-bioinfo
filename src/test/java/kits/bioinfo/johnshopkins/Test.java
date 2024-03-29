package kits.bioinfo.johnshopkins;

import java.util.Random;

import kits.bioinfo.core.DnaSequence;
import kits.bioinfo.matcher.BMSubSequenceMatcher;
import kits.bioinfo.matcher.QuickSubSequenceMatcher;
import kits.bioinfo.util.RandomSequenceGenerator;

public class Test {

    public static void main(String[] args) {
        Random random = new Random();
        RandomSequenceGenerator generator = new RandomSequenceGenerator();
        int patternLength = random.nextInt(50) + 1;
        DnaSequence pattern = generator.generateRandomDnaSequence(patternLength);
        DnaSequence text = generator.generateRandomDnaSequence(1000);
        System.out.println("Pattern: " + pattern);
        System.out.println("Test: " + text);
        new QuickSubSequenceMatcher(pattern).matchStartIndexes(text);
        new BMSubSequenceMatcher(pattern).matchStartIndexes(text);
    }

}
