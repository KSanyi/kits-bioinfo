package kits.bioinfo.assembly;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;

import kits.bioinfo.core.DnaSequence;

public class ReadPairAssemblerTest {

	@Test
	public void test() {
		List<ReadPair> readPairs = Arrays
				.asList("ACC|ATA", "ACT|ATT", "ATA|TGA", "ATT|TGA", "CAC|GAT", "CCG|TAC", "CGA|ACT", "CTG|AGC", "CTG|TTC", "GAA|CTT", "GAT|CTG",
						"GAT|CTG", "TAC|GAT", "TCT|AAG", "TGA|GCT", "TGA|TCT", "TTC|GAA")
				.stream().map(s -> new ReadPair(new DnaSequence(s.split("\\|")[0]), new DnaSequence(s.split("\\|")[1]), 1))
				.collect(Collectors.toList());

		Optional<DnaSequence> sequence = ReadPairAssembler.assembleSequence(readPairs);

		Assert.assertTrue(sequence.isPresent());

		DnaSequence expectedSequence = new DnaSequence("CACCGATACTGATTCTGAAGCTT");
		Assert.assertEquals(expectedSequence, sequence.get());
	}

}
