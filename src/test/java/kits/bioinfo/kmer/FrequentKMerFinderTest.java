package kits.bioinfo.kmer;

import static java.util.Arrays.asList;
import static java.util.Collections.singleton;

import java.util.HashSet;
import java.util.Set;

import kits.bioinfo.clump.FrequentKMerFinder;
import kits.bioinfo.core.DnaSequence;

import org.junit.Assert;
import org.junit.Test;

public class FrequentKMerFinderTest {

	private FrequentKMerFinder frequentKMerFinder = new FrequentKMerFinder();
	
	@Test
	public void mostFrequent() {
		DnaSequence sequence = new DnaSequence("ACTGCGTCGACTGCGACTG");
		
		Set<DnaSequence> result = frequentKMerFinder.findMostFrequentKmers(sequence, 4);
		Assert.assertEquals(singleton(new DnaSequence("ACTG")), result);
		
		result = frequentKMerFinder.findMostFrequentKmers(sequence, 3);
		Assert.assertEquals(new HashSet<DnaSequence>(asList(new DnaSequence("ACT"), new DnaSequence("CTG"))), result);
	}
	
	@Test
	public void mostFrequentWithDistance() {
		DnaSequence sequence = new DnaSequence("ACGTTGCATGTCGCATGATGCATGAGAGCT");
		
		Set<DnaSequence> result = frequentKMerFinder.findMostFrequentKmersWithDistance(sequence, 4, 1);
		Assert.assertEquals(new HashSet<>(asList(new DnaSequence("GATG"), new DnaSequence("ATGC"), new DnaSequence("ATGT"))), result);
	}
	
}
