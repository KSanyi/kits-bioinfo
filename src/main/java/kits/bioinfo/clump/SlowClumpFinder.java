package kits.bioinfo.clump;

import java.util.HashSet;
import java.util.Set;

import kits.bioinfo.core.DnaSequence;

public class SlowClumpFinder implements ClumpFinder {

	public Set<DnaSequence> findKmersFormingClumps(DnaSequence sequence, int L, int k, int t) {
		
		boolean printProgress = sequence.length() > 1_000_000;
		
		Set<DnaSequence> kMersFormingClumps = new HashSet<>();
		int processed = 0;	int lastProcessed = 0;
		for(int index=0;index<sequence.length()-L+1;index++) {
			KmerFrequencyMap frequencyMap = createFrequencyMap(sequence.subSequence(index, L), k);
			kMersFormingClumps.addAll(frequencyMap.kmersAppersAtLeast(t));
			
			lastProcessed = processed;
			processed = index * 100 / sequence.length();
			if(printProgress && lastProcessed != processed)System.out.println("Processed: " + processed + " %");
		}
		
		return kMersFormingClumps;
	}
	
	private KmerFrequencyMap createFrequencyMap(DnaSequence sequence, int k) {
		KmerFrequencyMap frequencyMap = new KmerFrequencyMap();
		
		for(int index=0;index<sequence.length()-k+1;index++) {
			DnaSequence kmer = sequence.subSequence(index, k);
			frequencyMap.put(kmer);
		}
		return frequencyMap;
	}
	
}
