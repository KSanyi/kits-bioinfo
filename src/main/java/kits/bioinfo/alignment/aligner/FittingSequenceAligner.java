package kits.bioinfo.alignment.aligner;

import kits.bioinfo.alignment.scorefunction.ScoreFunction;
import kits.bioinfo.core.Sequence;
import kits.bioinfo.util.IntPair;

public class FittingSequenceAligner<T> extends GlobalSequenceAligner<T>{

	public FittingSequenceAligner(ScoreFunction<T> scoreFunction) {
		super(scoreFunction);
	}

	@Override
	protected GridGraph buildGraph(Sequence<T> sequence1, Sequence<T> sequence2){
		GridGraph graph = super.buildGraph(sequence1, sequence2);
		IntPair startNode = new IntPair(0, 0);
		IntPair endNode = graph.endNode;
		
		for(int i=1;i<=sequence1.length();i++){
			IntPair nodeTo = new IntPair(i, 0);
			graph.addEdge(startNode, nodeTo, 0);
		}
		
		for(int i=1;i<=sequence1.length();i++){
			IntPair nodeFrom = new IntPair(i, sequence2.length());
			graph.addEdge(nodeFrom, endNode, 0);
		}
		
		return graph;
	}
	
}
