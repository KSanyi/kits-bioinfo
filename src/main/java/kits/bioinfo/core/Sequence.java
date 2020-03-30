package kits.bioinfo.core;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Sequence<T> implements Comparable<Sequence<T>>, Iterable<T> {

    public static Sequence<Character> of(String text) {
        return new Sequence<Character>(text.chars().mapToObj(c -> Character.valueOf((char) c)).collect(toList()));
    }

    protected final List<T> text;

    public Sequence() {
        this(Collections.emptyList());
    }

    public Sequence(List<T> text) {
        this.text = Collections.unmodifiableList(text);
    }

    public T position(int index) {
        return text.get(index);
    }

    public int length() {
        return text.size();
    }

    public Sequence<T> subSequence(int start, int k) {
        if (start < 0 || start + k > text.size())
            throw new IllegalArgumentException("Wrong sub sequence index: " + start + " : " + (start + k));
        return new Sequence<>(text.subList(start, start + k));
    }

    public Sequence<T> prefix(int k) {
        if (length() < k)
            throw new IllegalArgumentException("Can not form suffix with length " + k + " from sequence with length " + length());
        return subSequence(0, k);
    }

    public Sequence<T> prefix() {
        if (isEmpty())
            throw new IllegalStateException("Can not call prefix on empty sequence");
        return prefix(length() - 1);
    }

    public Sequence<T> suffix(int k) {
        if (length() < k)
            throw new IllegalArgumentException("Can not form suffix with length " + k + " from sequence with length " + length());
        return subSequence(length() - k, k);
    }

    public Sequence<T> suffix() {
        if (isEmpty())
            throw new IllegalStateException("Can not call suffix on empty sequence");
        return suffix(length() - 1);
    }

    public boolean isEmpty() {
        return length() == 0;
    }

    public Sequence<T> append(T elem) {
        List<T> newText = new ArrayList<>(text);
        newText.add(elem);
        return new Sequence<>(newText);
    }

    public Sequence<T> append(Sequence<T> other) {
        List<T> newText = new ArrayList<>(text);
        newText.addAll(other.text);
        return new Sequence<>(newText);
    }

    public Sequence<T> prepend(T elem) {
        List<T> newText = new ArrayList<>();
        newText.add(elem);
        newText.addAll(text);
        return new Sequence<>(newText);
    }

    public Sequence<T> prepend(Sequence<T> other) {
        List<T> newText = new ArrayList<>(other.text);
        newText.addAll(text);
        return new Sequence<>(newText);
    }

    public int hammingDistance(Sequence<T> other) {
        if (other.length() != length())
            throw new IllegalArgumentException("Different length sequences");
        int sum = 0;
        for (int i = 0; i < length(); i++) {
            sum += other.position(i) == position(i) ? 0 : 1;
        }
        return sum;
    }

    @Override
    public Iterator<T> iterator() {
        return text.iterator();
    }
    
    public Stream<T> stream() {
        return text.stream();
    }
    
    public Sequence<T> filter(Predicate<? super T> predicate) {
        return new Sequence<>(text.stream().filter(predicate).collect(toList()));
    }
    
    public Sequence<T> toSequence() {
        return new Sequence<>(text);
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof Sequence<?> ? text.equals(((Sequence<?>) other).text) : false;
    }

    @Override
    public int hashCode() {
        return text.hashCode();
    }

    @Override
    public String toString() {
        return text.stream().map(n -> n != null ? n.toString() : "-").collect(joining());
    }

    @Override
    public int compareTo(Sequence<T> other) {
        return this.toString().compareTo(other.toString());
    }

}
