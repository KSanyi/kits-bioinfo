package kits.bioinfo.core;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Ribosome {

	public List<AminoAcid> translateToAminoAcidSequence(RnaSequence rnaSequence) {
		
		List<Codon> codons = getCodonsFromRnaSequence(rnaSequence);
		
		List<AminoAcid> aminoAcids = codons.stream()
				.map(codon -> Codon.aminoAcid(codon))
				.filter(aminoAcid -> aminoAcid.isPresent())
				.map(aminoAcid -> aminoAcid.get()).collect(Collectors.toList());
		
		return Collections.unmodifiableList(aminoAcids);
	}
	
	private static List<Codon> getCodonsFromRnaSequence(RnaSequence rnaSequence) {
		List<Codon> codons = new LinkedList<>();
		for(int i=0;i+2<rnaSequence.length();i+=3){
			codons.add(new Codon(rnaSequence.subSequence(i, 3)));
		}
		return Collections.unmodifiableList(codons);
	}
	
}
