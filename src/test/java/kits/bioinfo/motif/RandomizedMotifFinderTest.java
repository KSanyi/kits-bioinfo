package kits.bioinfo.motif;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import kits.bioinfo.core.DnaSequence;

public class RandomizedMotifFinderTest {

    @Test
    public void test() {
        List<DnaSequence> sequences = List.of(new DnaSequence("CGCCCCTCTCGGGGGTGTTCAGTAAACGGCCA"),
                new DnaSequence("GGGCGAGGTATGTGTAAGTGCCAAGGTGCCAG"), new DnaSequence("TAGTACCGAGACCGAAAGAAGTATACAGGCGT"),
                new DnaSequence("TAGATCAAGTTTCAGGTGCACGTCGGTGAACC"), new DnaSequence("AATCCACCAGCTCCACGTGCAATGTTGGCCTA"));

        Set<DnaSequence> medianStrings = MedianStringFinder.findMedianStrings(sequences, 8);

        System.out.println("Median strings: " + medianStrings);

        System.out.println("Distance from median strings: "
                + medianStrings.stream().map(medianString -> Motifs.distance(sequences, medianString)).collect(Collectors.toList()));

        List<DnaSequence> motifs = new RandomizedMotifFinder(1000).findMotifs(sequences, 8);
        System.out.println("Motifs found with randomized motif finder: " + motifs);
        System.out.println("Motifs score: " + Motifs.score(motifs));
    }

}
