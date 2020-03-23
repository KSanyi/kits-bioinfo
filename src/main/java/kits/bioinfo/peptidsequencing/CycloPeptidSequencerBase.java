package kits.bioinfo.peptidsequencing;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import kits.bioinfo.core.AminoAcid;
import kits.bioinfo.core.Peptid;

public abstract class CycloPeptidSequencerBase {

    public abstract Set<Peptid> sequencePeptids(List<Integer> spectrum);

    protected static Set<Peptid> extendedPeptids(Set<Peptid> peptids, Set<AminoAcid> aminoAcids) {
        Set<Peptid> extendedPeptids = new HashSet<>();
        for (Peptid peptid : peptids) {
            for (AminoAcid aminoAcid : aminoAcids) {
                extendedPeptids.add(peptid.append((aminoAcid)));
            }
        }
        return extendedPeptids;
    }
}
