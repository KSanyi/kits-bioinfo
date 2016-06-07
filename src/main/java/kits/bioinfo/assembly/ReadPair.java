package kits.bioinfo.assembly;

import kits.bioinfo.base.Sequence;

public class ReadPair {

	public final Sequence read1;
	public final Sequence read2;
	
	public final int distance;

	public ReadPair(Sequence read1, Sequence read2, int distance) {
		this.read1 = read1;
		this.read2 = read2;
		this.distance = distance;
	}
	
	public ReadPair prefix() {
		return new ReadPair(read1.prefix(), read2.prefix(), distance);
	}
	
	public ReadPair suffix() {
		return new ReadPair(read1.suffix(), read2.suffix(), distance);
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder(read1.toString());
		for(int i=0;i<distance;i++){
			sb.append('.');
		}
		sb.append(read2.toString());
		return sb.toString();
	}
	
}
