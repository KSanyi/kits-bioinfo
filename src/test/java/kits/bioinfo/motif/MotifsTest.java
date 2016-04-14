package kits.bioinfo.motif;

import java.util.Arrays;
import java.util.List;

import kits.bioinfo.base.Sequence;

import org.junit.Assert;
import org.junit.Test;

public class MotifsTest {

	@Test
	public void consensusString() {
		Assert.assertEquals(new Sequence("AAAA"), Motifs.consensusString(Arrays.asList(new Sequence("AAAA"))));
		
		Assert.assertEquals(new Sequence("ACTA"), Motifs.consensusString(Arrays.asList(new Sequence("ACAC"), 
																					   new Sequence("ACTA"),
																					   new Sequence("CCTA"))));
	}
	
	@Test
	public void score() {
		Assert.assertEquals(0, Motifs.score(Arrays.asList(new Sequence("AAAA"))));
		
		Assert.assertEquals(3, Motifs.score(Arrays.asList(new Sequence("ACAC"), 
							         				      new Sequence("ACTA"),
														  new Sequence("CCTA"))));
	}
	
	@Test
	public void score2() {
		
		List<Sequence> motifs = Arrays.asList(new Sequence("TCTCGGGG"), 
										      new Sequence("CCAAGGTG"),
											  new Sequence("TACAGGCG"),
											  new Sequence("TTCAGGTG"),
											  new Sequence("TCCACGTG"));
		
		Assert.assertEquals(new Sequence("TCCAGGTG"), Motifs.consensusString(motifs));
		Assert.assertEquals(11, Motifs.score(motifs));
	}
	
}
