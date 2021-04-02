package kits.bioinfo.alignment.aligner;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.tuple.Pair;

import kits.bioinfo.alignment.aligner.GridGraph.Edge;
import kits.bioinfo.alignment.scorefunction.ScoreFunction;
import kits.bioinfo.core.Sequence;
import kits.bioinfo.util.IntPair;

public class GlobalSequenceAligner<T> {

    protected final ScoreFunction<T> scoreFunction;

    public GlobalSequenceAligner(ScoreFunction<T> scoreFunction) {
        this.scoreFunction = scoreFunction;
    }

    public AlignmentResult<T> findOneAlignment(Sequence<T> sequence1, Sequence<T> sequence2) {
        GridGraph graph = buildGraph(sequence1, sequence2);
        List<Edge> path = GridGraphLongestPathFinder.findLongestPath(graph);

        return buildResult(path, graph.getEndValue());
    }

    protected GridGraph buildGraph(Sequence<T> sequence1, Sequence<T> sequence2) {
        GridGraph graph = new GridGraph(sequence1.length() + 1, sequence2.length() + 1);

        for (int i=0;i<=sequence1.length();i++) {
            for (int j=0;j<=sequence2.length();j++) {
                IntPair nodeFrom = new IntPair(i, j);

                if (i < sequence1.length()) {
                    IntPair nodeTo = new IntPair(i + 1, j);
                    int weight = scoreFunction.score(Optional.of(sequence1.position(i)), Optional.empty());
                    var pair = Pair.of(Optional.of(sequence1.position(i)), Optional.empty());
                    graph.addEdge(nodeFrom, nodeTo, weight, pair);
                }
                if (j < sequence2.length()) {
                    IntPair nodeTo = new IntPair(i, j + 1);
                    int weight = scoreFunction.score(Optional.empty(), Optional.of(sequence2.position(j)));
                    var pair = Pair.of(Optional.empty(), Optional.of(sequence2.position(j)));
                    graph.addEdge(nodeFrom, nodeTo, weight, pair);
                }
                if (i < sequence1.length() && j < sequence2.length()) {
                    IntPair nodeTo = new IntPair(i + 1, j + 1);
                    int weight = scoreFunction.score(Optional.of(sequence1.position(i)), Optional.of(sequence2.position(j)));
                    var pair = Pair.of(Optional.of(sequence1.position(i)), Optional.of(sequence2.position(j)));
                    graph.addEdge(nodeFrom, nodeTo, weight, pair);
                }
            }
        }

        return graph;
    }
    
    private AlignmentResult<T> buildResult(List<Edge> path, int score) {
        Sequence<T> alignment1 = new Sequence<>();
        Sequence<T> alignment2 = new Sequence<>();
        for (int i = 0; i < path.size(); i++) {
            Edge edge = path.get(i);
            @SuppressWarnings("unchecked")
            Pair<Optional<T>, Optional<T>> data = (Pair<Optional<T>, Optional<T>>)edge.data();
            
            T element1 = data.getLeft().orElse(null);
            T element2 = data.getRight().orElse(null);
            if(element1 != null || element2 != null) {
                alignment1 = alignment1.append(element1);
                alignment2 = alignment2.append(element2); 
            }
        }
        return new AlignmentResult<>(alignment1, alignment2, score);
    }

}
