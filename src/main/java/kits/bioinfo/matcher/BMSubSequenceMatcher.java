package kits.bioinfo.matcher;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import kits.bioinfo.core.DnaBase;
import kits.bioinfo.core.DnaSequence;

/**
 * Boyer-Moore algorithm
 */
public class BMSubSequenceMatcher implements Matcher {

    protected final DnaSequence pattern;

    private final BadCharacterRuleTable badCharacterRuleTable;
    private final GoodSuffixRuleTable goodSuffixRuleTable;

    public BMSubSequenceMatcher(DnaSequence pattern) {
        this.pattern = pattern;
        badCharacterRuleTable = new BadCharacterRuleTable(pattern);
        goodSuffixRuleTable = new GoodSuffixRuleTable(pattern);
    }

    public BMSubSequenceMatcher(String patternString) {
        this(new DnaSequence(patternString));
    }

    @Override
    public boolean matches(DnaSequence sequence) {
        outer: for (int index = 0; index < sequence.length() - pattern.length() + 1; index++) {
            for (int j = 0; j < pattern.length(); j++) {
                if (sequence.position(index + j) != pattern.position(j)) {
                    index += badCharacterRuleTable.skipLength(sequence.position(index + j), j);
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
            for (int j = pattern.length() - 1; j >= 0; j--) {
                comparisons++;
                if (sequence.position(index + j) != pattern.position(j)) {
                    int goodSufSkip = goodSuffixRuleTable.skipLength(j);
                    int badCharSkip = badCharacterRuleTable.skipLength(sequence.position(index + j), j);
                    index += Math.max(goodSufSkip, badCharSkip);
                    continue outer;
                }
            }
            matchStartIndexes.add(index);
            index += goodSuffixRuleTable.skipAfterMatch;
        }
        System.out.println("Alignments: " + alignments);
        System.out.println("Comparisons: " + comparisons);
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

class BadCharacterRuleTable {

    final int[][] table;

    BadCharacterRuleTable(DnaSequence pattern) {
        table = new int[5][pattern.length()];
        for (DnaBase base : DnaBase.values()) {
            for (int index = 0; index < pattern.length(); index++) {
                table[base.ordinal()][index] = calculateSkipLength(pattern, base, index);
            }
        }
    }

    private int calculateSkipLength(DnaSequence pattern, DnaBase base, int position) {
        int counter = 0;
        for (int i = position - 1; i >= 0; i--) {
            if (pattern.position(i) == base) {
                break;
            }
            counter++;
        }
        return counter;
    }

    int skipLength(DnaBase base, int position) {
        return table[base.ordinal()][position];
    }
}

class GoodSuffixRuleTable {

    final int[] table;

    final int skipAfterMatch;

    GoodSuffixRuleTable(DnaSequence pattern) {
        if (pattern.length() == 0)
            throw new IllegalArgumentException("Empty pattern");
        table = new int[pattern.length()];
        for (int index = 0; index < pattern.length(); index++) {
            table[index] = calculateSkipLength(pattern, index);
        }
        skipAfterMatch = calculateSkipAfterMatch(pattern);
    }

    private int calculateSkipAfterMatch(DnaSequence pattern) {
        for (int k = pattern.length() - 1; k >= 1; k--) {
            DnaSequence suffix = pattern.subSequence(pattern.length() - k, k);
            DnaSequence postfix = pattern.subSequence(0, k);
            if (suffix.equals(postfix)) {
                return pattern.length() - k - 1;
            }
        }
        return pattern.length() - 1;
    }

    private int calculateSkipLength(DnaSequence pattern, int position) {
        int counter = 0;
        int length = pattern.length() - position - 1;
        DnaSequence suffix = pattern.subSequence(position + 1, length);
        for (int i = position; i >= 0; i--) {
            if (suffix.equals(pattern.subSequence(i, length))) {
                break;
            }
            counter++;
        }
        return counter;
    }

    int skipLength(int position) {
        return table[position];
    }

}