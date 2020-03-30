package kits.bioinfo.clump;

import java.util.HashSet;
import java.util.Set;

import kits.bioinfo.core.DnaBase;
import kits.bioinfo.core.DnaSequence;

public class QuickClumpFinder implements ClumpFinder {

    public Set<DnaSequence> findKmersFormingClumps(DnaSequence sequence, int L, int k, int t) {

        boolean printProgress = sequence.length() > 1_000_000;

        Set<DnaSequence> kMersFormingClumps = new HashSet<>();
        KmerFrequencyMap frequencyMap = new KmerFrequencyMap();

        for (int index = 0; index < L - k + 1; index++) {
            DnaSequence kmer = sequence.subSequence(index, k);
            frequencyMap.put(kmer);
        }

        kMersFormingClumps.addAll(frequencyMap.kmersAppersAtLeast(t));

        int processed = 0;
        int lastProcessed = 0;
        for (int index = 1; index < sequence.length() - L + 1; index++) {
            DnaBase base = sequence.position(index - 1);
            DnaSequence sequenceLost = sequence.subSequence(index, k - 1).prepend(base);
            frequencyMap.remove(sequenceLost);

            DnaBase base2 = sequence.position(index + L - 1);
            DnaSequence sequenceAdded = sequence.subSequence(index + L - k, k - 1).append(base2);
            frequencyMap.put(sequenceAdded);

            if (frequencyMap.frequency(sequenceAdded) >= t) {
                kMersFormingClumps.add(sequenceAdded);
            }

            lastProcessed = processed;
            processed = index * 100 / sequence.length();
            if (printProgress && lastProcessed != processed)
                System.out.println("Processed: " + processed + " %");
        }

        return kMersFormingClumps;
    }

}
