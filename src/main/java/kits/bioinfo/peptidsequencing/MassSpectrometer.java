package kits.bioinfo.peptidsequencing;

import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import kits.bioinfo.core.AminoAcid;
import kits.bioinfo.core.Peptid;

public class MassSpectrometer {

    public static List<Integer> generateMassSpectrumForPeptid(Peptid peptid) {
        return generateMassSpectrum(peptid, false);
    }

    public static List<Integer> generateMassSpectrumForCyclidPeptid(Peptid cyclidPeptid) {
        return generateMassSpectrum(cyclidPeptid, true);
    }

    private static List<Integer> generateMassSpectrum(Peptid peptid, boolean cylic) {

        List<Peptid> allSubPeptids = new ArrayList<>();
        for (int k = 1; k < peptid.length(); k++) {
            allSubPeptids.addAll(cylic ? generateCyclicCompositions(peptid, k) : generateCompositions(peptid, k));
        }

        List<Integer> masses = allSubPeptids.stream().map(p -> p.mass()).collect(Collectors.toList());
        masses.add(0);
        masses.add(peptid.mass());

        Collections.sort(masses);
        return Collections.unmodifiableList(masses);
    }

    private static List<Peptid> generateCompositions(Peptid peptid, int k) {
        if (peptid.length() < k)
            throw new IllegalArgumentException("Sequence length must be >= k");

        return range(0, peptid.length() - k + 1).mapToObj(i -> peptid.subPeptid(i, k)).collect(toList());
    }

    private static List<Peptid> generateCyclicCompositions(Peptid cyclidPeptid, int k) {
        if (cyclidPeptid.length() < k)
            throw new IllegalArgumentException("Sequence length must be >= k");

        Peptid linearizedPeptidBuilder = new Peptid("");
        for (AminoAcid aminoAcid : cyclidPeptid) {
            linearizedPeptidBuilder = linearizedPeptidBuilder.append(aminoAcid);
        }
        for (AminoAcid aminoAcid : cyclidPeptid.prefix()) {
            linearizedPeptidBuilder = linearizedPeptidBuilder.append(aminoAcid);
        }
        Peptid linearizedPeptid = linearizedPeptidBuilder;
        return range(0, cyclidPeptid.length()).mapToObj(i -> linearizedPeptid.subPeptid(i, k)).collect(toList());
    }

}
