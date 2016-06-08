package kits.bioinfo.core;

import java.util.List;
import java.util.Optional;

public class Codon extends RnaSequence {

	public Codon(RnaSequence rnaSequence) {
		this(rnaSequence.text);
	}
	
	public Codon(List<RnaBase> text) {
		super(text);
		if(text.size() != 3) throw new IllegalArgumentException("A codon must have 3 RNA bases");
	}
	
	public Codon(String text) {
		super(text);
		if(text.length() != 3) throw new IllegalArgumentException("A codon must have 3 RNA bases");
	}
	
	public static Optional<AminoAcid> aminoAcid(Codon codon) {
		switch(codon.toString()) {
			case"AAA": return Optional.of(AminoAcid.Asparagine);
			case"AAC": return Optional.of(AminoAcid.Asparagine);
			case"AAG": return Optional.of(AminoAcid.Asparagine);
			case"AAU": return Optional.of(AminoAcid.Asparagine);
			case"ACA": return Optional.of(AminoAcid.Threonine);
			case"ACC": return Optional.of(AminoAcid.Threonine);
			case"ACG": return Optional.of(AminoAcid.Threonine);
			case"ACU": return Optional.of(AminoAcid.Threonine);
			case"AGA": return Optional.of(AminoAcid.Arginine);
			case"AGC": return Optional.of(AminoAcid.Serine);
			case"AGG": return Optional.of(AminoAcid.Arginine);
			case"AGU": return Optional.of(AminoAcid.Serine);
			case"AUA": return Optional.of(AminoAcid.Isoleucine);
			case"AUC": return Optional.of(AminoAcid.Isoleucine);
			case"AUG": return Optional.of(AminoAcid.Methionine);
			case"AUU": return Optional.of(AminoAcid.Isoleucine);
			case"CAA": return Optional.of(AminoAcid.Glutamine);
			case"CAC": return Optional.of(AminoAcid.Histidine);
			case"CAG": return Optional.of(AminoAcid.Glutamine);
			case"CAU": return Optional.of(AminoAcid.Histidine);
			case"CCA": return Optional.of(AminoAcid.Proline);
			case"CCC": return Optional.of(AminoAcid.Proline);
			case"CCG": return Optional.of(AminoAcid.Proline);
			case"CCU": return Optional.of(AminoAcid.Proline);
			case"CGA": return Optional.of(AminoAcid.Arginine);
			case"CGC": return Optional.of(AminoAcid.Arginine);
			case"CGG": return Optional.of(AminoAcid.Arginine);
			case"CGU": return Optional.of(AminoAcid.Arginine);
			case"CUA": return Optional.of(AminoAcid.Leucine);
			case"CUC": return Optional.of(AminoAcid.Leucine);
			case"CUG": return Optional.of(AminoAcid.Leucine);
			case"CUU": return Optional.of(AminoAcid.Leucine);
			case"GAA": return Optional.of(AminoAcid.Glutamic);
			case"GAC": return Optional.of(AminoAcid.Aspartic);
			case"GAG": return Optional.of(AminoAcid.Glutamic);
			case"GAU": return Optional.of(AminoAcid.Aspartic);
			case"GCA": return Optional.of(AminoAcid.Alanine);
			case"GCC": return Optional.of(AminoAcid.Alanine);
			case"GCG": return Optional.of(AminoAcid.Alanine);
			case"GCU": return Optional.of(AminoAcid.Alanine);
			case"GGA": return Optional.of(AminoAcid.Glycine);
			case"GGC": return Optional.of(AminoAcid.Glycine);
			case"GGG": return Optional.of(AminoAcid.Glycine);
			case"GGU": return Optional.of(AminoAcid.Glycine);
			case"GUA": return Optional.of(AminoAcid.Valine);
			case"GUC": return Optional.of(AminoAcid.Valine);
			case"GUG": return Optional.of(AminoAcid.Valine);
			case"GUU": return Optional.of(AminoAcid.Valine);
			case"UAC": return Optional.of(AminoAcid.Tyrosine);
			case"UAU": return Optional.of(AminoAcid.Tyrosine);
			case"UCA": return Optional.of(AminoAcid.Serine);
			case"UCC": return Optional.of(AminoAcid.Serine);
			case"UCG": return Optional.of(AminoAcid.Serine);
			case"UCU": return Optional.of(AminoAcid.Serine);
			case"UGC": return Optional.of(AminoAcid.Cysteine);
			case"UGG": return Optional.of(AminoAcid.Tryptophan);
			case"UGU": return Optional.of(AminoAcid.Cysteine);
			case"UUA": return Optional.of(AminoAcid.Leucine);
			case"UUC": return Optional.of(AminoAcid.Phenylalanine);
			case"UUG": return Optional.of(AminoAcid.Leucine);
			case"UUU": return Optional.of(AminoAcid.Phenylalanine);
			default: return Optional.empty();
		}
	}

}
