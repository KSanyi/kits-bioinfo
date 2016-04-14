package kits.bioinfo.motif;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;
import kits.bioinfo.base.Nucleotid;
import kits.bioinfo.base.Sequence;

public class ProfileMatrix {

	private static MathContext MC = new MathContext(6, RoundingMode.HALF_UP);
	
	private final int k;
	
	public static ProfileMatrix buildWithPseudoCounts(Collection<Sequence> kmers) {
		return build(kmers, true);
	}
	
	public static ProfileMatrix build(Collection<Sequence> kmers) {
		return build(kmers, false);
	}
	
	private static ProfileMatrix build(Collection<Sequence> kmers, Boolean usingPseudoCounts) {
		if(kmers.size() == 0) {
			throw new IllegalArgumentException("Can not build profile matrix without any kmer");
		}
		
		int k = kmers.iterator().next().length();
		
		if(!kmers.stream().allMatch(kmer -> kmer.length() == k)) {
			throw new IllegalArgumentException("All kmers must have the same length");
		}
		
		List<Map<Nucleotid, BigDecimal>> values = new ArrayList<>();
		
		for(int index=0;index<k;index++) {
			Map<Nucleotid, BigDecimal> distribution = new HashMap<>();
			for(Nucleotid base : Nucleotid.values()){
				distribution.put(base, usingPseudoCounts ? BigDecimal.ONE : BigDecimal.ZERO);
			}
			for(Sequence kmer : kmers) {
				Nucleotid base = kmer.position(index);
				distribution.put(base, distribution.get(base).add(BigDecimal.ONE));
			}
			for(Nucleotid base : Nucleotid.values()){
				distribution.put(base, distribution.get(base).divide(BigDecimal.valueOf(kmers.size()), MC));
			}
			values.add(distribution);
		}
		
		return new ProfileMatrix(values);
	}
	
	
	private final List<Map<Nucleotid, BigDecimal>> values;
	
	private ProfileMatrix(List<Map<Nucleotid, BigDecimal>> values) {
		this.values = values;
		k = values.size();
	}
	
	public BigDecimal calculateProbability(Sequence sequence) {
		BigDecimal product = BigDecimal.ONE;
		for(int index=0;index<sequence.length();index++) {
			product = product.multiply(values.get(index).get(sequence.position(index)));
		}
		return product;
	}
	
	public Sequence findMostProbableKmer(Sequence sequence) {
		if(sequence.length() < k) {
			throw new IllegalArgumentException("Sequence is shorter than k");
		}
		Sequence mostProbable = sequence.subSequence(0, k);
		BigDecimal probability = calculateProbability(mostProbable);
		for(int index=1;index<sequence.length()-k+1;index++) {
			Sequence candidate = sequence.subSequence(index, k);
			BigDecimal candidateProbability = calculateProbability(candidate);
			if(candidateProbability.compareTo(probability) > 0) {
				probability = candidateProbability;
				mostProbable = candidate;
			}
		}
		return mostProbable;
	}
	
	@Override
	public String toString() {
		return Arrays.asList(Nucleotid.values()).stream().map(base -> values.stream().map(dist -> dist.get(base).toString()).collect(joining("\t"))).collect(joining("\n"));
	}
	
}
