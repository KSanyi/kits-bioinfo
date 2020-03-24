package kits.bioinfo.core;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DnaSequence extends Sequence<DnaBase> {

    public DnaSequence(List<DnaBase> text) {
        super(text);
    }

    public DnaSequence(String text) {
        this(text.chars().mapToObj(c -> DnaBase.of((char) c)).collect(toList()));
    }

    public DnaSequence(Sequence<DnaBase> sequence) {
        super(sequence.text);
    }

    public DnaSequence subSequence(int start, int k) {
        return new DnaSequence(super.subSequence(start, k));
    }

    public DnaSequence prefix(int k) {
        return new DnaSequence(super.prefix(k));
    }

    public DnaSequence prefix() {
        return new DnaSequence(super.prefix());
    }

    public DnaSequence suffix(int k) {
        return new DnaSequence(super.suffix(k));
    }

    public DnaSequence suffix() {
        return new DnaSequence(super.suffix());
    }

    public List<DnaSequence> allSubSequences(int k) {
        List<DnaSequence> subSequences = new ArrayList<>();
        for (int i = 0; i < length() - k + 1; i++) {
            subSequences.add(subSequence(i, k));
        }
        return subSequences;
    }

    public DnaSequence append(DnaBase nucleotid) {
        return new DnaSequence(super.append(nucleotid));
    }

    public DnaSequence append(DnaSequence other) {
        return new DnaSequence(super.append(other));
    }

    public DnaSequence prepend(DnaBase nucleotid) {
        return new DnaSequence(super.prepend(nucleotid));
    }

    public DnaSequence prepend(DnaSequence other) {
        return new DnaSequence(super.prepend(other));
    }

    public DnaSequence reverseComplement() {
        List<DnaBase> complementText = text.stream().map(DnaBase::complement).collect(toList());
        Collections.reverse(complementText);
        return new DnaSequence(complementText);
    }

    private Set<DnaSequence> neighbours() {
        Set<DnaSequence> neighbours = new HashSet<>();
        neighbours.add(this);
        for (int index = 0; index < length(); index++) {
            DnaBase base = text.get(index);
            for (DnaBase other : DnaBase.others(base)) {
                List<DnaBase> newText = new ArrayList<>(text);
                newText.set(index, other);
                neighbours.add(new DnaSequence(newText));
            }
        }
        return neighbours;
    }

    public Set<DnaSequence> neighbours(int k) {
        return neighboursOf(Collections.singleton(this), k);
    }

    private Set<DnaSequence> neighboursOf(Set<DnaSequence> sequences, int k) {
        if (k == 0) {
            return sequences;
        } else {
            Set<DnaSequence> neighbours = new HashSet<>();
            for (DnaSequence sequence : sequences) {
                neighbours.addAll(sequence.neighbours());
            }
            return neighboursOf(neighbours, k - 1);
        }
    }

    @Override
    public boolean equals(Object other) {
        return text.equals(((DnaSequence) other).text);
    }

    @Override
    public String toString() {
        return text.stream().map(n -> n.toString()).collect(joining());
    }

}
