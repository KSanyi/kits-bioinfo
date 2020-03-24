package kits.bioinfo.ucsandiego.course1;

import java.io.IOException;
import java.util.Set;

import kits.bioinfo.clump.FastClumpFinder;
import kits.bioinfo.core.DnaSequence;
import kits.bioinfo.infrastructure.SequenceReader;
import kits.bioinfo.matcher.SubSequenceMatcher;
import kits.bioinfo.util.Timer;

public class Main {

    public static void main(String[] args) throws IOException {
        clupmsInGenome("input/Vibrio_cholerae.txt");
        clupmsInGenome("input/E-coli.txt");
    }

    private static void patternInCholeraeGenome() throws IOException {
        DnaSequence choleraeGenome = SequenceReader.readDnaSequenceFromFile("input/Vibrio_cholerae.txt");
        DnaSequence oriCSequence = new DnaSequence("ATGATCAAG");

        System.out.println(new SubSequenceMatcher(oriCSequence).matchStartIndexes(choleraeGenome));
        System.out.println(new SubSequenceMatcher(oriCSequence.reverseComplement()).matchStartIndexes(choleraeGenome));
    }

    private static void clupmsInGenome(String path) throws IOException {
        DnaSequence genome = SequenceReader.readDnaSequenceFromFile(path);
        Timer.timed(() -> {
            Set<DnaSequence> kmersFormingClump = new FastClumpFinder().findKmersFormingClumps(genome, 500, 9, 5);
            System.out.println("Kmers forming clumps in genome file " + path + ": " + kmersFormingClump);
        });
    }

}
