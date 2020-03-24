package kits.bioinfo.ucsandiego.course2.part2;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import kits.bioinfo.core.Peptid;
import kits.bioinfo.peptidsequencing.SimpleCycloPeptidSequencer;

public class Challenge4 {

    /**
     * CODE CHALLENGE: Implement CYCLOPEPTIDESEQUENCING
     */
    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("input/dataset_100_5.txt"));
        List<Integer> massSpectrum = Arrays.asList(lines.get(0).split(" ")).stream().map(mass -> Integer.parseInt(mass)).collect(toList());
        Set<Peptid> peptids = new SimpleCycloPeptidSequencer().sequencePeptids(massSpectrum);

        Set<String> massSequences = peptids.stream()
                .map(peptid -> peptid.stream().map(aminoAcid -> String.valueOf(aminoAcid.mass)).collect(joining("-")))
                .collect(toSet());
        Files.write(Paths.get("./output/output_100_5.txt"), String.join(" ", massSequences).getBytes());
    }

}
