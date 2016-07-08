package kits.bioinfo.alignment;

import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;

import kits.bioinfo.TestUtil.EqualsInAnyOrder;
import kits.bioinfo.core.DnaSequence;

public class LongestCommonSubSequenceFinderTest {

	@Test
	public void test0() {
		DnaSequence sequence0 = new DnaSequence("");
		DnaSequence sequence1 = new DnaSequence("A");
		DnaSequence sequence2 = new DnaSequence("AC");
		DnaSequence sequence3 = new DnaSequence("CA");
		
		Collection<DnaSequence> sequences = LongestCommonSubSequenceFinder.findAllSequences(sequence0, sequence1);
		Assert.assertTrue(sequences.size() == 1);
		Assert.assertTrue(sequences.iterator().next().isEmpty());
		
		sequences = LongestCommonSubSequenceFinder.findAllSequences(sequence1, sequence1);
		Assert.assertTrue(sequences.size() == 1);
		Assert.assertEquals(new DnaSequence("A"), sequences.iterator().next());

		sequences = LongestCommonSubSequenceFinder.findAllSequences(sequence1, sequence2);
		Assert.assertTrue(sequences.size() == 1);
		Assert.assertEquals(new DnaSequence("A"), sequences.iterator().next());

		sequences = LongestCommonSubSequenceFinder.findAllSequences(sequence2, sequence3);
		Assert.assertTrue(sequences.size() == 2);
		Assert.assertThat(sequences, new EqualsInAnyOrder<>(new DnaSequence("A"), new DnaSequence("C")));
	}

	@Test
	public void test3() {
		DnaSequence sequence1 = new DnaSequence("AACCTTGG");
		DnaSequence sequence2 = new DnaSequence("ACACTGTGA");
		Collection<DnaSequence> sequences = LongestCommonSubSequenceFinder.findAllSequences(sequence1, sequence2);

		Assert.assertThat(sequences,
				new EqualsInAnyOrder<>(new DnaSequence("AACTTG"), new DnaSequence("AACTGG"), new DnaSequence("ACCTTG"), new DnaSequence("ACCTGG")));
	}

	@Test
	public void test4() {
		DnaSequence sequence1 = new DnaSequence("GCGATC");
		DnaSequence sequence2 = new DnaSequence("CTGACG");
		Collection<DnaSequence> sequences = LongestCommonSubSequenceFinder.findAllSequences(sequence1, sequence2);
		Assert.assertTrue(sequences.size() == 1);
		Assert.assertEquals(new DnaSequence("CGAC"), sequences.iterator().next());
	}

}
