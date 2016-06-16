package kits.bioinfo.peptidsequencing;

import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import kits.bioinfo.core.AminoAcid;
import kits.bioinfo.core.Peptid;

public class MassSpectrometer {

	public List<Integer> generateMassSpectrumForPeptid(Peptid peptid){
		return generateMassSpectrum(peptid, false);
	}
	
	public List<Integer> generateMassSpectrumForCyclidPeptid(Peptid cyclidPeptid){
		return generateMassSpectrum(cyclidPeptid, true);
	}
	
	private List<Integer> generateMassSpectrum(Peptid peptid, boolean cylic){
		
		List<Peptid> allSubPeptids = new ArrayList<>();
		for(int k=1;k<peptid.length();k++){
			allSubPeptids.addAll(cylic ? generateCyclicCompositions(peptid, k) : generateCompositions(peptid, k));
		}
		
		List<Integer> masses = allSubPeptids.stream().map(p -> p.mass()).collect(Collectors.toList());
		masses.add(0);
		masses.add(peptid.mass());
		
		Collections.sort(masses);
		return Collections.unmodifiableList(masses);
	}
	
	private List<Peptid> generateCompositions(Peptid peptid, int k) {
		if(peptid.length() < k) throw new IllegalArgumentException("Sequence length must be >= k");
		
		return range(0, peptid.length()-k+1).mapToObj(i -> peptid.subPeptid(i, k)).collect(toList());
	}
	
	private List<Peptid> generateCyclicCompositions(Peptid cyclidPeptid, int k) {
		if(cyclidPeptid.length() < k) throw new IllegalArgumentException("Sequence length must be >= k");
		
		List<AminoAcid> linearizedCycle = new ArrayList<>(cyclidPeptid.aminoAcids);
		linearizedCycle.addAll(cyclidPeptid.aminoAcids.subList(0, cyclidPeptid.aminoAcids.size()-1));
		Peptid linearizedPeptid = new Peptid(linearizedCycle);
		
		return range(0, cyclidPeptid.length()).mapToObj(i -> linearizedPeptid.subPeptid(i, k)).collect(toList());
	}

}
