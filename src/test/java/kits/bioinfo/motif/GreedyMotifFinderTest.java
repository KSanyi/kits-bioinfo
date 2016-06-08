package kits.bioinfo.motif;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import kits.bioinfo.core.Sequence;

public class GreedyMotifFinderTest {

	@Test
	public void test() {
		List<Sequence> sequences = Arrays.asList(
				new Sequence("GGCGTTCAGGCA"),
				new Sequence("AAGAATCAGTCA"), 
				new Sequence("CAAGGAGTTCGC"), 
				new Sequence("CACGTCAATCAC"),
				new Sequence("CAATAATATTCG"));
		
		System.out.println(new GreedyMotifFinder().findMotifs(sequences, 3));
	}
	
}
