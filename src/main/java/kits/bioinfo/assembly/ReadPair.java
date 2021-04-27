package kits.bioinfo.assembly;

import kits.bioinfo.core.DnaSequence;

public record ReadPair(DnaSequence read1, DnaSequence read2, int distance) {

    public ReadPair prefix() {
        return new ReadPair(read1.prefix(), read2.prefix(), distance);
    }

    public ReadPair suffix() {
        return new ReadPair(read1.suffix(), read2.suffix(), distance);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(read1.toString());
        for (int i = 0; i < distance; i++) {
            sb.append('.');
        }
        sb.append(read2.toString());
        return sb.toString();
    }

}
