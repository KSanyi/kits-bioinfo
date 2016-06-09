package kits.bioinfo.core;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Peptid {

	private final List<AminoAcid> aminoAcids;
	
	public Peptid(String aminoAcidCodes) {
		this(aminoAcidCodes.chars().mapToObj(c -> AminoAcid.of((char)c)).collect(Collectors.toList()));
	}
	
	public Peptid(List<AminoAcid> aminoAcids) {
		this.aminoAcids = Collections.unmodifiableList(aminoAcids);
	}
	
	public int size(){
		return aminoAcids.size();
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
