package kits.bioinfo.core;

import static java.util.stream.Collectors.toList;

import java.util.LinkedList;
import java.util.List;

public class Ribosome {

    public static Peptid translateToAminoAcidSequence(RnaSequence rnaSequence) {

        List<Codon> codons = getCodonsFromRnaSequence(rnaSequence);

        List<AminoAcid> aminoAcids = codons.stream()
                .flatMap(codon -> Codon.aminoAcid(codon).stream())
                .collect(toList());

        return new Peptid(aminoAcids);
    }

    private static List<Codon> getCodonsFromRnaSequence(RnaSequence rnaSequence) {
        List<Codon> codons = new LinkedList<>();
        for (int i = 0; i + 2 < rnaSequence.length(); i += 3) {
            codons.add(new Codon(rnaSequence.subSequence(i, 3)));
        }
        return List.copyOf(codons);
    }

}
