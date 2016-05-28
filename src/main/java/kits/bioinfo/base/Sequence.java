package kits.bioinfo.base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Sequence implements Comparable<Sequence>{
	
	private final List<Nucleotid> text;

	public Sequence(List<Nucleotid> text) {
		this.text = Collections.unmodifiableList(text);
	}
	
	public Sequence(String text) {
		this(text.chars().mapToObj(c -> Nucleotid.of((char)c)).collect(Collectors.toList())); 
	}

	public Nucleotid position(int index) {
		return text.get(index);
	}
	
	public int length() {
		return text.size();
	}
	
	public Sequence subSequence(int start, int k) {
		return new Sequence(text.subList(start, start+k));
	}
	
	public Sequence prefix(int k){
		return subSequence(0, k);
	}
	
	public Sequence prefix(){
		return prefix(length()-1);
	}
	
	public Sequence suffix(int k){
		return subSequence(length()-k, k);
	}
	
	public Sequence suffix(){
		return suffix(length()-1);
	}
	
	public List<Sequence> allSubSequences(int k) {
		List<Sequence> subSequences = new ArrayList<>();
		for(int i=0;i<length()-k+1;i++) {
			subSequences.add(subSequence(i, k));
		}
		return subSequences;
	}
	
	public Sequence append(Nucleotid nucleotid) {
		List<Nucleotid> newText = new ArrayList<>();
		newText.addAll(text);
		newText.add(nucleotid);
		return new Sequence(newText);
	}
	
	public Sequence prepend(Nucleotid nucleotid) {
		List<Nucleotid> newText = new ArrayList<>();
		newText.add(nucleotid);
		newText.addAll(text);
		return new Sequence(newText);
	}
	
	public Sequence reverseComplement() {
		List<Nucleotid> complementText = text.stream().map(n -> n.complement()).collect(Collectors.toList());
		Collections.reverse(complementText);
		return new Sequence(complementText);
	}
	
	public int distance(Sequence other) {
		if(other.length() != length()) throw new IllegalArgumentException("Different length sequences");
		int sum = 0;
		for(int i=0;i<length();i++) {
			sum += other.position(i) == position(i) ? 0 : 1;
		}
		return sum;
	}
	
	private Set<Sequence> neighbours() {
		Set<Sequence> neighbours = new HashSet<>();
		neighbours.add(this);
		for(int index=0;index<length();index++) {
			Nucleotid base = text.get(index);
			for(Nucleotid other : Nucleotid.others(base)) {
				List<Nucleotid> newText = new ArrayList<>(text);
				newText.set(index, other);
				neighbours.add(new Sequence(newText));
			}
		}
		return neighbours;
	}
	
	public Set<Sequence> neighbours(int k) {
		return neighboursOf(Collections.singleton(this), k);
	}
	
	private Set<Sequence> neighboursOf(Set<Sequence> sequences, int k) {
		if(k == 0) {
			return sequences;
		} else {
			Set<Sequence> neighbours = new HashSet<>();
			for(Sequence sequence : sequences) {
				neighbours.addAll(sequence.neighbours());
			}
			return neighboursOf(neighbours, k-1);
		}
	}
	
	@Override
	public boolean equals(Object other) {
		return other instanceof Sequence ? text.equals(((Sequence)other).text) : false;
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
	public int compareTo(Sequence o) {
		return this.toString().compareTo(o.toString());
	}

}
