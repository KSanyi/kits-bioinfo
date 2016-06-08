package kits.bioinfo.clump;

import java.util.HashSet;
import java.util.Set;

import kits.bioinfo.core.Sequence;
import kits.bioinfo.matcher.ApproximateSubSequenceMatcher;

public class FrequentKMerFinder {

	public Set<Sequence> findMostFrequentKmers(Sequence sequence, int k) {
	
		KmerFrequencyMap frequencyMap = new KmerFrequencyMap();
		
		for(int index=0;index<sequence.length()-k+1;index++) {
			Sequence kmer = sequence.subSequence(index, k);
			frequencyMap.put(kmer);
		}
		
		return frequencyMap.mostFrequentKmers();
	}
	
	public Set<Sequence> findMostFrequentKmersWithDistance(Sequence sequence, int k, int d) {
		
		Set<Sequence> candidateKmers = new HashSet<>();
		
		for(int index=0;index<sequence.length()-k+1;index++) {
			Sequence kmer = sequence.subSequence(index, k);
			candidateKmers.addAll(kmer.neighbours(k));
		}
		
		KmerFrequencyMap frequencyMap = new KmerFrequencyMap();
		for(Sequence kmer : candidateKmers) {
			int frequency = new ApproximateSubSequenceMatcher(kmer, d).matchCount(sequence);
			frequencyMap.put(kmer, frequency);
		}
		
		return frequencyMap.mostFrequentKmers();
	}
		
}
