package kits.bioinfo.core;

public class PeptidCreator {

    public static Peptid translateAndTranscribe(DnaSequence dnaSequence) {
        RnaSequence rnaSequence = RnaPolymerase.transcribeDnaSequence(dnaSequence);
        return Ribosome.translateToAminoAcidSequence(rnaSequence);
    }

}
