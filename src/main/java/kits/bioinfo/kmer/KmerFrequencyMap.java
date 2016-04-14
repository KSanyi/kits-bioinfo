package kits.bioinfo.kmer;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import kits.bioinfo.base.Sequence;

public class KmerFrequencyMap {

	private Map<Sequence, Integer> map = new HashMap<>();
	
	public Set<Sequence> kmersAppersAtLeast(int t) {
		return map.keySet().stream().filter(sequence -> map.get(sequence) >= t).collect(Collectors.toSet());
	}
	
	public Set<Sequence> sequences(){
		return map.keySet();
	}
	
	public int frequency(Sequence sequence) {
		return map.getOrDefault(sequence, 0);
	}
	
	public void put(Sequence kmer) {
		put(kmer, 1);
	}
	
	public void put(Sequence kmer, int frequency) {
		int newFrequency = map.getOrDefault(kmer, 0) + frequency; 
		map.put(kmer, newFrequency);
	}
	
	public void remove(Sequence kmer) {
		int frequency = map.get(kmer); 
		map.put(kmer, frequency - 1);
	}
	
	public Set<Sequence> mostFrequentKmers() {
		final int max = map.values().stream().max(Comparator.naturalOrder()).orElse(-1);
		return map.keySet().stream().filter(sequence -> map.get(sequence) == max).collect(Collectors.toSet());
	}
	
	@Override
	public String toString() {
		return map.toString();
	}
	
}
