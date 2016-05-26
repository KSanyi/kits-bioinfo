package kits.bioinfo.assembly;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import kits.bioinfo.base.Sequence;

public class KmerGraph {

	public static KmerGraph buildFromKmerList(List<Sequence> kmers, int k) {
		Map<Sequence, List<Sequence>> adjacencyMap = new HashMap<>();
		for(Sequence kmer : kmers) {
			List<Sequence> adjacentKmers = kmers.stream().filter(otherKmer -> kmer.suffix(k).equals(otherKmer.prefix(k))).collect(Collectors.toList());
			adjacencyMap.put(kmer, adjacentKmers);
		}
		return new KmerGraph(adjacencyMap);
	}
	
	private final Map<Sequence, List<Sequence>> adjacencyMap;
	
	public KmerGraph(Map<Sequence, List<Sequence>> adjacencyMap) {
		this.adjacencyMap = Collections.unmodifiableMap(adjacencyMap);
	}
	
	public String print() {
		StringBuilder sb = new StringBuilder();
		for(Entry<Sequence, List<Sequence>> entry : adjacencyMap.entrySet()){
			Sequence node = entry.getKey();
			entry.getValue().stream().forEach(adjacentNode -> sb.append(node + " -> " + adjacentNode + "\n"));
		}
		return sb.toString();
	}
	
}
