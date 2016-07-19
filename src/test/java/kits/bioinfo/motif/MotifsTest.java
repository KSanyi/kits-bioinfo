package kits.bioinfo.motif;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import kits.bioinfo.core.DnaSequence;

public class MotifsTest {

	@Test
	public void consensusString() {
		Assert.assertEquals(new DnaSequence("AAAA"), Motifs.consensusString(Arrays.asList(new DnaSequence("AAAA"))));

		Assert.assertEquals(new DnaSequence("ACTA"),
				Motifs.consensusString(Arrays.asList(new DnaSequence("ACAC"), new DnaSequence("ACTA"), new DnaSequence("CCTA"))));
	}

	@Test
	public void score() {
		Assert.assertEquals(0, Motifs.score(Arrays.asList(new DnaSequence("AAAA"))));

		Assert.assertEquals(3, Motifs.score(Arrays.asList(new DnaSequence("ACAC"), new DnaSequence("ACTA"), new DnaSequence("CCTA"))));
	}

	@Test
	public void score2() {

		List<DnaSequence> motifs = Arrays.asList(new DnaSequence("TCTCGGGG"), new DnaSequence("CCAAGGTG"), new DnaSequence("TACAGGCG"),
				new DnaSequence("TTCAGGTG"), new DnaSequence("TCCACGTG"));

		Assert.assertEquals(new DnaSequence("TCCAGGTG"), Motifs.consensusString(motifs));
		Assert.assertEquals(9, Motifs.score(motifs));
	}

}
