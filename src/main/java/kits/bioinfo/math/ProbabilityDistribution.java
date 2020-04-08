package kits.bioinfo.math;

import static java.util.stream.Collectors.toList;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class ProbabilityDistribution {

    private final Random random = new Random();

    private final List<Double> cumulativeValues;

    public ProbabilityDistribution(List<Double> values) {
        cumulativeValues = calculateCumulativeValues(normalize(values));
    }

    private static List<Double> normalize(List<Double> values) {
        double sum = values.stream().mapToDouble(d -> d).sum();
        return values.stream().map(value -> value / sum).collect(toList());
    }

    private static List<Double> calculateCumulativeValues(List<Double> values) {
        List<Double> cumulative = new LinkedList<>();
        double sum = 0;
        for (Double value : values) {
            sum += value;
            cumulative.add(sum);
        }
        return List.copyOf(cumulative);
    }

    public int randomInt() {
        double r = random.nextDouble();
        for (int i = 0; i < cumulativeValues.size(); i++) {
            if (r < cumulativeValues.get(i).doubleValue()) {
                return i;
            }
        }
        throw new RuntimeException("Error in drawing random int");
    }

}
