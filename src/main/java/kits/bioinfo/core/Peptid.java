package kits.bioinfo.core;

import java.util.List;
import java.util.stream.Collectors;

public class Peptid extends Sequence<AminoAcid>{

	public Peptid(String aminoAcidCodes) {
		this(aminoAcidCodes.chars().mapToObj(c -> AminoAcid.of((char)c)).collect(Collectors.toList()));
	}
	
	public Peptid(List<AminoAcid> aminoAcids) {
		super(aminoAcids);
	}
	
	public Peptid(Sequence<AminoAcid> sequence) {
		super(sequence.text);
	}
	
	public Peptid append(AminoAcid aminoAcid) {
		return new Peptid(super.append(aminoAcid));
	}
	
	public Peptid subSequence(int start, int k) {
		return new Peptid(super.subSequence(start, k));
	}
	
	// just to have a better name for subsequence method
	public Peptid subPeptid(int start, int k){
		return subSequence(start, k);
	}
	
	public int mass() {
		return text.stream().mapToInt(aminoAcid -> aminoAcid.mass).sum();
	}
	
	@Override
	public String toString() {
		return text.stream().map(a -> a.code1.toString()).collect(Collectors.joining());
	}
	
	@Override
	public boolean equals(Object other) {
		return text.equals(((Peptid)other).text);
	}
	
}
