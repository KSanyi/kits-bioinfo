package kits.bioinfo.clump;

import java.util.HashSet;
import java.util.Set;

import kits.bioinfo.core.DnaSequence;
import kits.bioinfo.matcher.ApproximateSubSequenceMatcher;

public class FrequentKMerFinder {

	public Set<DnaSequence> findMostFrequentKmers(DnaSequence sequence, int k) {
	
		KmerFrequencyMap frequencyMap = new KmerFrequencyMap();
		
		for(int index=0;index<sequence.length()-k+1;index++) {
			DnaSequence kmer = sequence.subSequence(index, k);
			frequencyMap.put(kmer);
		}
		
		return frequencyMap.mostFrequentKmers();
	}
	
	public Set<DnaSequence> findMostFrequentKmersWithDistance(DnaSequence sequence, int k, int d) {
		
		Set<DnaSequence> candidateKmers = new HashSet<>();
		
		for(int index=0;index<sequence.length()-k+1;index++) {
			DnaSequence kmer = sequence.subSequence(index, k);
			candidateKmers.addAll(kmer.neighbours(k));
		}
		
		KmerFrequencyMap frequencyMap = new KmerFrequencyMap();
		for(DnaSequence kmer : candidateKmers) {
			int frequency = new ApproximateSubSequenceMatcher(kmer, d).matchCount(sequence);
			frequencyMap.put(kmer, frequency);
		}
		
		return frequencyMap.mostFrequentKmers();
	}
		
}
