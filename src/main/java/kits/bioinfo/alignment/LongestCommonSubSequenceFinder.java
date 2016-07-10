package kits.bioinfo.alignment;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import kits.bioinfo.core.DnaSequence;

// TODO remove this class and use SequenceAligner
public class LongestCommonSubSequenceFinder {

	public static DnaSequence findOneSequence(DnaSequence sequence1, DnaSequence sequence2) {
		return findAllSequences(sequence1, sequence2).iterator().next();
	}

	public static Set<DnaSequence> findAllSequences(DnaSequence sequence1, DnaSequence sequence2) {
		Table table = new Table(sequence1, sequence2);
		//System.out.println(table);
		return table.findSequences();
	}
	
	private static class Table {
		
		private final int[][] scores;
		
		private final DnaSequence sequence1;
		private final DnaSequence sequence2;
		
		Table(DnaSequence sequence1, DnaSequence sequence2){
			this.sequence1 = sequence1;
			this.sequence2 = sequence2;
			scores = fillScoresMatrix();
		}
		
		private int[][] fillScoresMatrix(){
			int[][] scoresMatrix = new int[sequence1.length() + 1][sequence2.length() + 1];
			for (int i = 0; i <= sequence1.length(); i++) {
				scoresMatrix[i][0] = 0;
			}
			for (int j = 0; j <= sequence2.length(); j++) {
				scoresMatrix[0][j] = 0;
			}

			for (int i = 1; i <= sequence1.length(); i++) {
				for (int j = 1; j <= sequence2.length(); j++) {
					scoresMatrix[i][j] = max(
							scoresMatrix[i - 1][j],
							scoresMatrix[i][j - 1],
							scoresMatrix[i - 1][j - 1] + (sequence1.position(i - 1).equals(sequence2.position(j - 1)) ? 1 : 0));
				}
			}
			return scoresMatrix;
		}
		
		private static int max(int a, int b, int c) {
			return Math.max(a, Math.max(b, c));
		}
		
		private Set<DnaSequence> findSequences() {
			return findSequences(sequence1.length(), sequence2.length(), Collections.singleton(new DnaSequence("")));
		}

		private Set<DnaSequence> findSequences(int i, int j, Set<DnaSequence> sequences) {
			if (i == 0 || j == 0) {
				return sequences;
			}
			if (scores[i][j] > scores[i - 1][j - 1] && sequence1.position(i - 1).equals(sequence2.position(j - 1))) {
				Set<DnaSequence> updatedSequences = sequences.stream().map(seq -> seq.prepend(sequence1.position(i - 1))).collect(Collectors.toSet());
				return findSequences(i - 1, j - 1, updatedSequences);
			} else if (scores[i][j - 1] > scores[i - 1][j]) {
				return findSequences(i, j - 1, sequences);
			} else if (scores[i][j - 1] < scores[i - 1][j]) {
				return findSequences(i - 1, j, sequences);
			} else {
				Set<DnaSequence> seqs = new HashSet<>();
				seqs.addAll(findSequences(i - 1, j, sequences));
				seqs.addAll(findSequences(i, j - 1, sequences));
				return seqs;
			}
		}
		
		@Override
		public String toString(){
			StringBuilder sb = new StringBuilder("\t");
			for(int i=0;i<sequence1.length();i++){
				sb.append(sequence1.position(i) + "\t");
			}
			sb.append("\n\t");
			for(int j=0;j<sequence1.length();j++){
				sb.append(scores[j][0] + "\t");
			}
			sb.append("\n");
			
			for(int i=0;i<sequence2.length();i++){
				sb.append(sequence2.position(i) + " ");
				for(int j=0;j<=sequence1.length();j++){
					sb.append(scores[j][i+1] + "\t");
				}
				sb.append("\n");
			}
			
			return sb.toString();
		}
	}

}
