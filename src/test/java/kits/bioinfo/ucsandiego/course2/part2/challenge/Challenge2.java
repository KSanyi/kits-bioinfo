package kits.bioinfo.ucsandiego.course2.part2.challenge;

import static java.util.stream.Collectors.toList;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import kits.bioinfo.assembly.KmerCompositioner;
import kits.bioinfo.core.DnaSequence;
import kits.bioinfo.core.Peptid;
import kits.bioinfo.core.PeptidCreator;

public class Challenge2 {

    /**
     * CODE CHALLENGE: Solve the Peptide Encoding Problem. Peptide Encoding
     * Problem: Find substrings of a genome encoding a given amino acid
     * sequence. Input: A DNA string Text, an amino acid string Peptide, and the
     * array GeneticCode. Output: All substrings of Text encoding Peptide (if
     * any such substrings exist).
     */
    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("input/dataset_96_8.txt"));
        DnaSequence dnaSequence = new DnaSequence(lines.get(0));
        Peptid goalPeptid = new Peptid(lines.get(1));
        List<DnaSequence> candidates = KmerCompositioner.generateCompositions(dnaSequence, goalPeptid.length() * 3);

        List<DnaSequence> codingSequences = candidates.stream().filter(candidate -> goalPeptid.equals(PeptidCreator.translateAndTranscribe(candidate))
                || goalPeptid.equals(PeptidCreator.translateAndTranscribe(candidate.reverseComplement()))).collect(toList());
        Files.write(Paths.get("./output/output_96_8.txt"), codingSequences.stream().map(seq -> seq.toString()).collect(toList()));
    }

}
