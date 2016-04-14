package kits.bioinfo.motif;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import kits.bioinfo.base.Sequence;

import org.junit.Test;

public class GibbsSamplerTest {

	@Test
	public void test() {
		List<Sequence> sequences = Arrays.asList(
				new Sequence("CGCCCCTCTCGGGGGTGTTCAGTAACCGGCCA"),
				new Sequence("GGGCGAGGTATGTGTAAGTGCCAAGGTGCCAG"), 
				new Sequence("TAGTACCGAGACCGAAAGAAGTATACAGGCGT"), 
				new Sequence("TAGATCAAGTTTCAGGTGCACGTCGGTGAACC"),
				new Sequence("AATCCACCAGCTCCACGTGCAATGTTGGCCTA"));
		
		Set<Sequence> medianStrings = new MedianStringFinder().findMedianStrings(sequences, 8);
		
		System.out.println("Median strings: " + medianStrings);
		
		System.out.println("Distance from median strings: " + medianStrings.stream().map(medianString -> Motifs.distance(sequences, medianString)).collect(Collectors.toList()));
		
		List<Sequence> motifs = new GibbsSampler(20).findMotifs(sequences, 8, 100);
		System.out.println("Motifs found with Gibbs sampler motif finder: " + motifs);
		System.out.println("Motifs score: " + Motifs.score(motifs));
	}
	
}
