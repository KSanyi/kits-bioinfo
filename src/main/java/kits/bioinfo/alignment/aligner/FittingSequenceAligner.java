package kits.bioinfo.alignment.aligner;

import java.util.Optional;

import org.apache.commons.lang3.tuple.Pair;

import kits.bioinfo.alignment.scorefunction.ScoreFunction;
import kits.bioinfo.core.Sequence;
import kits.bioinfo.util.IntPair;

public class FittingSequenceAligner<T> extends GlobalSequenceAligner<T> {

    public FittingSequenceAligner(ScoreFunction<T> scoreFunction) {
        super(scoreFunction);
    }

    @Override
    protected GridGraph buildGraph(Sequence<T> sequence1, Sequence<T> sequence2) {
        GridGraph graph = super.buildGraph(sequence1, sequence2);
        IntPair startNode = new IntPair(0, 0);
        IntPair endNode = graph.endNode;

        for (int i=1;i<=sequence1.length();i++) {
            IntPair nodeTo = new IntPair(i, 0);
            int weight = scoreFunction.score(Optional.of(sequence1.position(i - 1)), Optional.empty());
            var pair = Pair.of(Optional.of(sequence1.position(i - 1)), Optional.empty());
            graph.addEdge(startNode, nodeTo, weight, pair);
            
            if (!sequence2.isEmpty()) {
                nodeTo = new IntPair(i, 1);
                weight = scoreFunction.score(Optional.of(sequence1.position(i - 1)), Optional.of(sequence2.position(0)));
                pair = Pair.of(Optional.of(sequence1.position(i - 1)), Optional.of(sequence2.position(0)));
                graph.addEdge(startNode, nodeTo, weight, pair);
            }
            
        }

        for (int i=0;i<sequence1.length();i++) {
            IntPair nodeFrom = new IntPair(i, sequence2.length());
            var pair = Pair.of(Optional.empty(), Optional.empty());
            graph.addEdge(nodeFrom, endNode, 0, pair);
        }

        return graph;
    }

}
