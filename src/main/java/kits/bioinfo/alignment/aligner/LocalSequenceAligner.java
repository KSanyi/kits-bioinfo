package kits.bioinfo.alignment.aligner;

import java.util.Optional;

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
		IntPair startNode = graph.startNode;
		IntPair endNode = graph.endNode;
		for (int i = 0; i <= sequence1.length(); i++) {
			for (int j = 0; j <= sequence2.length(); j++) {
				boolean isStart = i == 0 && j == 0;
				if (!isStart) {
					IntPair nodeTo = new IntPair(i, j);
					Optional<T> elemI = i > 0 ? Optional.of(sequence1.position(i - 1)) : Optional.empty();
					Optional<T> elemJ = j > 0 ? Optional.of(sequence2.position(j - 1)) : Optional.empty();
					int weight = scoreFunction.score(elemI, elemJ);
					graph.addEdge(startNode, nodeTo, weight);
				}

				IntPair nodeFrom = new IntPair(i, j);
				graph.addEdge(nodeFrom, endNode, 0);
			}
		}
		return graph;
	}

}
