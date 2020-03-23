package kits.bioinfo.ucsandiego.course2.part2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import kits.bioinfo.core.Peptid;
import kits.bioinfo.peptidsequencing.CycloPeptidSequencer;

public class Challenge6 {

    /**
     * CODE CHALLENGE: Implement LEADERBOARDCYCLOPEPTIDESEQUENCING. Input: An
     * integer N and a collection of integers Spectrum. Output: LeaderPeptide
     * after running LEADERBOARDCYCLOPEPTIDESEQUENCING(Spectrum, N).
     */
    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("input/dataset_102_7.txt"));
        int cutRank = Integer.parseInt(lines.get(0));
        List<Integer> experimentalSpectrum = Arrays.asList(lines.get(1).split(" ")).stream().map(mass -> Integer.parseInt(mass))
                .collect(Collectors.toList());

        Set<Peptid> peptids = new CycloPeptidSequencer(cutRank, 20).sequencePeptids(experimentalSpectrum);

        Set<String> massSequences = peptids.stream()
                .map(peptid -> peptid.stream().map(aminoAcid -> String.valueOf(aminoAcid.mass)).collect(Collectors.joining("-")))
                .collect(Collectors.toSet());
        System.out.println(massSequences);
    }

}
