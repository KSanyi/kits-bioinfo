package kits.bioinfo.assembly;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Optional;

import kits.bioinfo.core.DnaSequence;
import kits.bioinfo.math.graph.EulerianPathFinder;
import kits.bioinfo.math.graph.Graph;

public class ReadPairAssembler {

    public static Optional<DnaSequence> assembleSequence(List<ReadPair> readPairs) {
        checkReadPairs(readPairs);

        int distance = readPairs.get(0).distance();
        int k = readPairs.get(0).read1().length();

        Graph<ReadPair> graph = KmerGraph.buildReadPairDeBrujinGraph(readPairs);
        for (int i = 0; i < 10000; i++) {
            List<ReadPair> path = EulerianPathFinder.findEulerianPath(graph).stream().map(node -> node).collect(toList());
            DnaSequence sequence1 = KmerCompositioner
                    .readSequenceFromComposition(path.stream().map(ReadPair::read1).collect(toList()));
            DnaSequence sequence2 = KmerCompositioner
                    .readSequenceFromComposition(path.stream().map(ReadPair::read2).collect(toList()));
            if (areValidReadPairSequences(sequence1, sequence2, distance, k - 1)) {
                return Optional.of(readSequenceFromReadPairComposition(path));
            }
        }
        return Optional.empty();
    }

    private static boolean areValidReadPairSequences(DnaSequence sequence1, DnaSequence sequence2, int distance, int k) {
        return sequence1.suffix(sequence1.length() - k - distance - 1).equals(sequence2.prefix(sequence1.length() - k - distance - 1));
    }

    private static DnaSequence readSequenceFromReadPairComposition(List<ReadPair> composition) {
        int distance = composition.get(0).distance();
        int k = composition.get(0).read1().length();
        DnaSequence sequence1 = KmerCompositioner
                .readSequenceFromComposition(composition.stream().map(ReadPair::read1).collect(toList()));
        DnaSequence sequence2 = KmerCompositioner
                .readSequenceFromComposition(composition.stream().map(ReadPair::read2).collect(toList()));
        return sequence1.append(sequence2.suffix(distance + k + 1));
    }

    private static void checkReadPairs(List<ReadPair> readPairs) {
        if (readPairs.isEmpty())
            throw new IllegalArgumentException("Can not assemple sequence from empty read pair list");

        int distance = readPairs.get(0).distance();
        if (readPairs.stream().anyMatch(readPair -> readPair.distance() != distance))
            throw new IllegalArgumentException("Read pairs found with unequal distance");

        int k = readPairs.get(0).read1().length();
        if (readPairs.stream().anyMatch(readPair -> readPair.read1().length() != k))
            throw new IllegalArgumentException("Read pairs found with unequal length");
        if (readPairs.stream().anyMatch(readPair -> readPair.read2().length() != k))
            throw new IllegalArgumentException("Read pairs found with unequal length");
    }

}
