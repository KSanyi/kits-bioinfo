package kits.bioinfo.assembly;

import java.util.Arrays;

import org.junit.Test;

import kits.bioinfo.base.Sequence;

public class KmerGraphTest {

	@Test
	public void buildGraphFromNodes() {
		KmerGraph graph = KmerGraph.buildFromKmerNodesList(Arrays.asList(
				new Sequence("ATGCG"),
				new Sequence("GCATG"),
				new Sequence("CATGC"),
				new Sequence("AGGCA"),
				new Sequence("GGCAT")), 4);
		System.out.println(graph.printEdges());
	}
	
	@Test
	public void buildGraphFromEdges() {
		Sequence sequence = new Sequence("AAGATTCTCTAAGA");
		KmerGraph graph = KmerGraph.buildDeBrujinGraph(KmerCompositioner.generateCompositions(sequence, 3));
		System.out.println(graph.print());
	}
	
}
