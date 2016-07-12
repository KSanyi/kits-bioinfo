package kits.bioinfo.alignment;

import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;

import kits.bioinfo.TestUtil.EqualsInAnyOrder;
import kits.bioinfo.core.DnaBase;
import kits.bioinfo.core.DnaSequence;
import kits.bioinfo.core.Sequence;

public class LongestCommonSubSequenceFinderTest {

	@Test
	public void test0() {
		DnaSequence sequence0 = new DnaSequence("");
		DnaSequence sequence1 = new DnaSequence("A");
		DnaSequence sequence2 = new DnaSequence("AC");
		DnaSequence sequence3 = new DnaSequence("CA");
		
		Collection<Sequence<DnaBase>> sequences = LongestCommonSubSequenceFinder.findAllSequences(sequence0, sequence1);
		Assert.assertTrue(sequences.size() == 1);
		Assert.assertTrue(sequences.iterator().next().isEmpty());
		
		sequences = LongestCommonSubSequenceFinder.findAllSequences(sequence1, sequence1);
		Assert.assertTrue(sequences.size() == 1);
		Assert.assertEquals(new DnaSequence("A").toSequence(), sequences.iterator().next());

		sequences = LongestCommonSubSequenceFinder.findAllSequences(sequence1, sequence2);
		Assert.assertTrue(sequences.size() == 1);
		Assert.assertEquals(new DnaSequence("A").toSequence(), sequences.iterator().next());

		sequences = LongestCommonSubSequenceFinder.findAllSequences(sequence2, sequence3);
		Assert.assertTrue(sequences.size() == 2);
		Assert.assertThat(sequences, new EqualsInAnyOrder<>(new DnaSequence("A").toSequence(), new DnaSequence("C").toSequence()));
	}

	@Test
	public void test1() {
		DnaSequence sequence1 = new DnaSequence("AACCTTGG");
		DnaSequence sequence2 = new DnaSequence("ACACTGTGA");
		Collection<Sequence<DnaBase>> sequences = LongestCommonSubSequenceFinder.findAllSequences(sequence1, sequence2);

		Assert.assertThat(sequences,
				new EqualsInAnyOrder<>(new DnaSequence("AACTTG").toSequence(), new DnaSequence("AACTGG").toSequence(),
						new DnaSequence("ACCTTG").toSequence(), new DnaSequence("ACCTGG").toSequence()));
	}

	@Test
	public void test2() {
		DnaSequence sequence1 = new DnaSequence("GCGATC");
		DnaSequence sequence2 = new DnaSequence("CTGACG");
		Collection<Sequence<DnaBase>> sequences = LongestCommonSubSequenceFinder.findAllSequences(sequence1, sequence2);
		Assert.assertTrue(sequences.size() == 1);
		Assert.assertEquals(new DnaSequence("CGAC").toSequence(), sequences.iterator().next());
	}

}
