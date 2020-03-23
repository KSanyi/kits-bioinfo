package kits.bioinfo.ucsandiego.course2.part2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import kits.bioinfo.core.Peptid;
import kits.bioinfo.peptidsequencing.MassSpectrometer;

public class Challenge3 {

    /**
     * CODE CHALLENGE: Solve the Generating Theoretical Spectrum Problem.
     * Generating Theoretical Spectrum Problem: Generate the theoretical
     * spectrum of a cyclic peptide. Input: An amino acid string Peptide.
     */
    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("input/dataset_98_4.txt"));
        Peptid peptid = new Peptid(lines.get(0));
        List<Integer> massSpectrum = new MassSpectrometer().generateMassSpectrumForCyclidPeptid(peptid);
        System.out.println(massSpectrum.stream().map(mass -> mass.toString()).collect(Collectors.joining(" ")));
    }

}
