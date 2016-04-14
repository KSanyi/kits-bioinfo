package kits.bioinfo.clump;

import java.util.HashSet;
import java.util.Set;

import kits.bioinfo.base.Nucleotid;
import kits.bioinfo.base.Sequence;
import kits.bioinfo.kmer.KmerFrequencyMap;

public class QuickClumpFinder implements ClumpFinder {

	public Set<Sequence> findKmersFormingClumps(Sequence sequence, int L, int k, int t) {
		
		boolean printProgress = sequence.length() > 1_000_000;
		
		Set<Sequence> kMersFormingClumps = new HashSet<>();
		KmerFrequencyMap frequencyMap = new KmerFrequencyMap();
		
		for(int index=0;index<L-k+1;index++) {
			Sequence kmer = sequence.subSequence(index, k);
			frequencyMap.put(kmer);
		}
		
		kMersFormingClumps.addAll(frequencyMap.kmersAppersAtLeast(t));
		
		int processed = 0;
		int lastProcessed = 0;
		for(int index=1;index<sequence.length()-L+1;index++) {
			Nucleotid n = sequence.position(index-1);
			Sequence sequenceLost = sequence.subSequence(index, k-1).prepend(n);
			frequencyMap.remove(sequenceLost);
			
			Nucleotid n2 = sequence.position(index+L-1);
			Sequence sequenceAdded = sequence.subSequence(index+L-k, k-1).append(n2);
			frequencyMap.put(sequenceAdded);
			
			if(frequencyMap.frequency(sequenceAdded) >= t){
				kMersFormingClumps.add(sequenceAdded);
			}
			
			lastProcessed = processed;
			processed = index * 100 / sequence.length();
			if(printProgress && lastProcessed != processed)System.out.println("Processed: " + processed + " %");
		}
		
		return kMersFormingClumps;
	}
	
}
