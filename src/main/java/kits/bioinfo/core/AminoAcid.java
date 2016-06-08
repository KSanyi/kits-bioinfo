package kits.bioinfo.core;

import java.util.Arrays;
import java.util.Optional;

public enum AminoAcid {

	Alanine('A', "ala"), Arginine('R', "arg"), Asparagine('N', "asn"), Aspartic('D', "asp"), Cysteine('C', "cys"),
	Glutamine('Q', "gln"), Glutamic('E', "glu"), Glycine('G', "gly"), Histidine('H', "his"), Isoleucine('I', "ile"),
	Leucine('L', "leu"), Lysine('K', "lys"), Methionine('M', "met"), Phenylalanine('F', "phe"), Proline('P', "pro"),
	Serine('S', "ser"), Threonine('T', "thr"), Tryptophan('W', "trp"), Tyrosine('Y', "tyr"), Valine('V', "val");

	public final Character code1;
	public final String code3;
	
	private AminoAcid(char code1, String code3){
		this.code1 = code1;
		this.code3 = code3;
	}
	
	public static AminoAcid of(char code1) {
		Optional<AminoAcid> aminoAcid = Arrays.asList(values()).stream().filter(a -> a.code1 == code1).findAny();
		if(!aminoAcid.isPresent()) throw new IllegalArgumentException("There is no amino acid with one letter code '" + code1 + "'");
		return aminoAcid.get();
	}
	
	public static AminoAcid of(String code3) {
		Optional<AminoAcid> aminoAcid = Arrays.asList(values()).stream().filter(a -> a.code3.equals(code3)).findAny();
		if(!aminoAcid.isPresent()) throw new IllegalArgumentException("There is no amino acid with three letter code '" + code3 + "'");
		return aminoAcid.get();
	}
	
}
