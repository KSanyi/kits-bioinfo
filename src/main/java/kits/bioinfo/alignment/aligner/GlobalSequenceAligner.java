package kits.bioinfo.alignment.aligner;

import java.util.List;
import java.util.Optional;

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
        List<IntPair> path = GridGraphLongestPathFinder.findLongestPath(graph);

        return buildResult(sequence1, sequence2, path, graph.getValue());
    }

    protected GridGraph buildGraph(Sequence<T> sequence1, Sequence<T> sequence2) {
        GridGraph graph = new GridGraph(sequence1.length() + 1, sequence2.length() + 1);

        for (int i=0;i<=sequence1.length();i++) {
            for (int j=0;j<=sequence2.length();j++) {
                IntPair nodeFrom = new IntPair(i, j);

                if (i < sequence1.length()) {
                    IntPair nodeTo = new IntPair(i + 1, j);
                    int weight = scoreFunction.score(Optional.of(sequence1.position(i)), Optional.empty());
                    graph.addEdge(nodeFrom, nodeTo, weight);
                }
                if (j < sequence2.length()) {
                    IntPair nodeTo = new IntPair(i, j + 1);
                    int weight = scoreFunction.score(Optional.empty(), Optional.of(sequence2.position(j)));
                    graph.addEdge(nodeFrom, nodeTo, weight);
                }
                if (i < sequence1.length() && j < sequence2.length()) {
                    IntPair nodeTo = new IntPair(i + 1, j + 1);
                    int weight = scoreFunction.score(Optional.of(sequence1.position(i)), Optional.of(sequence2.position(j)));
                    graph.addEdge(nodeFrom, nodeTo, weight);
                }
            }
        }

        graph.addEdge(new IntPair(sequence1.length(), sequence2.length()), graph.endNode, 0);

        return graph;
    }
    
    private AlignmentResult<T> buildResult(Sequence<T> sequence1, Sequence<T> sequence2, List<IntPair> path, int score) {
        Sequence<T> alignment1 = new Sequence<>();
        Sequence<T> alignment2 = new Sequence<>();
        for (int i = 1; i < path.size(); i++) {
            IntPair prevNode = path.get(i - 1);
            IntPair node = path.get(i);

            if (node.first() == (prevNode.first())) {
                alignment1 = alignment1.append((T) null);
            } else {
                alignment1 = alignment1.append(sequence1.position(node.first() - 1));
            }
            if (node.second() == (prevNode.second())) {
                alignment2 = alignment2.append((T) null);
            } else {
                alignment2 = alignment2.append(sequence2.position(node.second() - 1));
            }
        }
        return new AlignmentResult<>(alignment1, alignment2, score);
    }

}
