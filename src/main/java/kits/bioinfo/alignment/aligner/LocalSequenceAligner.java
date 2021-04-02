package kits.bioinfo.alignment.aligner;

import java.util.Optional;

import org.apache.commons.lang3.tuple.Pair;

import kits.bioinfo.alignment.scorefunction.ScoreFunction;
import kits.bioinfo.core.Sequence;
import kits.bioinfo.util.IntPair;

public class LocalSequenceAligner<T> extends GlobalSequenceAligner<T> {

    public LocalSequenceAligner(ScoreFunction<T> scoreFunction) {
        super(scoreFunction);
    }

    @Override
    protected GridGraph buildGraph(Sequence<T> sequence1, Sequence<T> sequence2) {
        GridGraph graph = super.buildGraph(sequence1, sequence2);
        
        var pair = Pair.of(Optional.empty(), Optional.empty());
        for (int i=0;i<=sequence1.length();i++) {
            for (int j=0;j<=sequence2.length();j++) {
                var node = new IntPair(i, j);
                if (!node.equals(graph.startNode)) {
                    graph.addEdge(graph.startNode, node, 0, pair);
                }

                if(!node.equals(graph.endNode)) {
                    graph.addEdge(new IntPair(i, j), graph.endNode, 0, pair); 
                }
            }
        }
        return graph;
    }

}
