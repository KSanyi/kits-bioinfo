package kits.bioinfo.util;

public record Pair<T>(T first, T second) {

    @Override
    public String toString() {
        return "<" + first + ", " + second + ">";
    }

}
