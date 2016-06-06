package kits.bioinfo.assembly;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import kits.bioinfo.base.Sequence;

public class KmerGraph extends Graph<Sequence> {

	public static KmerGraph buildFromKmerNodesList(List<Sequence> nodes, int k) {
		Map<Sequence, Set<Sequence>> adjacencyMap = new HashMap<>();
		for(Sequence node : nodes) {
			Set<Sequence> adjacentNodes = nodes.stream().filter(otherKmer -> node.suffix(k).equals(otherKmer.prefix(k))).collect(Collectors.toSet());
			adjacencyMap.put(node, adjacentNodes);
		}
		return new KmerGraph(adjacencyMap);
	}
	
	public static KmerGraph buildDeBrujinGraph(List<Sequence> edges) {
		Map<Sequence, Set<Sequence>> adjacencyMap = new HashMap<>();
		
		for(Sequence edge : edges) {
			Sequence fromNode = edge.prefix();
			Sequence toNode = edge.suffix();
			
			Set<Sequence> adjacentNodes = adjacencyMap.getOrDefault(fromNode, new HashSet<>());
			adjacentNodes.add(toNode);
			adjacencyMap.put(fromNode, adjacentNodes);
		}
		
		return new KmerGraph(adjacencyMap);
	}
	
	public KmerGraph(Map<Sequence, Set<Sequence>> adjacencyMap) {
		super(adjacencyMap);
	}
	
}
