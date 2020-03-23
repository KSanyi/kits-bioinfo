package kits.bioinfo.matcher;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import kits.bioinfo.core.DnaSequence;

public class SubSequenceMatcher implements Matcher {

    protected final DnaSequence pattern;

    public SubSequenceMatcher(DnaSequence pattern) {
        this.pattern = pattern;
    }

    public SubSequenceMatcher(String patternString) {
        this.pattern = new DnaSequence(patternString);
    }

    @Override
    public boolean matches(DnaSequence sequence) {
        for (int index = 0; index < sequence.length() - pattern.length() + 1; index++) {
            if (matchesSubSequence(sequence.subSequence(index, pattern.length())))
                return true;
        }
        return false;
    }

    @Override
    public List<Integer> matchStartIndexes(DnaSequence sequence) {
        List<Integer> matchStartIndexes = new LinkedList<>();
        for (int index = 0; index < sequence.length() - pattern.length() + 1; index++) {
            if (matchesSubSequence(sequence.subSequence(index, pattern.length())))
                matchStartIndexes.add(index);
        }
        return Collections.unmodifiableList(matchStartIndexes);
    }

    protected boolean matchesSubSequence(DnaSequence subSequence) {
        return pattern.equals(subSequence);
    }

    @Override
    public int matchCount(DnaSequence sequence) {
        return matchStartIndexes(sequence).size();
    }

}
