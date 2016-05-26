package kits.bioinfo.assembly;

import java.util.Arrays;

import org.junit.Test;

import kits.bioinfo.base.Sequence;

public class KmerGraphTest {

	@Test
	public void buildGraph() {
		KmerGraph graph = KmerGraph.buildFromKmerList(Arrays.asList(
				new Sequence("ATGCG"),
				new Sequence("GCATG"),
				new Sequence("CATGC"),
				new Sequence("AGGCA"),
				new Sequence("GGCAT")), 4);
		System.out.println(graph.print());
	}
	
}
