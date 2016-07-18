package kits.bioinfo.alignment.aligner;

import java.util.LinkedList;
import java.util.List;

import kits.bioinfo.util.IntPair;

class GridGraph {

	public final int m;
	public final int n;
	
	final int[][] nodes;
	
	private final List<Edge>[][] sourceEdges;
	
	public final IntPair startNode = new IntPair(0, 0);
	public final IntPair endNode;
	private final List<Edge> edgesToEndNode = new LinkedList<>();
	
	private int endValue;
	
	@SuppressWarnings("unchecked")
	public GridGraph(int m, int n){
		this.m = m;
		this.n = n;
		nodes = new int[m][n];
		endNode = new IntPair(m, n);
		sourceEdges = new LinkedList[m][n]; 
		for(int i=0;i<m;i++){
			for(int j=0;j<n;j++){
				sourceEdges[i][j] = new LinkedList<Edge>();
			}
		}
	}
	
	public void set(IntPair index, int value){
		set(index.first, index.second, value);
	}
	
	public void set(int i, int j, int value){
		nodes[i][j] = value;
	}
	
	public int get(IntPair index){
		if(index.equals(endNode)){
			return endValue;
		} else {
			return get(index.first, index.second);
		}
	}
	
	public int get(int i, int j){
		return nodes[i][j];
	}
	
	static class Edge {
		final IntPair sourceIndex;
		final int weight;
		
		public Edge(IntPair sourceIndex, int weight) {
			this.sourceIndex = sourceIndex;
			this.weight = weight;
		}
	}
	
	public void addEdge(IntPair sourceIndex, IntPair targetIndex, int weigth){
		if(targetIndex.equals(endNode)){
			edgesToEndNode.add(new Edge(sourceIndex, weigth));
		} else {
			sourceEdges[targetIndex.first][targetIndex.second].add(new Edge(sourceIndex, weigth));
		}
	}
	
	public List<Edge> edgesTo(IntPair targetIndex){
		return edgesTo(targetIndex.first, targetIndex.second);
	}
	
	public List<Edge> edgesTo(int i, int j){
		//return sourceEdges[i][j];
		if(i == endNode.first && j == endNode.second){
			return new LinkedList<>(edgesToEndNode);
		} else {
			return new LinkedList<>(sourceEdges[i][j]);
		}
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		for(int j=0;j<n;j++){
			for(int i=0;i<m;i++){
				sb.append(nodes[i][j]).append("\t");
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	public void setValue(int endValue) {
		this.endValue = endValue;
	}

	public int getValue() {
		return endValue;
	}
	
}
