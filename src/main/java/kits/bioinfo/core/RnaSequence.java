package kits.bioinfo.core;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;

public class RnaSequence extends Sequence<RnaBase> {

    public RnaSequence(List<RnaBase> text) {
        super(text);
    }

    public RnaSequence(String text) {
        this(text.chars().mapToObj(c -> RnaBase.of((char) c)).collect(toList()));
    }

    public RnaSequence(Sequence<RnaBase> sequence) {
        super(sequence.text);
    }

    public RnaSequence subSequence(int start, int k) {
        return new RnaSequence(super.subSequence(start, k));
    }

    public RnaSequence prefix(int k) {
        return new RnaSequence(super.prefix(k));
    }

    public RnaSequence prefix() {
        return new RnaSequence(super.prefix());
    }

    public RnaSequence suffix(int k) {
        return new RnaSequence(super.suffix(k));
    }

    public RnaSequence suffix() {
        return new RnaSequence(super.suffix());
    }

    public List<RnaSequence> allSubSequences(int k) {
        List<RnaSequence> subSequences = new ArrayList<>();
        for (int i = 0; i < length() - k + 1; i++) {
            subSequences.add(subSequence(i, k));
        }
        return subSequences;
    }

    public RnaSequence append(RnaBase nucleotid) {
        return new RnaSequence(super.append(nucleotid));
    }

    public RnaSequence append(RnaSequence other) {
        return new RnaSequence(super.append(other));
    }

    public RnaSequence prepend(RnaBase nucleotid) {
        return new RnaSequence(super.prepend(nucleotid));
    }

    public RnaSequence prepend(RnaSequence other) {
        return new RnaSequence(super.prepend(other));
    }

    @Override
    public boolean equals(Object other) {
        return text.equals(((RnaSequence) other).text);
    }

}
