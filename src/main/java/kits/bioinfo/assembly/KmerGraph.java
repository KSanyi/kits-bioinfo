package kits.bioinfo.assembly;

import java.util.List;
import java.util.stream.Collectors;

import kits.bioinfo.core.DnaSequence;
import kits.bioinfo.math.graph.Graph;

public class KmerGraph extends Graph<DnaSequence> {

	public static KmerGraph buildFromKmerNodesList(List<DnaSequence> nodes) {
		return new KmerGraph(nodes.stream()
				.flatMap(node -> nodes.stream()
						.filter(otherNode -> node.suffix().equals(otherNode.prefix()))
						.map(otherNode -> new Edge<>(node, otherNode)))
				.collect(Collectors.toList()));
	}
	
	public static KmerGraph buildDeBrujinGraph(List<DnaSequence> edgeSequences) {
		return new KmerGraph(edgeSequences.stream()
				.map(edgeSequence -> new Edge<>(edgeSequence.prefix(), edgeSequence.suffix()))
				.collect(Collectors.toList()));
	}
	
	public static Graph<ReadPair> buildReadPairDeBrujinGraph(List<ReadPair> edgeSequences) {
		return new Graph<>(edgeSequences.stream()
				.map(edgeSequence -> new Edge<>(edgeSequence.prefix(), edgeSequence.suffix()))
				.collect(Collectors.toList()));
	}
	
	public KmerGraph(List<Edge<DnaSequence>> edges) {
		super(edges);
	}
	
}
