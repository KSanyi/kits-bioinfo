package kits.bioinfo.util;

public class Pair<T> {

	public final T first;
	public final T second;
	
	public Pair(T first, T second) {
		super();
		this.first = first;
		this.second = second;
	}
	
	@Override
	public String toString(){
		return "<" + first + ", " + second + ">";
	}
	
	@Override
	public boolean equals(Object other){
		@SuppressWarnings("unchecked")
		Pair<T> otherPair = (Pair<T>) other;
		return otherPair.first.equals(first) && otherPair.second.equals(second); 
	}
	
	@Override
	public int hashCode(){
		return first.hashCode() * 37 + second.hashCode();
	}
	
}
