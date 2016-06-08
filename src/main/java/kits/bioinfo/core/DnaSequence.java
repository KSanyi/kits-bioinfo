package kits.bioinfo.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DnaSequence implements Comparable<DnaSequence>{
	
	private final List<DnaBase> text;

	public DnaSequence(List<DnaBase> text) {
		this.text = Collections.unmodifiableList(text);
	}
	
	public DnaSequence(String text) {
		this(text.chars().mapToObj(c -> DnaBase.of((char)c)).collect(Collectors.toList())); 
	}

	public DnaBase position(int index) {
		return text.get(index);
	}
	
	public int length() {
		return text.size();
	}
	
	public DnaSequence subSequence(int start, int k) {
		if(start < 0 || start + k > text.size()) throw new IllegalArgumentException("Wrong sub sequence index: " + start  + " : " + (start+k));
		return new DnaSequence(text.subList(start, start+k));
	}
	
	public DnaSequence prefix(int k){
		if(length() < k) throw new IllegalArgumentException("Can not form suffix with length " + k + " from sequence with length " + length());
		return subSequence(0, k);
	}
	
	public DnaSequence prefix(){
		if(isEmpty()) throw new IllegalStateException("Can not call prefix on empty sequence");
		return prefix(length()-1);
	}
	
	public DnaSequence suffix(int k){
		if(length() < k) throw new IllegalArgumentException("Can not form suffix with length " + k + " from sequence with length " + length());
		return subSequence(length()-k, k);
	}
	
	public DnaSequence suffix(){
		if(isEmpty()) throw new IllegalStateException("Can not call suffix on empty sequence");
		return suffix(length()-1);
	}
	
	public boolean isEmpty(){
		return length() == 0;
	}
	
	public List<DnaSequence> allSubSequences(int k) {
		List<DnaSequence> subSequences = new ArrayList<>();
		for(int i=0;i<length()-k+1;i++) {
			subSequences.add(subSequence(i, k));
		}
		return subSequences;
	}
	
	public DnaSequence append(DnaBase nucleotid) {
		List<DnaBase> newText = new ArrayList<>(text);
		newText.add(nucleotid);
		return new DnaSequence(newText);
	}
	
	public DnaSequence append(DnaSequence other) {
		List<DnaBase> newText = new ArrayList<>(text);
		newText.addAll(other.text);
		return new DnaSequence(newText);
	}
	
	public DnaSequence prepend(DnaBase nucleotid) {
		List<DnaBase> newText = new ArrayList<>();
		newText.add(nucleotid);
		newText.addAll(text);
		return new DnaSequence(newText);
	}
	
	public DnaSequence reverseComplement() {
		List<DnaBase> complementText = text.stream().map(n -> n.complement()).collect(Collectors.toList());
		Collections.reverse(complementText);
		return new DnaSequence(complementText);
	}
	
	public int distance(DnaSequence other) {
		if(other.length() != length()) throw new IllegalArgumentException("Different length sequences");
		int sum = 0;
		for(int i=0;i<length();i++) {
			sum += other.position(i) == position(i) ? 0 : 1;
		}
		return sum;
	}
	
	private Set<DnaSequence> neighbours() {
		Set<DnaSequence> neighbours = new HashSet<>();
		neighbours.add(this);
		for(int index=0;index<length();index++) {
			DnaBase base = text.get(index);
			for(DnaBase other : DnaBase.others(base)) {
				List<DnaBase> newText = new ArrayList<>(text);
				newText.set(index, other);
				neighbours.add(new DnaSequence(newText));
			}
		}
		return neighbours;
	}
	
	public Set<DnaSequence> neighbours(int k) {
		return neighboursOf(Collections.singleton(this), k);
	}
	
	private Set<DnaSequence> neighboursOf(Set<DnaSequence> sequences, int k) {
		if(k == 0) {
			return sequences;
		} else {
			Set<DnaSequence> neighbours = new HashSet<>();
			for(DnaSequence sequence : sequences) {
				neighbours.addAll(sequence.neighbours());
			}
			return neighboursOf(neighbours, k-1);
		}
	}
	
	@Override
	public boolean equals(Object other) {
		return other instanceof DnaSequence ? text.equals(((DnaSequence)other).text) : false;
	}
	
	@Override
	public int hashCode() {
		return text.hashCode();
	}
	
	@Override
	public String toString() {
		return text.stream().map(n -> n.toString()).collect(Collectors.joining());
	}

	@Override
	public int compareTo(DnaSequence o) {
		return this.toString().compareTo(o.toString());
	}

}
