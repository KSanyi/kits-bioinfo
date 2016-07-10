package kits.bioinfo.alignment;

import java.util.List;
import java.util.Optional;

import kits.bioinfo.core.AminoAcid;

public interface ScoreFunction<T> {

	int score(Optional<T> a, Optional<T> b);
	
	static <T> ScoreFunction<T> simpleScoreFunction(){
		return new SimpleScoreFunction<>();
	}
	
	static <T> ScoreFunction<T> basicScoreFunction(int mismatchPenalty, int indelPenalty){
		return new BasicScoreFunction<>(mismatchPenalty, indelPenalty);
	}
	
	static ScoreFunction<AminoAcid> blosum62ScoreFunction(int indelPenalty){
		return new Blosum62ScoreFunction(indelPenalty);
	}
	
}

class SimpleScoreFunction<T> implements ScoreFunction<T>{

	public int score(Optional<T> a, Optional<T> b) {
		if(!a.isPresent() && !b.isPresent()) throw new IllegalArgumentException("Score function called with two empty parameters");
		return a.equals(b) ? 1 : 0;
	}
	
}

class BasicScoreFunction<T> implements ScoreFunction<T>{

	private final int mismatchPenalty;
	private final int indelPenalty;
	
	public BasicScoreFunction(int mismatchPenalty, int indelPenalty) {
		this.mismatchPenalty = mismatchPenalty;
		this.indelPenalty = indelPenalty;
	}

	public int score(Optional<T> a, Optional<T> b) {
		if(!a.isPresent() && !b.isPresent()) throw new IllegalArgumentException("Score function called with two empty parameters");
		if(a.isPresent() && !b.isPresent() || !a.isPresent() && b.isPresent()) return -indelPenalty;
		if(!a.get().equals(b.get())) return -mismatchPenalty;
		return 1;
	}
	
}

class MatrixBasedScoreFunction<T> implements ScoreFunction<T>{
	
	private final List<T> elemList;
	private final int[][] scoreMatrix;
	private final int indelPenalty;

	public MatrixBasedScoreFunction(List<T> elemList, int[][] scoreMatrix, int indelPenalty) {
		this.elemList = elemList;
		this.scoreMatrix = scoreMatrix;
		this.indelPenalty = indelPenalty;
	}
	
	public int score(Optional<T> a, Optional<T> b) {
		if(!a.isPresent() && !b.isPresent()) throw new IllegalArgumentException("Score function called with two empty parameters");
		if(a.isPresent() && !b.isPresent() || !a.isPresent() && b.isPresent()) return -indelPenalty;
		return scoreMatrix[elemList.indexOf(a.get())][elemList.indexOf(b.get())];
	}
	
}
