package kits.bioinfo.core;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Codon extends RnaSequence {

	public Codon(RnaSequence rnaSequence) {
		this(rnaSequence.text);
	}

	public Codon(List<RnaBase> text) {
		super(text);
		if (text.size() != 3)
			throw new IllegalArgumentException("A codon must have 3 RNA bases");
	}

	public Codon(String text) {
		super(text);
		if (text.length() != 3)
			throw new IllegalArgumentException("A codon must have 3 RNA bases");
	}

	public static Optional<AminoAcid> aminoAcid(Codon codon) {
		return Optional.ofNullable(codonTable.get(codon));
	}

	public static final Map<Codon, AminoAcid> codonTable;

	static {
		Map<Codon, AminoAcid> table = new HashMap<>();
		table.put(new Codon("AAA"), AminoAcid.Lysine);
		table.put(new Codon("AAC"), AminoAcid.Asparagine);
		table.put(new Codon("AAG"), AminoAcid.Lysine);
		table.put(new Codon("AAU"), AminoAcid.Asparagine);
		table.put(new Codon("ACA"), AminoAcid.Threonine);
		table.put(new Codon("ACC"), AminoAcid.Threonine);
		table.put(new Codon("ACG"), AminoAcid.Threonine);
		table.put(new Codon("ACU"), AminoAcid.Threonine);
		table.put(new Codon("AGA"), AminoAcid.Arginine);
		table.put(new Codon("AGC"), AminoAcid.Serine);
		table.put(new Codon("AGG"), AminoAcid.Arginine);
		table.put(new Codon("AGU"), AminoAcid.Serine);
		table.put(new Codon("AUA"), AminoAcid.Isoleucine);
		table.put(new Codon("AUC"), AminoAcid.Isoleucine);
		table.put(new Codon("AUG"), AminoAcid.Methionine);
		table.put(new Codon("AUU"), AminoAcid.Isoleucine);
		table.put(new Codon("CAA"), AminoAcid.Glutamine);
		table.put(new Codon("CAC"), AminoAcid.Histidine);
		table.put(new Codon("CAG"), AminoAcid.Glutamine);
		table.put(new Codon("CAU"), AminoAcid.Histidine);
		table.put(new Codon("CCA"), AminoAcid.Proline);
		table.put(new Codon("CCC"), AminoAcid.Proline);
		table.put(new Codon("CCG"), AminoAcid.Proline);
		table.put(new Codon("CCU"), AminoAcid.Proline);
		table.put(new Codon("CGA"), AminoAcid.Arginine);
		table.put(new Codon("CGC"), AminoAcid.Arginine);
		table.put(new Codon("CGG"), AminoAcid.Arginine);
		table.put(new Codon("CGU"), AminoAcid.Arginine);
		table.put(new Codon("CUA"), AminoAcid.Leucine);
		table.put(new Codon("CUC"), AminoAcid.Leucine);
		table.put(new Codon("CUG"), AminoAcid.Leucine);
		table.put(new Codon("CUU"), AminoAcid.Leucine);
		table.put(new Codon("GAA"), AminoAcid.Glutamic);
		table.put(new Codon("GAC"), AminoAcid.Aspartic);
		table.put(new Codon("GAG"), AminoAcid.Glutamic);
		table.put(new Codon("GAU"), AminoAcid.Aspartic);
		table.put(new Codon("GCA"), AminoAcid.Alanine);
		table.put(new Codon("GCC"), AminoAcid.Alanine);
		table.put(new Codon("GCG"), AminoAcid.Alanine);
		table.put(new Codon("GCU"), AminoAcid.Alanine);
		table.put(new Codon("GGA"), AminoAcid.Glycine);
		table.put(new Codon("GGC"), AminoAcid.Glycine);
		table.put(new Codon("GGG"), AminoAcid.Glycine);
		table.put(new Codon("GGU"), AminoAcid.Glycine);
		table.put(new Codon("GUA"), AminoAcid.Valine);
		table.put(new Codon("GUC"), AminoAcid.Valine);
		table.put(new Codon("GUG"), AminoAcid.Valine);
		table.put(new Codon("GUU"), AminoAcid.Valine);
		table.put(new Codon("UAC"), AminoAcid.Tyrosine);
		table.put(new Codon("UAU"), AminoAcid.Tyrosine);
		table.put(new Codon("UCA"), AminoAcid.Serine);
		table.put(new Codon("UCC"), AminoAcid.Serine);
		table.put(new Codon("UCG"), AminoAcid.Serine);
		table.put(new Codon("UCU"), AminoAcid.Serine);
		table.put(new Codon("UGC"), AminoAcid.Cysteine);
		table.put(new Codon("UGG"), AminoAcid.Tryptophan);
		table.put(new Codon("UGU"), AminoAcid.Cysteine);
		table.put(new Codon("UUA"), AminoAcid.Leucine);
		table.put(new Codon("UUC"), AminoAcid.Phenylalanine);
		table.put(new Codon("UUG"), AminoAcid.Leucine);
		table.put(new Codon("UUU"), AminoAcid.Phenylalanine);
		codonTable = Collections.unmodifiableMap(table);
	}
}
