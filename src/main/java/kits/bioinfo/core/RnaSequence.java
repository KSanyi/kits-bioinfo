package kits.bioinfo.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class RnaSequence implements Comparable<RnaSequence>{
	
	protected final List<RnaBase> text;

	public RnaSequence(List<RnaBase> text) {
		this.text = Collections.unmodifiableList(text);
	}
	
	public RnaSequence(String text) {
		this(text.chars().mapToObj(c -> RnaBase.of((char)c)).collect(Collectors.toList())); 
	}

	public RnaBase position(int index) {
		return text.get(index);
	}
	
	public int length() {
		return text.size();
	}
	
	public RnaSequence subSequence(int start, int k) {
		if(start < 0 || start + k > text.size()) throw new IllegalArgumentException("Wrong sub sequence index: " + start  + " : " + (start+k));
		return new RnaSequence(text.subList(start, start+k));
	}
	
	public RnaSequence prefix(int k){
		if(length() < k) throw new IllegalArgumentException("Can not form suffix with length " + k + " from sequence with length " + length());
		return subSequence(0, k);
	}
	
	public RnaSequence prefix(){
		if(isEmpty()) throw new IllegalStateException("Can not call prefix on empty sequence");
		return prefix(length()-1);
	}
	
	public RnaSequence suffix(int k){
		if(length() < k) throw new IllegalArgumentException("Can not form suffix with length " + k + " from sequence with length " + length());
		return subSequence(length()-k, k);
	}
	
	public RnaSequence suffix(){
		if(isEmpty()) throw new IllegalStateException("Can not call suffix on empty sequence");
		return suffix(length()-1);
	}
	
	public boolean isEmpty(){
		return length() == 0;
	}
	
	public List<RnaSequence> allSubSequences(int k) {
		List<RnaSequence> subSequences = new ArrayList<>();
		for(int i=0;i<length()-k+1;i++) {
			subSequences.add(subSequence(i, k));
		}
		return subSequences;
	}
	
	public RnaSequence append(RnaBase nucleotid) {
		List<RnaBase> newText = new ArrayList<>(text);
		newText.add(nucleotid);
		return new RnaSequence(newText);
	}
	
	public RnaSequence append(RnaSequence other) {
		List<RnaBase> newText = new ArrayList<>(text);
		newText.addAll(other.text);
		return new RnaSequence(newText);
	}
	
	public RnaSequence prepend(RnaBase nucleotid) {
		List<RnaBase> newText = new ArrayList<>();
		newText.add(nucleotid);
		newText.addAll(text);
		return new RnaSequence(newText);
	}
	
	public int distance(RnaSequence other) {
		if(other.length() != length()) throw new IllegalArgumentException("Different length sequences");
		int sum = 0;
		for(int i=0;i<length();i++) {
			sum += other.position(i) == position(i) ? 0 : 1;
		}
		return sum;
	}
	
	@Override
	public boolean equals(Object other) {
		return other instanceof RnaSequence ? text.equals(((RnaSequence)other).text) : false;
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
	public int compareTo(RnaSequence o) {
		return this.toString().compareTo(o.toString());
	}

}
