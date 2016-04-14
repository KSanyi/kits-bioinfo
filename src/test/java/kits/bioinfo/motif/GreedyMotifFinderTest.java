package kits.bioinfo.motif;

import java.util.Arrays;
import java.util.List;

import kits.bioinfo.base.Sequence;

import org.junit.Test;

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
