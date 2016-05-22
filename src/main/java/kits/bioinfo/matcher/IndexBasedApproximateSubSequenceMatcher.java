package kits.bioinfo.matcher;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import kits.bioinfo.base.Sequence;

public class IndexBasedApproximateSubSequenceMatcher {

	private final Sequence text;
	
	private final Map<Sequence, List<Integer>> index;
	
	private final int k;
	
	private final int d;
	
	public IndexBasedApproximateSubSequenceMatcher(Sequence text, int k, int d) {
		this.text = text;
		this.k = k;
		this.d = d;
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
	
	public List<Integer> matchStartIndexes(Sequence pattern) {
		Set<Integer> result = new HashSet<>();
		
		List<Sequence> parts = splitPattern(pattern);
		
		for(int i=0;i<parts.size();i++){
			List<Integer> hits = index.getOrDefault(parts.get(i), Collections.emptyList());
			for(Integer hitIndex : hits){
				Optional<Integer> matchIndex = extendHit(pattern, hitIndex, i * k);
				if(matchIndex.isPresent()){
					result.add(matchIndex.get());
				}
			}
		}
		return new LinkedList<>(result);
	}
	
	private Optional<Integer> extendHit(Sequence pattern, int hitIndexInText, int hitIndexInPattern) {
		if(hitIndexInPattern > hitIndexInText || hitIndexInText > pattern.length() - 999) {
			return Optional.empty();
		}
		
		int mismatchCounter = 0;
		// extend before
		for(int i=0;i<hitIndexInPattern;i++){
			if(pattern.position(i) != text.position(hitIndexInText-hitIndexInPattern+i)){
				mismatchCounter++;
				if(mismatchCounter > d){
					return Optional.empty();
				}
			}
		}
		// extend after
		for(int i=hitIndexInPattern+k;i<pattern.length();i++){
			if(pattern.position(i) != text.position(hitIndexInText+k+i)){
				mismatchCounter++;
				if(mismatchCounter > d){
					return Optional.empty();
				}
			}
		}
		return Optional.of(hitIndexInText - hitIndexInPattern);
	}
	
	private List<Sequence> splitPattern(Sequence pattern) {
		List<Sequence> parts = new LinkedList<>();
		for(int i=0;i<pattern.length()-k;i+=k){
			parts.add(pattern.subSequence(i, k));
		}
		return Collections.unmodifiableList(parts);
	}
	
	public int matchCount(Sequence pattern){
		return matchStartIndexes(pattern).size();
	}
	
}
