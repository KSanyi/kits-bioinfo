package kits.bioinfo.math.grid;

public class Grid {

	private Integer[][] values;
	private int[][] rights;
	private int[][] downs;
	
	public final int m;
	public final int n;
	
	public Grid(int m, int n, String rightsMatrix, String downsMatrix){
		this(m, n, parseMatrix(rightsMatrix), parseMatrix(downsMatrix));
	}
	
	public Grid(int m, int n, int[][] rights, int[][] downs){
		this.m = m;
		this.n = n;
		this.rights = rights;
		this.downs = downs;
		values = new Integer[n][m];
	}
	
	public void setValue(int i, int j, int value){
		values[j][i] = value;
	}
	
	public Integer value(int i, int j){
		if(!(0 <= i && i < m && 0 <= j && j < n)) throw new IllegalArgumentException("i, j: " + i + ", " + j);
		return values[j][i];
	}
	
	public Integer right(int i, int j){
		if(!(0 <= i && i < m-1 && 0 <= j && j < n)) throw new IllegalArgumentException("i, j: " + i + ", " + j);
		return rights[j][i];
	}
	
	public Integer down(int i, int j){
		if(!(0 <= i && i < m && 0 <= j && j < n-1)) throw new IllegalArgumentException("i, j: " + i + ", " + j);
		return downs[j][i];
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("values:\n");
		for(int j=0;j<n;j++){
			for(int i=0;i<m;i++){
			sb.append(value(i, j)).append("\t");
			}
			sb.append("\n");
		}
		
		sb.append("rights:\n");
		for(int j=0;j<n;j++){
			for(int i=0;i<m-1;i++){
				sb.append(right(i, j)).append("\t");
			}
			sb.append("\n");
		}
		
		sb.append("downs:\n");
		for(int j=0;j<n-1;j++){
			for(int i=0;i<m;i++){
				sb.append(down(i, j)).append("\t");
			}
			sb.append("\n");
		}
		
		return sb.toString();
	}
	
	private static int[][] parseMatrix(String matrixString){
		String[] rowStrings = matrixString.split("\n");
		int n = rowStrings.length;
		int m = rowStrings[0].split(" ").length;
		int[][] matrix = new int[n][m]; 
		for(int j=0;j<n;j++){
			String rowString = rowStrings[j];
			String[] values = rowString.split(" ");
			for(int i=0;i<m;i++){
				matrix[j][i] = Integer.parseInt(values[i]);
			}
		}
		return matrix;
	}
	
}
