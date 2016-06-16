package kits.bioinfo.peptidsequencing;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import kits.bioinfo.core.AminoAcid;
import kits.bioinfo.core.Peptid;

public class CycloPeptidSequencer {

	public static List<Peptid> sequencePeptids(List<Integer> spectrum){
		List<Peptid> result = new LinkedList<>();
		List<Peptid> peptids = new LinkedList<>();
		peptids.add(new Peptid(new LinkedList<>()));
		
		while(!peptids.isEmpty()) {
			peptids = extendedPeptids(peptids);
			peptids = peptids.stream().filter(peptid -> consistentWithSpectrum(peptid, spectrum)).collect(Collectors.toList());
			
			List<Peptid> matchingPeptids = peptids.stream().filter(peptid -> matchesWithSpectrum(peptid, spectrum)).collect(Collectors.toList());
			result.addAll(matchingPeptids);
			peptids.removeAll(matchingPeptids);
		}
		
		return result;
	}
	
	private static List<Peptid> extendedPeptids(List<Peptid> peptids){
		List<Peptid> extendedPeptids = new LinkedList<>();
		for(Peptid peptid : peptids){
			for(AminoAcid aminoAcid : AminoAcid.values()){
				extendedPeptids.add(peptid.extendedPeptid(aminoAcid));
			}
		}
		return extendedPeptids;
	}
	
	private static boolean consistentWithSpectrum(Peptid peptid, List<Integer> spectrum){
		List<Integer> theoreticalSpectrum = new MassSpectrometer().generateMassSpectrumForPeptid(peptid);
		return spectrum.containsAll(theoreticalSpectrum);
	}
	
	private static boolean matchesWithSpectrum(Peptid peptid, List<Integer> spectrum){
		List<Integer> theoreticalSpectrum = new MassSpectrometer().generateMassSpectrumForCyclidPeptid(peptid);
		return spectrum.equals(theoreticalSpectrum);
	}
	
}
