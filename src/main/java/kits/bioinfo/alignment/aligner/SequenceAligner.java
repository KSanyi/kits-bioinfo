package kits.bioinfo.alignment.aligner;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import kits.bioinfo.alignment.scorefunction.ScoreFunction;
import kits.bioinfo.core.Sequence;
import kits.bioinfo.util.Pair;

// TODO redesign this class
public class SequenceAligner<T> {

	private final ScoreFunction<T> scoreFunction;

	public SequenceAligner(ScoreFunction<T> scoreFunction) {
		this.scoreFunction = scoreFunction;
	}

	public AlignmentResult<T> findOneGlobalAlignment(Sequence<T> sequence1, Sequence<T> sequence2) {
		Table<T> table = new Table<>(sequence1, sequence2, scoreFunction);
		table.fillGlobalScoresMatrix();
		return table.findOneAlignment();
	}
	
	public AlignmentResult<T> findOneLocalAlignment(Sequence<T> sequence1, Sequence<T> sequence2) {
		Table<T> table = new Table<>(sequence1, sequence2, scoreFunction);
		table.fillLocalScoresMatrix();
		return table.findOneAlignment();
	}
	
	public AlignmentResult<T> findOneFittingAlignment(Sequence<T> longSequence, Sequence<T> sequence) {
		Table<T> table = new Table<>(longSequence, sequence, scoreFunction);
		table.fillFittingScoresMatrix();
		return table.findOneAlignment();
	}
	
	public AlignmentResult<T> findOneOverlappingAlignment(Sequence<T> longSequence, Sequence<T> sequence) {
		Table<T> table = new Table<>(longSequence, sequence, scoreFunction);
		table.fillOverlappingScoresMatrix();
		return table.findOneAlignment();
	}

	// TODO runs very slow, need to modify the recursion using dynamic algorithm
	public Set<AlignmentResult<T>> findAllGlobalAlignments(Sequence<T> sequence1, Sequence<T> sequence2) {
		Table<T> table = new Table<>(sequence1, sequence2, scoreFunction);
		table.fillGlobalScoresMatrix();
		return table.findAllAlignments();
	}

	private static class Table<T> {

		private static class Cell {
			final int score;
			final Set<Pair<Integer>> parents;
			
			public Cell(int score, Set<Pair<Integer>> parents) {
				this.score = score;
				this.parents = parents;
			}
			
			public String toString(){
				return String.valueOf(score);
			}
		}
		
		private Cell[][] scoresMatrix;

		private final Sequence<T> sequence1;
		private final Sequence<T> sequence2;

		private final ScoreFunction<T> scoreFunction;

		Table(Sequence<T> sequence1, Sequence<T> sequence2, ScoreFunction<T> scoreFunction) {
			this.sequence1 = sequence1;
			this.sequence2 = sequence2;
			this.scoreFunction = scoreFunction;
		}

		Cell[][] fillLocalScoresMatrix() {
			scoresMatrix = new Cell[sequence1.length() + 1][sequence2.length() + 1];
			scoresMatrix[0][0] = new Cell(0, Collections.emptySet());
			for (int i = 1; i <= sequence1.length(); i++) {
				int score1 = scoresMatrix[i-1][0].score + scoreFunction.score(Optional.of(sequence1.position(i-1)), Optional.empty());
				int score = max(0, score1);
				Set<Pair<Integer>> parents = new HashSet<>();
				if(score == 0) parents.add(new Pair<>(0, 0));
				if(score == score1) parents.add(new Pair<>(i-1, 0));
				scoresMatrix[i][0] = new Cell(score, parents);
			}
			for (int j = 1; j <= sequence2.length(); j++) {
				int score1 = scoresMatrix[0][j-1].score + scoreFunction.score(Optional.empty(), Optional.of(sequence2.position(j-1)));
				int score = max(0, score1);
				Set<Pair<Integer>> parents = new HashSet<>();
				if(score == 0) parents.add(new Pair<>(0, 0));
				if(score == score1) parents.add(new Pair<>(0, j-1));
				scoresMatrix[0][j] = new Cell(score, parents);
			}

			for (int i = 1; i <= sequence1.length(); i++) {
				for (int j = 1; j <= sequence2.length(); j++) {
					
					int score1 = scoresMatrix[i - 1][j].score + scoreFunction.score(Optional.of(sequence1.position(i - 1)), Optional.empty());
					int score2 = scoresMatrix[i][j - 1].score + scoreFunction.score(Optional.empty(), Optional.of(sequence2.position(j - 1)));
					int score3 = scoresMatrix[i - 1][j - 1].score + scoreFunction.score(Optional.of(sequence1.position(i - 1)), Optional.of(sequence2.position(j - 1)));
					
					int score = max(0, score1, score2, score3);
					Set<Pair<Integer>> parents = new HashSet<>();
					if(score == 0) parents.add(new Pair<>(0, 0));
					if(score == score1) parents.add(new Pair<>(i-1, j));
					if(score == score2) parents.add(new Pair<>(i, j-1));
					if(score == score3) parents.add(new Pair<>(i-1, j-1));
					
					scoresMatrix[i][j] = new Cell(score, parents);
				}
			}
			
			Cell bottomCell = scoresMatrix[sequence1.length()][sequence2.length()];
			int max = Integer.MIN_VALUE;
			for (int i = 1; i <= sequence1.length(); i++) {
				for (int j = 1; j <= sequence2.length(); j++) {
					if(scoresMatrix[i][j].score > max){
						max = scoresMatrix[i][j].score;
					}
				}
			}
			Set<Pair<Integer>> parents = new HashSet<>();
			if(max >= bottomCell.score){
				for (int i = 1; i <= sequence1.length(); i++) {
					for (int j = 1; j <= sequence2.length(); j++) {
						if(scoresMatrix[i][j].score == max){
							parents.add(new Pair<>(i, j));
						}
					}
				}
				if(max == bottomCell.score){
					parents.addAll(bottomCell.parents);
				}
				scoresMatrix[sequence1.length()][sequence2.length()] = new Cell(max, parents);
			}
			
			//System.out.println(this);
			return scoresMatrix;
		}
		
		Cell[][] fillFittingScoresMatrix() {
			scoresMatrix = new Cell[sequence1.length() + 1][sequence2.length() + 1];
			scoresMatrix[0][0] = new Cell(0, Collections.emptySet());
			
			for (int i = 1; i <= sequence1.length(); i++) {
				int score1 = scoresMatrix[i-1][0].score + scoreFunction.score(Optional.of(sequence1.position(i-1)), Optional.empty());
				int score = max(0, score1);
				Set<Pair<Integer>> parents = new HashSet<>();
				if(score == 0) parents.add(new Pair<>(0, 0));
				if(score == score1) parents.add(new Pair<>(i-1, 0));
				scoresMatrix[i][0] = new Cell(score, parents);
			}
			
			for (int j = 1; j <= sequence2.length(); j++) {
				int score = scoresMatrix[0][j-1].score + scoreFunction.score(Optional.empty(), Optional.of(sequence2.position(j-1)));
				scoresMatrix[0][j] = new Cell(score, Collections.singleton(new Pair<>(0, j-1)));
			}

			for (int i = 1; i <= sequence1.length(); i++) {
				for (int j = 1; j <= sequence2.length(); j++) {
					
					int score1 = scoresMatrix[i - 1][j].score + scoreFunction.score(Optional.of(sequence1.position(i - 1)), Optional.empty());
					int score2 = scoresMatrix[i][j - 1].score + scoreFunction.score(Optional.empty(), Optional.of(sequence2.position(j - 1)));
					int score3 = scoresMatrix[i - 1][j - 1].score + scoreFunction.score(Optional.of(sequence1.position(i - 1)), Optional.of(sequence2.position(j - 1)));
					
					int score = max(score1, score2, score3);
					Set<Pair<Integer>> parents = new HashSet<>();
					if(score == score1) parents.add(new Pair<>(i-1, j));
					if(score == score2) parents.add(new Pair<>(i, j-1));
					if(score == score3) parents.add(new Pair<>(i-1, j-1));
					
					scoresMatrix[i][j] = new Cell(score, parents);
				}
			}
			
			Cell bottomCell = scoresMatrix[sequence1.length()][sequence2.length()];
			int max = Integer.MIN_VALUE;
			for (int i = 1; i <= sequence1.length(); i++) {
				if(scoresMatrix[i][sequence2.length()].score > max){
					max = scoresMatrix[i][sequence2.length()].score;
				}
			}
			Set<Pair<Integer>> parents = new HashSet<>();
			if(max >= bottomCell.score){
				for (int i = 1; i <= sequence1.length(); i++) {
					if(scoresMatrix[i][sequence2.length()].score == max){
						parents.add(new Pair<>(i, sequence2.length()));
					}
				}
				if(max == bottomCell.score){
					parents.addAll(bottomCell.parents);
				}
				scoresMatrix[sequence1.length()][sequence2.length()] = new Cell(max, parents);
			}

			//System.out.println(this);
			
			return scoresMatrix;
		}
		
		Cell[][] fillOverlappingScoresMatrix() {
			scoresMatrix = new Cell[sequence1.length() + 1][sequence2.length() + 1];
			scoresMatrix[0][0] = new Cell(0, Collections.emptySet());
			
			for (int i = 1; i <= sequence1.length(); i++) {
				int score1 = scoresMatrix[i-1][0].score + scoreFunction.score(Optional.of(sequence1.position(i-1)), Optional.empty());
				int score = max(0, score1);
				Set<Pair<Integer>> parents = new HashSet<>();
				if(score == 0) parents.add(new Pair<>(0, 0));
				if(score == score1) parents.add(new Pair<>(i-1, 0));
				scoresMatrix[i][0] = new Cell(score, parents);
			}
			
			for (int j = 1; j <= sequence2.length(); j++) {
				int score = scoresMatrix[0][j-1].score + scoreFunction.score(Optional.empty(), Optional.of(sequence2.position(j-1)));
				scoresMatrix[0][j] = new Cell(score, Collections.singleton(new Pair<>(0, j-1)));
			}

			for (int i = 1; i <= sequence1.length(); i++) {
				for (int j = 1; j <= sequence2.length(); j++) {
					
					int score1 = scoresMatrix[i - 1][j].score + scoreFunction.score(Optional.of(sequence1.position(i - 1)), Optional.empty());
					int score2 = scoresMatrix[i][j - 1].score + scoreFunction.score(Optional.empty(), Optional.of(sequence2.position(j - 1)));
					int score3 = scoresMatrix[i - 1][j - 1].score + scoreFunction.score(Optional.of(sequence1.position(i - 1)), Optional.of(sequence2.position(j - 1)));
					
					int score = max(score1, score2, score3);
					Set<Pair<Integer>> parents = new HashSet<>();
					if(score == score1) parents.add(new Pair<>(i-1, j));
					if(score == score2) parents.add(new Pair<>(i, j-1));
					if(score == score3) parents.add(new Pair<>(i-1, j-1));
					
					scoresMatrix[i][j] = new Cell(score, parents);
				}
			}
			
			Cell bottomCell = scoresMatrix[sequence1.length()][sequence2.length()];
			int max = Integer.MIN_VALUE;
			for (int j = 1; j <= sequence2.length(); j++) {
				if(scoresMatrix[sequence1.length()][j].score > max){
					max = scoresMatrix[sequence1.length()][j].score;
				}
			}
			Set<Pair<Integer>> parents = new HashSet<>();
			if(max >= bottomCell.score){
				for (int j = 1; j <= sequence2.length(); j++) {
					if(scoresMatrix[sequence1.length()][j].score == max){
						parents.add(new Pair<>(sequence1.length(), j));
					}
				}
				if(max == bottomCell.score){
					parents.addAll(bottomCell.parents);
				}
				scoresMatrix[sequence1.length()][sequence2.length()] = new Cell(max, parents);
			}

			//System.out.println(this);
			
			return scoresMatrix;
		}

		Cell[][] fillGlobalScoresMatrix() {
			scoresMatrix = new Cell[sequence1.length() + 1][sequence2.length() + 1];
			scoresMatrix[0][0] = new Cell(0, Collections.emptySet());
			for (int i = 1; i <= sequence1.length(); i++) {
				int score = scoresMatrix[i-1][0].score + scoreFunction.score(Optional.of(sequence1.position(i-1)), Optional.empty());
				scoresMatrix[i][0] = new Cell(score, Collections.singleton(new Pair<>(i-1, 0)));
			}
			for (int j = 1; j <= sequence2.length(); j++) {
				int score = scoresMatrix[0][j-1].score + scoreFunction.score(Optional.empty(), Optional.of(sequence2.position(j-1)));
				scoresMatrix[0][j] = new Cell(score, Collections.singleton(new Pair<>(0, j-1)));
			}

			for (int i = 1; i <= sequence1.length(); i++) {
				for (int j = 1; j <= sequence2.length(); j++) {
					
					int score1 = scoresMatrix[i - 1][j].score + scoreFunction.score(Optional.of(sequence1.position(i - 1)), Optional.empty());
					int score2 = scoresMatrix[i][j - 1].score + scoreFunction.score(Optional.empty(), Optional.of(sequence2.position(j - 1)));
					int score3 = scoresMatrix[i - 1][j - 1].score + scoreFunction.score(Optional.of(sequence1.position(i - 1)), Optional.of(sequence2.position(j - 1)));
					
					int score = max(score1, score2, score3);
					Set<Pair<Integer>> parents = new HashSet<>();
					if(score == score1) parents.add(new Pair<>(i-1, j));
					if(score == score2) parents.add(new Pair<>(i, j-1));
					if(score == score3) parents.add(new Pair<>(i-1, j-1));
					
					scoresMatrix[i][j] = new Cell(score, parents);
				}
			}
			return scoresMatrix;
		}

		
		private static int max(Integer ... values) {
			return Arrays.asList(values).stream().max(Integer::compare).get();
		}

		private AlignmentResult<T> findOneAlignment() {
			Pair<Sequence<T>> sequencePair = findOneAlignment(sequence1.length(), sequence2.length(),
					new Pair<>(new Sequence<>(Collections.emptyList()),new Sequence<>(Collections.emptyList())));
			return new AlignmentResult<>(sequencePair.first, sequencePair.second, scoresMatrix[sequence1.length()][sequence2.length()].score);
		}
		
		private Set<AlignmentResult<T>> findAllAlignments() {
			Set<Pair<Sequence<T>>> sequencePairs = findAllAlignments(sequence1.length(), sequence2.length(),
					Collections.singleton(new Pair<>(new Sequence<>(Collections.emptyList()),new Sequence<>(Collections.emptyList()))));
			
			return sequencePairs.stream().map(seqPair -> new AlignmentResult<>(seqPair.first, seqPair.second, scoresMatrix[sequence1.length()][sequence2.length()].score)).collect(Collectors.toSet());
		}

		// TODO runs very slow, need to modify the recursion using dynamic algorithm
		private Set<Pair<Sequence<T>>> findAllAlignments(int i, int j, Set<Pair<Sequence<T>>> sequencePairs) {
			if (i == 0 && j == 0) {
				return sequencePairs;
			} else {
				Set<Pair<Sequence<T>>> seqs = new HashSet<>();
				for(Pair<Integer> parent : scoresMatrix[i][j].parents){
					T newElem1 = parent.first == i-1 ? sequence1.position(i-1) : null;
					T newElem2 = parent.second == j-1 ? sequence2.position(j-1) : null;
					Set<Pair<Sequence<T>>> updatedSequencePairs = sequencePairs.stream().map(
							seqPair -> new Pair<>(seqPair.first.prepend(newElem1), seqPair.second.prepend(newElem2))).collect(Collectors.toSet());
					seqs.addAll(findAllAlignments(parent.first, parent.second, updatedSequencePairs));
				}
				return seqs;
			}
		}
		
		private Pair<Sequence<T>> findOneAlignment(int i, int j, Pair<Sequence<T>> sequencePair) {
			if (i == 0 && j == 0) {
				return sequencePair;
			} else {
				Pair<Integer> parent = scoresMatrix[i][j].parents.iterator().next();
				if(parent.equals(new Pair<>(0, 0))){
					return sequencePair;	
				} 
				if(i == sequence1.length() && j == sequence2.length() && scoresMatrix[i][j].score == scoresMatrix[parent.first][parent.second].score){
					return findOneAlignment(parent.first, parent.second, sequencePair);
				}
				T newElem1 = parent.first == i-1 ? sequence1.position(i-1) : null;
				T newElem2 = parent.second == j-1 ? sequence2.position(j-1) : null;
				return findOneAlignment(parent.first, parent.second, 
						new Pair<>(sequencePair.first.prepend(newElem1), sequencePair.second.prepend(newElem2)));
			}
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder("\t");
			for (int i = 0; i < sequence1.length(); i++) {
				sb.append(sequence1.position(i).toString().charAt(0) + "\t");
			}
			sb.append("\n\t");
			for (int j = 0; j < sequence1.length(); j++) {
				sb.append(scoresMatrix[j][0] + "\t");
			}
			sb.append("\n");

			for (int i = 0; i < sequence2.length(); i++) {
				sb.append(sequence2.position(i).toString().charAt(0) + " ");
				for (int j = 0; j <= sequence1.length(); j++) {
					sb.append(scoresMatrix[j][i + 1] + "\t");
				}
				sb.append("\n");
			}

			return sb.toString();
		}
	}

	public static class AlignmentResult<T> {
		public final Sequence<T> sequence1;
		public final Sequence<T> sequence2;
		public final int score;

		public AlignmentResult(Sequence<T> sequence1, Sequence<T> sequence2, int score) {
			this.sequence1 = sequence1;
			this.sequence2 = sequence2;
			this.score = score;
		}
		
		public String toString(){
			return sequence1 + "\n" + sequence2 + "\nScore: " + score;
		}
	}

}
