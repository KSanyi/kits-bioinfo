package kits.bioinfo.ucsandiego.course2.part1.challenge;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import kits.bioinfo.assembly.KmerCompositioner;
import kits.bioinfo.core.DnaSequence;
import kits.bioinfo.infrastructure.SequenceReader;

public class Challenge02 {

    /**
     * CODE CHALLENGE: Solve the String Spelled by a Genome Path Problem.
     */
    public static void main(String[] args) throws IOException {
        List<DnaSequence> composition = SequenceReader.readDnaSequencesPerLine("input/dataset_198_3.txt");
        DnaSequence sequence = KmerCompositioner.readSequenceFromComposition(composition);
        Files.write(Paths.get("./output/output_198_3.txt"), List.of(sequence.toString()));
    }

}
