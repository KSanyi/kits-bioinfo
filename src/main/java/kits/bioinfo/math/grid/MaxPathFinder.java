package kits.bioinfo.math.grid;

public class MaxPathFinder {

    public static int findMaxPathLength(Grid grid) {
        
        grid.setValue(0, 0, 0);
        
        for(int i=1;i<grid.m;i++) {
            grid.setValue(i, 0, grid.value(i-1, 0) + grid.right(i-1, 0));
        }
        
        for(int j=1;j<grid.n;j++) {
            grid.setValue(0, j, grid.value(0, j-1) + grid.down(0, j-1));
        }
        
        for(int i=1;i<grid.m;i++) {
            for(int j=1;j<grid.n;j++) {
                int value = Math.max(grid.value(i - 1, j) + grid.right(i - 1, j), grid.value(i, j - 1) + grid.down(i, j - 1));
                grid.setValue(i, j, value);
            }
        }
        
        return grid.value(grid.m-1, grid.n-1);
    }

}
