package kits.bioinfo.motif;

import java.util.Collection;

import kits.bioinfo.core.DnaBase;
import kits.bioinfo.core.DnaSequence;
import kits.bioinfo.util.FrequencyMap;

public class Motifs {

	public static DnaSequence consensusString(Collection<DnaSequence> motifs) {
		if (motifs.size() == 0)
			throw new IllegalArgumentException("Can not build profile matrix without any kmer");

		int k = motifs.iterator().next().length();
		if (!motifs.stream().allMatch(kmer -> kmer.length() == k))
			throw new IllegalArgumentException("All kmers must have the same length");

		DnaSequence consensus = new DnaSequence("");

		for (int index = 0; index < k; index++) {
			FrequencyMap<DnaBase> frequencyMap = new FrequencyMap<>();
			for (DnaSequence sequence : motifs) {
				DnaBase base = sequence.position(index);
				frequencyMap.put(base);
			}
			consensus = consensus.append(frequencyMap.getMostFrequent());
		}

		return consensus;
	}
	
	public static int score(Collection<DnaSequence> motifs) {
		return distance(motifs, consensusString(motifs));
	}
	
	public static int distance(Collection<DnaSequence> sequences, DnaSequence motif) {
		return sequences.stream().map(sequence -> distance(sequence, motif)).mapToInt(Integer::intValue).sum();
	}
	
	public static int distance(DnaSequence sequence, DnaSequence kmer) {
		int k = kmer.length();
		if(sequence.length() < k) {
			throw new IllegalArgumentException("Sequence can not be shorter than the kmer");
		}
		
		int distance = kmer.length();
		for(int i=0;i<sequence.length()-k+1;i++) {
			int d = sequence.subSequence(i, k).hammingDistance(kmer);
			if(d < distance){
				distance = d;
			}
		}
		return distance;
	}

}
