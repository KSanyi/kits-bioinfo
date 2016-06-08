package kits.bioinfo.motif;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import kits.bioinfo.core.DnaSequence;

public class GreedyMotifFinderTest {

	@Test
	public void test() {
		List<DnaSequence> sequences = Arrays.asList(
				new DnaSequence("GGCGTTCAGGCA"),
				new DnaSequence("AAGAATCAGTCA"), 
				new DnaSequence("CAAGGAGTTCGC"), 
				new DnaSequence("CACGTCAATCAC"),
				new DnaSequence("CAATAATATTCG"));
		
		System.out.println(new GreedyMotifFinder().findMotifs(sequences, 3));
	}
	
}
