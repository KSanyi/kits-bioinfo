package kits.bioinfo.matcher;

import java.util.LinkedList;
import java.util.List;

import kits.bioinfo.core.DnaSequence;

public class QuickSubSequenceMatcher implements Matcher {

    protected final DnaSequence pattern;

    public QuickSubSequenceMatcher(DnaSequence pattern) {
        this.pattern = pattern;
    }

    public QuickSubSequenceMatcher(String patternString) {
        this(new DnaSequence(patternString));
    }

    @Override
    public boolean matches(DnaSequence sequence) {
        outer: for (int index = 0; index < sequence.length() - pattern.length() + 1; index++) {
            for (int j = 0; j < pattern.length(); j++) {
                if (sequence.position(index + j) != pattern.position(j)) {
                    continue outer;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public List<Integer> matchStartIndexes(DnaSequence sequence) {
        int alignments = 0;
        int comparisons = 0;

        List<Integer> matchStartIndexes = new LinkedList<>();
        outer: for (int index = 0; index < sequence.length() - pattern.length() + 1; index++) {
            alignments++;
            for (int j = 0; j < pattern.length(); j++) {
                comparisons++;
                if (sequence.position(index + j) != pattern.position(j)) {
                    continue outer;
                }
            }
            matchStartIndexes.add(index);
        }
        System.out.println("Alignments: " + alignments);
        System.out.println("Comparisons: " + comparisons);
        return List.copyOf(matchStartIndexes);
    }

    protected boolean matchesSubSequence(DnaSequence subSequence) {
        return pattern.equals(subSequence);
    }

    @Override
    public int matchCount(DnaSequence sequence) {
        return matchStartIndexes(sequence).size();
    }

}
