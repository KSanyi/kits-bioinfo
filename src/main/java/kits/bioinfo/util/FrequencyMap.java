package kits.bioinfo.util;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class FrequencyMap<T> {

	private Map<T, Integer> map = new HashMap<>();
	
	public void put(T elem) {
		map.put(elem, map.getOrDefault(elem, 0) + 1);
	}
	
	public T getMostFrequent() {
		final int max = map.values().stream().max(Comparator.naturalOrder()).orElse(-1);
		return map.keySet().stream().filter(elem -> map.get(elem) == max).findFirst().get();
	}
	
	@Override
	public String toString() {
		return map.keySet().stream().map(elem -> elem + " " + map.get(elem)).collect(Collectors.joining(", "));
	}
	
}
