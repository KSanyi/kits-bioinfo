package kits.bioinfo.skew;

import java.util.LinkedList;
import java.util.List;

import kits.bioinfo.core.DnaBase;
import kits.bioinfo.core.DnaSequence;

public class SkewFinder {

	public List<Integer> calculateSkew(DnaSequence sequence) {
		int sumGCDiff = 0;
		List<Integer> skew = new LinkedList<>();
		for (int index = 0; index < sequence.length(); index++) {
			skew.add(sumGCDiff);
			DnaBase base = sequence.position(index);
			switch (base) {
			case G:
				sumGCDiff += 1;
				break;
			case C:
				sumGCDiff -= 1;
				break;
			default:
			}
		}
		skew.add(sumGCDiff);
		return skew;
	}

	public List<Integer> calculateSkewMin(DnaSequence sequence) {

		List<Integer> minIndexes = new LinkedList<>();
		List<Integer> skew = calculateSkew(sequence);

		int min = Integer.MAX_VALUE;
		for (int index = 0; index < skew.size(); index++) {
			int skewValue = skew.get(index);
			if (skewValue < min) {
				minIndexes.clear();
				minIndexes.add(index);
				min = skewValue;
			} else if (skewValue == min) {
				minIndexes.add(index);
			}
		}
		return minIndexes;
	}

}
