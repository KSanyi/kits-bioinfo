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

import kits.bioinfo.core.DnaBase;
import kits.bioinfo.core.DnaSequence;

import static java.util.stream.Collectors.*;

public class ProfileMatrix {

	private static MathContext MC = new MathContext(6, RoundingMode.HALF_UP);

	private final int k;

	public static ProfileMatrix buildWithPseudoCounts(Collection<DnaSequence> kmers) {
		return build(kmers, true);
	}

	public static ProfileMatrix build(Collection<DnaSequence> kmers) {
		return build(kmers, false);
	}

	private static ProfileMatrix build(Collection<DnaSequence> kmers, Boolean usingPseudoCounts) {
		if (kmers.size() == 0) {
			throw new IllegalArgumentException("Can not build profile matrix without any kmer");
		}

		int k = kmers.iterator().next().length();

		if (!kmers.stream().allMatch(kmer -> kmer.length() == k)) {
			throw new IllegalArgumentException("All kmers must have the same length");
		}

		List<Map<DnaBase, BigDecimal>> values = new ArrayList<>();

		for (int index = 0; index < k; index++) {
			Map<DnaBase, BigDecimal> distribution = new HashMap<>();
			for (DnaBase base : DnaBase.values()) {
				distribution.put(base, usingPseudoCounts ? BigDecimal.ONE : BigDecimal.ZERO);
			}
			for (DnaSequence kmer : kmers) {
				DnaBase base = kmer.position(index);
				distribution.put(base, distribution.get(base).add(BigDecimal.ONE));
			}
			for (DnaBase base : DnaBase.values()) {
				distribution.put(base, distribution.get(base).divide(BigDecimal.valueOf(kmers.size()), MC));
			}
			values.add(distribution);
		}

		return new ProfileMatrix(values);
	}

	private final List<Map<DnaBase, BigDecimal>> values;

	private ProfileMatrix(List<Map<DnaBase, BigDecimal>> values) {
		this.values = values;
		k = values.size();
	}

	public BigDecimal calculateProbability(DnaSequence sequence) {
		BigDecimal product = BigDecimal.ONE;
		for (int index = 0; index < sequence.length(); index++) {
			product = product.multiply(values.get(index).get(sequence.position(index)));
		}
		return product;
	}

	public DnaSequence findMostProbableKmer(DnaSequence sequence) {
		if (sequence.length() < k) {
			throw new IllegalArgumentException("Sequence is shorter than k");
		}
		DnaSequence mostProbable = sequence.subSequence(0, k);
		BigDecimal probability = calculateProbability(mostProbable);
		for (int index = 1; index < sequence.length() - k + 1; index++) {
			DnaSequence candidate = sequence.subSequence(index, k);
			BigDecimal candidateProbability = calculateProbability(candidate);
			if (candidateProbability.compareTo(probability) > 0) {
				probability = candidateProbability;
				mostProbable = candidate;
			}
		}
		return mostProbable;
	}

	@Override
	public String toString() {
		return Arrays.asList(DnaBase.values()).stream().map(base -> values.stream().map(dist -> dist.get(base).toString()).collect(joining("\t")))
				.collect(joining("\n"));
	}

}
