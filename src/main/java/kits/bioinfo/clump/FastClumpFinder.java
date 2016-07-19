package kits.bioinfo.clump;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import kits.bioinfo.core.DnaSequence;

import java.util.Set;
import java.util.TreeSet;

public class FastClumpFinder implements ClumpFinder {

	public Set<DnaSequence> findKmersFormingClumps(DnaSequence sequence, int L, int k, int t) {

		boolean printProgress = sequence.length() > 1_000_000;

		Map<DnaSequence, List<Integer>> kmerStartIndexMap = new HashMap<>();
		int processed = 0;
		int lastProcessed = 0;
		for (int index = 0; index < sequence.length() - k + 1; index++) {
			DnaSequence kmer = sequence.subSequence(index, k);
			List<Integer> indexes = kmerStartIndexMap.getOrDefault(kmer, new LinkedList<>());
			if (!indexes.isEmpty() && indexes.size() < t && index > indexes.get(indexes.size() - 1) + L) {
				indexes.clear();
			}
			indexes.add(index);
			kmerStartIndexMap.put(kmer, indexes);

			lastProcessed = processed;
			processed = index * 100 / sequence.length();
			if (printProgress && lastProcessed != processed)
				System.out.println("Processed: " + processed + " %");
		}
		Set<DnaSequence> kMersFormingClumps = new TreeSet<>();
		for (Entry<DnaSequence, List<Integer>> entry : kmerStartIndexMap.entrySet()) {
			if (doIndexesFormClump(entry.getValue(), L, k, t)) {
				kMersFormingClumps.add(entry.getKey());
			}
		}
		return kMersFormingClumps;
	}

	private boolean doIndexesFormClump(List<Integer> indexes, int L, int k, int t) {
		for (int i = 0; i < indexes.size() - t + 1; i++) {
			if (indexes.get(i + t - 1) - indexes.get(i) <= L - k) {
				return true;
			}
		}
		return false;
	}

}
