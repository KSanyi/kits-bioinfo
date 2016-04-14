package kits.bioinfo.motif;

import java.util.Collection;

import kits.bioinfo.base.Nucleotid;
import kits.bioinfo.base.Sequence;
import kits.bioinfo.util.FrequencyMap;

public class Motifs {

	public static Sequence consensusString(Collection<Sequence> motifs) {
		if (motifs.size() == 0)
			throw new IllegalArgumentException("Can not build profile matrix without any kmer");

		int k = motifs.iterator().next().length();
		if (!motifs.stream().allMatch(kmer -> kmer.length() == k))
			throw new IllegalArgumentException("All kmers must have the same length");

		Sequence consensus = new Sequence("");

		for (int index = 0; index < k; index++) {
			FrequencyMap<Nucleotid> frequencyMap = new FrequencyMap<>();
			for (Sequence sequence : motifs) {
				Nucleotid base = sequence.position(index);
				frequencyMap.put(base);
			}
			consensus = consensus.append(frequencyMap.getMostFrequent());
		}

		return consensus;
	}
	
	public static int score(Collection<Sequence> motifs) {
		return distance(motifs, consensusString(motifs));
	}
	
	public static int distance(Collection<Sequence> sequences, Sequence motif) {
		return sequences.stream().map(sequence -> distance(sequence, motif)).mapToInt(Integer::intValue).sum();
	}
	
	public static int distance(Sequence sequence, Sequence kmer) {
		int k = kmer.length();
		if(sequence.length() < k) {
			throw new IllegalArgumentException("Sequence can not be shorter than the kmer");
		}
		
		int distance = kmer.length();
		for(int i=0;i<sequence.length()-k+1;i++) {
			int d = sequence.subSequence(i, k).distance(kmer);
			if(d < distance){
				distance = d;
			}
		}
		return distance;
	}

}
