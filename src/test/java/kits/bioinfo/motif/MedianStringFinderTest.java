package kits.bioinfo.motif;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import java.util.HashSet;
import java.util.Set;

import kits.bioinfo.base.Sequence;

import org.junit.Test;

public class MedianStringFinderTest {

	private MedianStringFinder medianStringFinder = new MedianStringFinder();
	
	@Test
	public void basic() {
		assertThat(medianStringFinder.findMedianStrings(new HashSet<>(asList(new Sequence("AA"))), 2), contains(new Sequence("AA")));
		assertThat(medianStringFinder.findMedianStrings(new HashSet<>(asList(new Sequence("AAC"),new Sequence("AAG"))), 2), contains(new Sequence("AA")));
	}
	
	@Test
	public void test1() {
		Set<Sequence> sequences = new HashSet<>(asList(
				new Sequence("AAATTGACGCAT"),
				new Sequence("GACGACCACGTT"),
				new Sequence("CGTCAGCGCCTG"),
				new Sequence("GCTGAGCACCGG"),
				new Sequence("AGTTCGGGACAG")));
		Set<Sequence> medianStrings = new MedianStringFinder().findMedianStrings(sequences, 3);
		assertThat(medianStrings, contains(new Sequence("GAC")));
	}
	
	@Test
	public void test2() {
		Set<Sequence> sequences = new HashSet<>(asList(
				new Sequence("CTCGATGAGTAGGAAAGTAGTTTCACTGGGCGAACCACCCCGGCGCTAATCCTAGTGCCC"),
				new Sequence("GCAATCCTACCCGAGGCCACATATCAGTAGGAACTAGAACCACCACGGGTGGCTAGTTTC"),
				new Sequence("GGTGTTGAACCACGGGGTTAGTTTCATCTATTGTAGGAATCGGCTTCAAATCCTACACAG")));
		Set<Sequence> medianStrings = new MedianStringFinder().findMedianStrings(sequences, 7);
		assertThat(medianStrings, containsInAnyOrder(new Sequence("GTAGGAA"), new Sequence("AATCCTA"), new Sequence("TAGTTTC"), new Sequence("GAACCAC")));
	}
	
}
