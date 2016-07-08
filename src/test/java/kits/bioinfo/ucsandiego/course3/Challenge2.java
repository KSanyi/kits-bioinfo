package kits.bioinfo.ucsandiego.course3;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import kits.bioinfo.math.grid.Grid;
import kits.bioinfo.math.grid.MaxPathFinder;

public class Challenge2 {

	public static void main(String[] args) throws Exception{
		List<String> lines = Files.readAllLines(Paths.get("input/dataset_261_9.txt"));
		String[] dimensions = lines.get(0).split(" ");
		int n = Integer.parseInt(dimensions[0]);
		int m = Integer.parseInt(dimensions[1]);
		
		String downsMatrixString = String.join("\n", lines.subList(1, n+1));
		String rightsMatrixString = String.join("\n", lines.subList(n+2, n+n+3));
		
		Grid grid = new Grid(m+1, n+1, rightsMatrixString, downsMatrixString);
		
		MaxPathFinder.findMaxPath(grid);
	}
	
}
