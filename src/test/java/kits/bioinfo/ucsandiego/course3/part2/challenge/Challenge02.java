package kits.bioinfo.ucsandiego.course3.part2.challenge;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.junit.jupiter.api.Test;

import kits.bioinfo.genomerearrangement.Permutation;

/*
 * Number of Breakpoints Problem: Find the number of breakpoints in a permutation.
 *    Input: A permutation.
 *    Output: The number of breakpoints in this permutation.
 */
public class Challenge02 {

    @Test
    public void test() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("input/dataset_287_6.txt"));
        
        Permutation permutation = Permutation.parse(lines.get(0));
        
        assertEquals(180, permutation.numberOfBreakPoints());
    }

}

