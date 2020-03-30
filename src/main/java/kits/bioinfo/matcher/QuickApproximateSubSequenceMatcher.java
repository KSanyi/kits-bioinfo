package kits.bioinfo.matcher;

import java.util.LinkedList;
import java.util.List;

import kits.bioinfo.core.DnaSequence;

public class QuickApproximateSubSequenceMatcher implements Matcher {

    private final int distance;
    private final DnaSequence pattern;

    public QuickApproximateSubSequenceMatcher(DnaSequence pattern, int distance) {
        this.pattern = pattern;
        this.distance = distance;
    }

    public QuickApproximateSubSequenceMatcher(String patternString, int distance) {
        this(new DnaSequence(patternString), distance);
    }


    @Override
    public boolean matches(DnaSequence sequence) {
        outer: for (int index = 0; index < sequence.length() - pattern.length() + 1; index++) {
            int mismatches = 0;
            for (int j = 0; j < pattern.length(); j++) {
                if (sequence.position(index + j) != pattern.position(j)) {
                    mismatches++;
                    if(mismatches > distance)
                        continue outer;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public List<Integer> matchStartIndexes(DnaSequence sequence) {
        List<Integer> matchStartIndexes = new LinkedList<>();
        outer: for (int index = 0; index < sequence.length() - pattern.length() + 1; index++) {
            int mismatches = 0;
            for (int j = 0; j < pattern.length(); j++) {
                if (sequence.position(index + j) != pattern.position(j)) {
                    mismatches++;
                    if(mismatches > distance)
                        continue outer;
                }
            }
            matchStartIndexes.add(index);
        }
        return List.copyOf(matchStartIndexes);
    }

    @Override
    public int matchCount(DnaSequence sequence) {
        return matchStartIndexes(sequence).size();
    }

}
