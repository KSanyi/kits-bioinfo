package kits.bioinfo.assembly;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import kits.bioinfo.core.DnaSequence;

public class ReadPairAssemblerTest {

    @Test
    public void test() {
        List<ReadPair> readPairs = List.of(
                                        "ACC|ATA", 
                                        "ACT|ATT", 
                                        "ATA|TGA", 
                                        "ATT|TGA",
                                        "CAC|GAT",
                                        "CCG|TAC", 
                                        "CGA|ACT", 
                                        "CTG|AGC", 
                                        "CTG|TTC",
                                        "GAA|CTT",
                                        "GAT|CTG",
                                        "GAT|CTG", 
                                        "TAC|GAT", 
                                        "TCT|AAG", 
                                        "TGA|GCT", 
                                        "TGA|TCT", 
                                        "TTC|GAA")
                .stream().map(s -> new ReadPair(new DnaSequence(s.split("\\|")[0]), new DnaSequence(s.split("\\|")[1]), 1))
                .collect(toList());

        Optional<DnaSequence> sequence = ReadPairAssembler.assembleSequence(readPairs);

        assertTrue(sequence.isPresent());

        DnaSequence expectedSequence = new DnaSequence("CACCGATACTGATTCTGAAGCTT");
        assertEquals(expectedSequence, sequence.get());
    }

}
