package kits.bioinfo.kmer;

import static java.util.Arrays.asList;
import static java.util.Collections.singleton;

import java.util.HashSet;
import java.util.Set;

import kits.bioinfo.clump.FrequentKMerFinder;
import kits.bioinfo.core.Sequence;

import org.junit.Assert;
import org.junit.Test;

public class FrequentKMerFinderTest {

	private FrequentKMerFinder frequentKMerFinder = new FrequentKMerFinder();
	
	@Test
	public void mostFrequent() {
		Sequence sequence = new Sequence("ACTGCGTCGACTGCGACTG");
		
		Set<Sequence> result = frequentKMerFinder.findMostFrequentKmers(sequence, 4);
		Assert.assertEquals(singleton(new Sequence("ACTG")), result);
		
		result = frequentKMerFinder.findMostFrequentKmers(sequence, 3);
		Assert.assertEquals(new HashSet<Sequence>(asList(new Sequence("ACT"), new Sequence("CTG"))), result);
	}
	
	@Test
	public void mostFrequentWithDistance() {
		Sequence sequence = new Sequence("ACGTTGCATGTCGCATGATGCATGAGAGCT");
		
		Set<Sequence> result = frequentKMerFinder.findMostFrequentKmersWithDistance(sequence, 4, 1);
		Assert.assertEquals(new HashSet<>(asList(new Sequence("GATG"), new Sequence("ATGC"), new Sequence("ATGT"))), result);
	}
	
}
