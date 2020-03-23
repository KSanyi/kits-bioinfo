package kits.bioinfo.ucsandiego.course2.part2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;

import kits.bioinfo.core.Peptid;
import kits.bioinfo.core.Ribosome;
import kits.bioinfo.core.RnaSequence;
import kits.bioinfo.infrastructure.SequenceReader;

public class Challenge1 {

    /**
     * CODE CHALLENGE: Solve the Protein Translation Problem. Protein
     * Translation Problem: Translate an RNA string into an amino acid string.
     * Input: An RNA string Pattern and the array GeneticCode. Output: The
     * translation of Pattern into an amino acid string Peptide.
     */
    public static void main(String[] args) throws IOException {
        RnaSequence rnaSequence = SequenceReader.readRnaSequenceFromFile("input/dataset_96_5.txt");
        Peptid peptid = new Ribosome().translateToAminoAcidSequence(rnaSequence);
        Files.write(Paths.get("./output/output_96_5.txt"), Collections.singletonList(peptid.toString()));
    }

}
