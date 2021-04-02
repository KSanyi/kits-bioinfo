package kits.bioinfo.ucsandiego.course3.part2.challenge;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import kits.bioinfo.genomerearrangement.GreedySorter;
import kits.bioinfo.genomerearrangement.Permutation;

/*
 * Code Challenge: Implement GreedySorting.
 *    Input: A permutation P.
 *    Output: The sequence of permutations corresponding to applying GreedySorting to P,
 *            ending with the identity permutation.
 */
public class Challenge01 {

    @Test
    public void test() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("input/dataset_286_3.txt"));
        
        Permutation permutation = Permutation.parse(lines.get(0));
        List<Permutation> permutations = GreedySorter.sort(permutation);
        
        //Files.write(Paths.get("output/output_286_3.txt"), permutations.stream().map(p -> p.toString()).collect(Collectors.toList()));
        
        List<Permutation> expectedPermutations = Files.readAllLines(Paths.get("output/output_286_3.txt")).stream()
                .map(permutationString -> Permutation.parse(permutationString)).collect(Collectors.toList());
        
        assertEquals(expectedPermutations, permutations);
    }

}

