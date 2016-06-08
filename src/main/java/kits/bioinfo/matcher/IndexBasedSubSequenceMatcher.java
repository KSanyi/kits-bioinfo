package kits.bioinfo.matcher;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import kits.bioinfo.core.Sequence;

public class IndexBasedSubSequenceMatcher {

	private final Sequence text;
	
	private final Map<Sequence, List<Integer>> index;
	
	private final int k;
	
	public IndexBasedSubSequenceMatcher(Sequence text, int k) {
		this.text = text;
		this.k = k;
		index = buildIndex();
	}
	
	private Map<Sequence, List<Integer>> buildIndex() {
		Map<Sequence, List<Integer>> kmers = new HashMap<>();
		for(int i=0;i<text.length()-k+1;i++){
			Sequence subSequence = text.subSequence(i, k);
			List<Integer> offsets = kmers.getOrDefault(subSequence, new LinkedList<>());
			offsets.add(i);
			kmers.put(subSequence, offsets);
		}
		return Collections.unmodifiableMap(kmers);
	}
	
	public boolean matches(Sequence pattern){
		Sequence patternSubSequence = pattern.subSequence(0, k);
		List<Integer> offsets = index.get(patternSubSequence);
		for(int offset : offsets) {
			if(pattern.equals(text.subSequence(offset, k))){
				return true;
			}
		}
		return false;	
	}
	
	public List<Integer> matchStartIndexes(Sequence pattern) {
		Sequence patternSubSequence = pattern.subSequence(0, k);
		List<Integer> offsets = index.getOrDefault(patternSubSequence, Collections.emptyList());
		List<Integer> hits = new LinkedList<>();
		for(int offset : offsets) {
			if(offset + pattern.length() <= text.length() && pattern.equals(text.subSequence(offset, pattern.length()))){
				hits.add(offset);
			}
		}
		return hits;
	}
	
	public int matchCount(Sequence pattern){
		return matchStartIndexes(pattern).size();
	}
	
}
