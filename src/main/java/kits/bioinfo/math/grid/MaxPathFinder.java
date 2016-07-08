package kits.bioinfo.math.grid;

import java.util.List;

public class MaxPathFinder {

	public static List<String> findMaxPath(Grid grid){
		int value = getValue(grid, grid.m-1, grid.n-1);
		
		System.out.println(value);
		return null;
	}
	
	private static int getValue(Grid grid, int i, int j){
		int value;
		if(i==0 && j==0){
			value = 0;
		} else if(i == 0){
			value = getValue(grid, 0, j-1) + grid.down(0, j-1);
		} else if(j == 0){
			value = getValue(grid, i-1, 0) + grid.right(i-1, 0); 
		} else {
			value = Math.max(getValue(grid, i-1, j) + grid.right(i-1, j), getValue(grid, i, j-1) + grid.down(i, j-1)); 
		}
		grid.setValue(i, j, value);
		return value;
	}
	
}
