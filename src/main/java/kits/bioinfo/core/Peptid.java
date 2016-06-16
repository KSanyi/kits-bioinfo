package kits.bioinfo.core;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Peptid {

	public final List<AminoAcid> aminoAcids;
	
	public Peptid(String aminoAcidCodes) {
		this(aminoAcidCodes.chars().mapToObj(c -> AminoAcid.of((char)c)).collect(Collectors.toList()));
	}
	
	public Peptid(List<AminoAcid> aminoAcids) {
		this.aminoAcids = Collections.unmodifiableList(aminoAcids);
	}
	
	public Peptid extendedPeptid(AminoAcid aminoAcid){
		List<AminoAcid> extendedAminoAcids = new LinkedList<>(aminoAcids);
		extendedAminoAcids.add(aminoAcid);
		return new Peptid(extendedAminoAcids);
	}
	
	public int length(){
		return aminoAcids.size();
	}
	
	public Peptid subPeptid(int start, int k) {
		if(start < 0 || start + k > aminoAcids.size()) throw new IllegalArgumentException("Wrong sub sequence index: " + start  + " : " + (start+k));
		return new Peptid(aminoAcids.subList(start, start+k));
	}
	
	public int mass() {
		return aminoAcids.stream().mapToInt(aminoAcid -> aminoAcid.mass).sum();
	}
	
	@Override
	public String toString() {
		return aminoAcids.stream().map(a -> a.code1.toString()).collect(Collectors.joining());
	}
	
	@Override
	public boolean equals(Object other) {
		if(other == this) return true;
		if(other == null) return false;
		if(!(other instanceof Peptid)) return false;
		return aminoAcids.equals(((Peptid)other).aminoAcids);
	}
	
}
