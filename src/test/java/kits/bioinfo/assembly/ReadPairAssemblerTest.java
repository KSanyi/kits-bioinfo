package kits.bioinfo.assembly;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;

import kits.bioinfo.core.Sequence;

public class ReadPairAssemblerTest {

	@Test
	public void test() {
		List<ReadPair> readPairs = Arrays.asList(
				"ACC|ATA", "ACT|ATT", "ATA|TGA", "ATT|TGA", "CAC|GAT", "CCG|TAC", "CGA|ACT",
				"CTG|AGC", "CTG|TTC", "GAA|CTT", "GAT|CTG", "GAT|CTG", "TAC|GAT", "TCT|AAG",
				"TGA|GCT", "TGA|TCT", "TTC|GAA").stream()
				.map(s -> new ReadPair(new Sequence(s.split("\\|")[0]), new Sequence(s.split("\\|")[1]), 1)).collect(Collectors.toList());
		
		Optional<Sequence> sequence = ReadPairAssembler.assembleSequence(readPairs);
		
		Assert.assertTrue(sequence.isPresent());
		
		Sequence expectedSequence = new Sequence("CACCGATACTGATTCTGAAGCTT");
		Assert.assertEquals(expectedSequence, sequence.get());
	}
	
}
