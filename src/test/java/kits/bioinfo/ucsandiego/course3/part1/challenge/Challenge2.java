package kits.bioinfo.ucsandiego.course3.part1.challenge;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import kits.bioinfo.math.grid.Grid;
import kits.bioinfo.math.grid.MaxPathFinder;

/**
 * Code Challenge: Find the length of a longest path in the Manhattan Tourist Problem.
 * Input: Integers n and m, followed by an n × (m + 1) matrix Down and an (n + 1) × m matrix Right. 
 *        The two matrices are separated by the "-" symbol.
 * Output: The length of a longest path from source (0, 0) to sink (n, m) in the rectangular grid whose edges are defined by
 *         the matrices Down and Right.
 */
public class Challenge2 {

    public static void main(String[] args) throws Exception {
        List<String> lines = Files.readAllLines(Paths.get("input/dataset_261_9.txt"));
        String[] dimensions = lines.get(0).split(" ");
        int n = Integer.parseInt(dimensions[0]);
        int m = Integer.parseInt(dimensions[1]);

        String downsMatrixString = String.join("\n", lines.subList(1, n + 1));
        String rightsMatrixString = String.join("\n", lines.subList(n + 2, n + n + 3));

        Grid grid = new Grid(m + 1, n + 1, rightsMatrixString, downsMatrixString);

        int pathLength = MaxPathFinder.findMaxPathLength(grid);
        
        System.out.println(pathLength);
    }

}
