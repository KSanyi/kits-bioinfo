package kits.bioinfo.assembly;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import kits.bioinfo.base.Sequence;

public class KmerGraph {

	public static KmerGraph buildFromKmerNodesList(List<Sequence> nodes, int k) {
		Map<Sequence, List<Sequence>> adjacencyMap = new HashMap<>();
		for(Sequence node : nodes) {
			List<Sequence> adjacentNodes = nodes.stream().filter(otherKmer -> node.suffix(k).equals(otherKmer.prefix(k))).collect(Collectors.toList());
			adjacencyMap.put(node, adjacentNodes);
		}
		return new KmerGraph(adjacencyMap);
	}
	
	public static KmerGraph buildDeBrujinGraph(List<Sequence> edges) {
		Map<Sequence, List<Sequence>> adjacencyMap = new HashMap<>();
		
		for(Sequence edge : edges) {
			Sequence fromNode = edge.prefix();
			Sequence toNode = edge.suffix();
			
			List<Sequence> adjacentNodes = adjacencyMap.getOrDefault(fromNode, new LinkedList<>());
			adjacentNodes.add(toNode);
			adjacencyMap.put(fromNode, adjacentNodes);
		}
		
		return new KmerGraph(adjacencyMap);
	}
	
	private final Map<Sequence, List<Sequence>> adjacencyMap;
	
	public KmerGraph(Map<Sequence, List<Sequence>> adjacencyMap) {
		this.adjacencyMap = Collections.unmodifiableMap(adjacencyMap);
	}
	
	public String printEdges() {
		StringBuilder sb = new StringBuilder();
		for(Entry<Sequence, List<Sequence>> entry : adjacencyMap.entrySet()){
			Sequence node = entry.getKey();
			entry.getValue().stream().forEach(adjacentNode -> sb.append(node + " -> " + adjacentNode + "\n"));
		}
		return sb.toString();
	}
	
	public String print() {
		StringBuilder sb = new StringBuilder();
		for(Entry<Sequence, List<Sequence>> entry : adjacencyMap.entrySet()){
			sb.append(entry.getKey() + " -> ");
			sb.append(entry.getValue().stream().map(n -> n.toString()).collect(Collectors.joining(", ")) + "\n");
		}
		return sb.toString();
	}
	
}
