package kits.bioinfo.alignment.aligner;

import kits.bioinfo.alignment.scorefunction.ScoreFunction;
import kits.bioinfo.core.Sequence;
import kits.bioinfo.util.IntPair;

public class LocalSequenceAligner<T> extends GlobalSequenceAligner<T>{

	public LocalSequenceAligner(ScoreFunction<T> scoreFunction) {
		super(scoreFunction);
	}

	@Override
	protected GridGraph buildGraph(Sequence<T> sequence1, Sequence<T> sequence2){
		GridGraph graph = super.buildGraph(sequence1, sequence2);
		IntPair startNode = new IntPair(0, 0);
		IntPair endNode = graph.endNode;
		for(int i=0;i<=sequence1.length();i++){
			for(int j=0;j<=sequence2.length();j++){
				boolean isStart = i == 0 && j == 0; 
				if(!isStart){
					IntPair nodeTo = new IntPair(i, j);
					graph.addEdge(startNode, nodeTo, 0);
				}
				
				IntPair nodeFrom = new IntPair(i, j);
				graph.addEdge(nodeFrom, endNode, 0);
			}
		}
		return graph;
	}
	
}
