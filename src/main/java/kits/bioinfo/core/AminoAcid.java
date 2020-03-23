package kits.bioinfo.core;

import java.util.Arrays;
import java.util.Optional;

public enum AminoAcid {

    Alanine('A', "ala", 71), Arginine('R', "arg", 156), Asparagine('N', "asn", 114), Aspartic('D', "asp", 115), Cysteine('C', "cys", 103), Glutamine(
            'Q', "gln",
            128), Glutamic('E', "glu", 129), Glycine('G', "gly", 57), Histidine('H', "his", 137), Isoleucine('I', "ile", 113), Leucine('L', "leu",
                    113), Lysine('K', "lys", 128), Methionine('M', "met", 131), Phenylalanine('F', "phe", 147), Proline('P', "pro", 97), Serine('S',
                            "ser", 87), Threonine('T', "thr", 101), Tryptophan('W', "trp", 186), Tyrosine('Y', "tyr", 163), Valine('V', "val", 99);

    public final Character code1;
    public final String code3;
    public final int mass;

    private AminoAcid(char code1, String code3, int mass) {
        this.code1 = code1;
        this.code3 = code3;
        this.mass = mass;
    }

    public static AminoAcid of(char code1) {
        Optional<AminoAcid> aminoAcid = Arrays.asList(values()).stream().filter(a -> a.code1 == code1).findAny();
        if (!aminoAcid.isPresent())
            throw new IllegalArgumentException("There is no amino acid with one letter code '" + code1 + "'");
        return aminoAcid.get();
    }

    public static AminoAcid of(String code3) {
        Optional<AminoAcid> aminoAcid = Arrays.asList(values()).stream().filter(a -> a.code3.equals(code3)).findAny();
        if (!aminoAcid.isPresent())
            throw new IllegalArgumentException("There is no amino acid with three letter code '" + code3 + "'");
        return aminoAcid.get();
    }

}
