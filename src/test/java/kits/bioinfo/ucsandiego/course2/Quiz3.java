package kits.bioinfo.ucsandiego.course2;

import kits.bioinfo.core.AminoAcid;
import kits.bioinfo.core.Codon;
import kits.bioinfo.core.Peptid;
import kits.bioinfo.core.Ribosome;
import kits.bioinfo.core.RnaSequence;
import kits.bioinfo.peptidsequencing.MassSpectrometer;

public class Quiz3 {

	public static void main(String[] args) {
		question2();
		question3();
		question5();
		question6();
	}
	
	private static void question2() {
		System.out.println("\n" + Thread.currentThread().getStackTrace()[1].getMethodName() + ": ");
		
		Ribosome ribosome = new Ribosome();
		System.out.println(ribosome.translateToAminoAcidSequence(new RnaSequence("CCCAGUACCGAAAUUAAC")));
		System.out.println(ribosome.translateToAminoAcidSequence(new RnaSequence("CCCCGUACGGAGAUGAAA")));
		System.out.println(ribosome.translateToAminoAcidSequence(new RnaSequence("CCGAGGACCGAAAUCAAC")));
		System.out.println(ribosome.translateToAminoAcidSequence(new RnaSequence("CCCAGGACUGAGAUCAAU")));
	}
	
	private static void question3() {
		System.out.println("\n" + Thread.currentThread().getStackTrace()[1].getMethodName() + ": ");
		
		Peptid peptid = new Peptid("MASS");
		
		int res=1;
		for(AminoAcid aminoAcid : peptid){
			res *= Codon.codonTable.values().stream().filter(codedAminoAcid -> codedAminoAcid.equals(aminoAcid)).count();
		}
		System.out.println(res);
	}
	
	private static void question5() {
		System.out.println("\n" + Thread.currentThread().getStackTrace()[1].getMethodName() + ": ");
		
		MassSpectrometer massSpectrometer = new MassSpectrometer();
		System.out.println(massSpectrometer.generateMassSpectrumForCyclidPeptid(new Peptid("MTAI")));
		System.out.println(massSpectrometer.generateMassSpectrumForCyclidPeptid(new Peptid("TMLA")));
		System.out.println(massSpectrometer.generateMassSpectrumForCyclidPeptid(new Peptid("IAMT")));
		System.out.println(massSpectrometer.generateMassSpectrumForCyclidPeptid(new Peptid("TAIM")));
		System.out.println(massSpectrometer.generateMassSpectrumForCyclidPeptid(new Peptid("MIAT")));
		System.out.println(massSpectrometer.generateMassSpectrumForCyclidPeptid(new Peptid("ALTM")));
	}
	
	private static void question6() {
		System.out.println("\n" + Thread.currentThread().getStackTrace()[1].getMethodName() + ": ");
		
		MassSpectrometer massSpectrometer = new MassSpectrometer();
		System.out.println(massSpectrometer.generateMassSpectrumForPeptid(new Peptid("CET")));
		System.out.println(massSpectrometer.generateMassSpectrumForPeptid(new Peptid("AQV")));
		System.out.println(massSpectrometer.generateMassSpectrumForPeptid(new Peptid("ETC")));
		System.out.println(massSpectrometer.generateMassSpectrumForPeptid(new Peptid("VAQ")));
		System.out.println(massSpectrometer.generateMassSpectrumForPeptid(new Peptid("TCE")));
		System.out.println(massSpectrometer.generateMassSpectrumForPeptid(new Peptid("CTV")));
	}

}
