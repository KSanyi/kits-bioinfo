package kits.bioinfo.alignment.aligner;

import java.util.LinkedList;
import java.util.List;

import kits.bioinfo.util.IntPair;

/**
 * 
 * This is a graph that has a grid structure (extended with and end node), it's nodes and edges form a grid:
 * 
 *  O -> O -> O -> O
 *  |    |    |    |
 *  v    v    v    v
 *  O -> O -> O -> O
 *  |    |    |    |
 *  v    v    v    v
 *  O -> O -> O -> O
 *                  \
 *                   O
 *  
 *  The nodes can be accessed via (i, j) coordinates.
 *  It can also have further edges (eg. diagonals) or special start-to-node, or node-to-end edges
 *  An integer value can be assigned tp each node.
 */
class GridGraph {

    public final int m;
    public final int n;
    final int[][] nodes;
    // list of edges pointing to node (i,j)
    private final List<Edge>[][] edgesTo;
    public final IntPair startNode = new IntPair(0, 0);
    public final IntPair endNode;
    private final List<Edge> edgesToEndNode = new LinkedList<>();
    private int endValue;

    @SuppressWarnings("unchecked")
    public GridGraph(int m, int n) {
        this.m = m;
        this.n = n;
        nodes = new int[m][n];
        endNode = new IntPair(m, n);
        edgesTo = new LinkedList[m][n];
        for (int i=0;i<m;i++) {
            for (int j=0;j<n;j++) {
                edgesTo[i][j] = new LinkedList<Edge>();
            }
        }
    }

    public void set(int i, int j, int value) {
        nodes[i][j] = value;
    }

    public int get(IntPair index) {
        if (index.equals(endNode)) {
            return endValue;
        } else {
            return get(index.first(), index.second());
        }
    }

    public int get(int i, int j) {
        return nodes[i][j];
    }
    
    public void setValue(int endValue) {
        this.endValue = endValue;
    }

    public int getValue() {
        return endValue;
    }

    public void addEdge(IntPair sourceIndex, IntPair targetIndex, int weigth) {
        Edge edge = new Edge(sourceIndex, weigth);
        if (targetIndex.equals(endNode)) {
            edgesToEndNode.add(edge);
        } else {
            edgesTo[targetIndex.first()][targetIndex.second()].add(edge);
        }
    }

    public List<Edge> edgesTo(IntPair targetIndex) {
        return edgesTo(targetIndex.first(), targetIndex.second());
    }

    public List<Edge> edgesTo(int i, int j) {
        if (i == endNode.first() && j == endNode.second()) {
            return List.copyOf(edgesToEndNode);
        } else {
            return List.copyOf(edgesTo[i][j]);
        }
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < m; i++) {
                sb.append(nodes[i][j]).append("    ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    record Edge(IntPair sourceIndex, int weight) {
        @Override
        public String toString() {
            return sourceIndex + "--" + weight + "-->";
        }
    }

}
