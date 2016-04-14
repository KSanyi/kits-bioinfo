package kits.bioinfo.motif;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class ProbabilityDistribution {

	private final Random random = new Random();
	
	private final List<BigDecimal> cumulativeValues;
	
	public ProbabilityDistribution(List<BigDecimal> values) {
		cumulativeValues = calculateCumulativeValues(normalize(values));
	}
	
	private List<BigDecimal> normalize(List<BigDecimal> values) {
		MathContext MC = new MathContext(6, RoundingMode.HALF_UP);
		BigDecimal sum = values.stream().reduce(BigDecimal.ZERO, (a, b) -> a.add(b));
		return values.stream().map(p -> p.divide(sum, MC)).collect(Collectors.toList());
	}
	
	private List<BigDecimal> calculateCumulativeValues(List<BigDecimal> values) {
		List<BigDecimal> cumulative = new LinkedList<>();
		BigDecimal sum = BigDecimal.ZERO;
		for(BigDecimal value : values) {
			sum = sum.add(value);
			cumulative.add(sum);
		}
		return Collections.unmodifiableList(cumulative);
	}
	
	public int randomInt() {
		double r = random.nextDouble();
		for(int i=0;i<cumulativeValues.size();i++) {
			if(r < cumulativeValues.get(i).doubleValue()){
				return i;
			}	
		}
		throw new RuntimeException("Error in drawing random int");
	}
	
}
