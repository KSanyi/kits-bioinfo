package kits.bioinfo.core;

public class PeptidCreator {

	private RnaPolymerase rnaPolymerase = new RnaPolymerase();
	private Ribosome ribosome = new Ribosome();

	public Peptid translateAndTranscribe(DnaSequence dnaSequence) {
		RnaSequence rnaSequence = rnaPolymerase.transcribeDnaSequence(dnaSequence);
		return ribosome.translateToAminoAcidSequence(rnaSequence);
	}

}
