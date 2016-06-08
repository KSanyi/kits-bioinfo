package kits.bioinfo.core;

import java.util.Arrays;
import java.util.HashSet;

import kits.bioinfo.core.DnaSequence;
import kits.bioinfo.util.RandomSequenceGenerator;

import org.junit.Assert;
import org.junit.Test;

public class SequenceTest {

	@Test
	public void reverseComplementBasecases() {
		Assert.assertEquals(new DnaSequence(""), new DnaSequence("").reverseComplement());
		Assert.assertEquals(new DnaSequence("A"), new DnaSequence("T").reverseComplement());
		Assert.assertEquals(new DnaSequence("C"), new DnaSequence("G").reverseComplement());
		Assert.assertEquals(new DnaSequence("TG"), new DnaSequence("CA").reverseComplement());
	}
	
	@Test
	public void reverseComplement() {
		Assert.assertEquals(new DnaSequence("ACCGGGTTTT"), new DnaSequence("AAAACCCGGT").reverseComplement());
	}
	
	@Test
	public void reverseComplementOfReverseComplementIsTheOriginalSequence() {
		int NR_OF_TESTS = 1000;
		int SEQUENCE_LENGTH = 1000;
		
		RandomSequenceGenerator generator = new RandomSequenceGenerator();
		for(int i=0;i<NR_OF_TESTS;i++) {
			DnaSequence sequence = generator.generateRandomSequence(SEQUENCE_LENGTH);
			Assert.assertEquals(sequence, sequence.reverseComplement().reverseComplement());
		}
	}
	
	@Test
	public void subSequence() {
		Assert.assertEquals(new DnaSequence("A"), new DnaSequence("ACTGCTGAC").subSequence(0, 1));
		Assert.assertEquals(new DnaSequence("ACTG"), new DnaSequence("ACTGCTGAC").subSequence(0, 4));
		Assert.assertEquals(new DnaSequence("TGAC"), new DnaSequence("ACTGCTGAC").subSequence(5, 4));
		Assert.assertEquals(new DnaSequence("TGCT"), new DnaSequence("ACTGCTGAC").subSequence(2, 4));
	}
	
	@Test
	public void prefix() {
		Assert.assertEquals(new DnaSequence("ACT"), new DnaSequence("ACTGCTGAC").prefix(3));
		Assert.assertEquals(new DnaSequence(""), new DnaSequence("ACTGCTGAC").prefix(0));
		Assert.assertEquals(new DnaSequence("ACTGCTGAC"), new DnaSequence("ACTGCTGAC").prefix(9));
	}
	
	@Test
	public void suffix() {
		Assert.assertEquals(new DnaSequence("GAC"), new DnaSequence("ACTGCTGAC").suffix(3));
		Assert.assertEquals(new DnaSequence(""), new DnaSequence("ACTGCTGAC").suffix(0));
		Assert.assertEquals(new DnaSequence("ACTGCTGAC"), new DnaSequence("ACTGCTGAC").suffix(9));
	}
	
	@Test
	public void distance() {
		Assert.assertEquals(0, new DnaSequence("A").distance(new DnaSequence("A")));
		Assert.assertEquals(0, new DnaSequence("ACTG").distance(new DnaSequence("ACTG")));
		Assert.assertEquals(1, new DnaSequence("A").distance(new DnaSequence("C")));
		Assert.assertEquals(1, new DnaSequence("ACTG").distance(new DnaSequence("ACTT")));
	}
	
	@Test
	public void neighbours() {
		Assert.assertEquals(new HashSet<>(Arrays.asList(new DnaSequence("A"), new DnaSequence("C"), new DnaSequence("T"),new DnaSequence("G"))), new DnaSequence("A").neighbours(1));
		Assert.assertEquals(13, new DnaSequence("ACCT").neighbours(1).size());
		Assert.assertEquals(67, new DnaSequence("ACCT").neighbours(2).size());
	}
	
	
}
