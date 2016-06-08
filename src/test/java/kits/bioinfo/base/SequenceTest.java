package kits.bioinfo.base;

import java.util.Arrays;
import java.util.HashSet;

import kits.bioinfo.core.Sequence;
import kits.bioinfo.util.RandomSequenceGenerator;

import org.junit.Assert;
import org.junit.Test;

public class SequenceTest {

	@Test
	public void reverseComplementBasecases() {
		Assert.assertEquals(new Sequence(""), new Sequence("").reverseComplement());
		Assert.assertEquals(new Sequence("A"), new Sequence("T").reverseComplement());
		Assert.assertEquals(new Sequence("C"), new Sequence("G").reverseComplement());
		Assert.assertEquals(new Sequence("TG"), new Sequence("CA").reverseComplement());
	}
	
	@Test
	public void reverseComplement() {
		Assert.assertEquals(new Sequence("ACCGGGTTTT"), new Sequence("AAAACCCGGT").reverseComplement());
	}
	
	@Test
	public void reverseComplementOfReverseComplementIsTheOriginalSequence() {
		int NR_OF_TESTS = 1000;
		int SEQUENCE_LENGTH = 1000;
		
		RandomSequenceGenerator generator = new RandomSequenceGenerator();
		for(int i=0;i<NR_OF_TESTS;i++) {
			Sequence sequence = generator.generateRandomSequence(SEQUENCE_LENGTH);
			Assert.assertEquals(sequence, sequence.reverseComplement().reverseComplement());
		}
	}
	
	@Test
	public void subSequence() {
		Assert.assertEquals(new Sequence("A"), new Sequence("ACTGCTGAC").subSequence(0, 1));
		Assert.assertEquals(new Sequence("ACTG"), new Sequence("ACTGCTGAC").subSequence(0, 4));
		Assert.assertEquals(new Sequence("TGAC"), new Sequence("ACTGCTGAC").subSequence(5, 4));
		Assert.assertEquals(new Sequence("TGCT"), new Sequence("ACTGCTGAC").subSequence(2, 4));
	}
	
	@Test
	public void prefix() {
		Assert.assertEquals(new Sequence("ACT"), new Sequence("ACTGCTGAC").prefix(3));
		Assert.assertEquals(new Sequence(""), new Sequence("ACTGCTGAC").prefix(0));
		Assert.assertEquals(new Sequence("ACTGCTGAC"), new Sequence("ACTGCTGAC").prefix(9));
	}
	
	@Test
	public void suffix() {
		Assert.assertEquals(new Sequence("GAC"), new Sequence("ACTGCTGAC").suffix(3));
		Assert.assertEquals(new Sequence(""), new Sequence("ACTGCTGAC").suffix(0));
		Assert.assertEquals(new Sequence("ACTGCTGAC"), new Sequence("ACTGCTGAC").suffix(9));
	}
	
	@Test
	public void distance() {
		Assert.assertEquals(0, new Sequence("A").distance(new Sequence("A")));
		Assert.assertEquals(0, new Sequence("ACTG").distance(new Sequence("ACTG")));
		Assert.assertEquals(1, new Sequence("A").distance(new Sequence("C")));
		Assert.assertEquals(1, new Sequence("ACTG").distance(new Sequence("ACTT")));
	}
	
	@Test
	public void neighbours() {
		Assert.assertEquals(new HashSet<>(Arrays.asList(new Sequence("A"), new Sequence("C"), new Sequence("T"),new Sequence("G"))), new Sequence("A").neighbours(1));
		Assert.assertEquals(13, new Sequence("ACCT").neighbours(1).size());
		Assert.assertEquals(67, new Sequence("ACCT").neighbours(2).size());
	}
	
	
}
