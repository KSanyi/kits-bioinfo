package kits.bioinfo.matcher;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import kits.bioinfo.core.DnaSequence;

public class IndexBasedSubSequenceMatcher {

	private final DnaSequence text;

	private final Map<DnaSequence, List<Integer>> index;

	private final int k;

	public IndexBasedSubSequenceMatcher(DnaSequence text, int k) {
		this.text = text;
		this.k = k;
		index = buildIndex();
	}

	private Map<DnaSequence, List<Integer>> buildIndex() {
		Map<DnaSequence, List<Integer>> kmers = new HashMap<>();
		for (int i = 0; i < text.length() - k + 1; i++) {
			DnaSequence subSequence = text.subSequence(i, k);
			List<Integer> offsets = kmers.getOrDefault(subSequence, new LinkedList<>());
			offsets.add(i);
			kmers.put(subSequence, offsets);
		}
		return Collections.unmodifiableMap(kmers);
	}

	public boolean matches(DnaSequence pattern) {
		DnaSequence patternSubSequence = pattern.subSequence(0, k);
		List<Integer> offsets = index.get(patternSubSequence);
		for (int offset : offsets) {
			if (pattern.equals(text.subSequence(offset, k))) {
				return true;
			}
		}
		return false;
	}

	public List<Integer> matchStartIndexes(DnaSequence pattern) {
		DnaSequence patternSubSequence = pattern.subSequence(0, k);
		List<Integer> offsets = index.getOrDefault(patternSubSequence, Collections.emptyList());
		List<Integer> hits = new LinkedList<>();
		for (int offset : offsets) {
			if (offset + pattern.length() <= text.length() && pattern.equals(text.subSequence(offset, pattern.length()))) {
				hits.add(offset);
			}
		}
		return hits;
	}

	public int matchCount(DnaSequence pattern) {
		return matchStartIndexes(pattern).size();
	}

}
