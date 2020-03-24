package kits.bioinfo.util;

public record IntPair(int first, int second) {

    @Override
    public String toString() {
        return "<" + first + ", " + second + ">";
    }

}