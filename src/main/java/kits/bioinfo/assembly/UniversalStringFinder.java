package kits.bioinfo.assembly;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import kits.bioinfo.assembly.Graph.Edge;

public class UniversalStringFinder {

	public static String findUniversalString(int k) {
		
		int N = (int) Math.pow(2, k);
		
		List<String> strings = IntStream.range(0, N).mapToObj(i -> String.format("%0" + k + "d", Integer.parseInt(Integer.toBinaryString(i)))).collect(Collectors.toList());
		
		Graph<String> deBrujinGraph = buildDeBrujinGraph(strings);
		
		List<String> path = EulerianPathFinder.findEulerianPath(deBrujinGraph);
		return readString(path,k);
	}
	
	public static String findUniversalCircularString(int k) {
		
		int N = (int) Math.pow(2, k);
		
		List<String> strings = IntStream.range(0, N).mapToObj(i -> String.format("%0" + k + "d", Integer.parseInt(Integer.toBinaryString(i)))).collect(Collectors.toList());
		
		Graph<String> deBrujinGraph = buildDeBrujinGraph(strings);
		
		List<String> cycle = EulerianCycleFinder.findEulerianCycle(deBrujinGraph);
		return readCycle(cycle, k);
	}
	
	private static Graph<String> buildDeBrujinGraph(List<String> edgeStrings) {
		Set<Edge<String>> edges = new HashSet<>();
		for(String edgeString : edgeStrings) {
			String fromNode = edgeString.substring(0, edgeString.length()-1);
			String toNode = edgeString.substring(1, edgeString.length());
			
			edges.add(new Edge<>(fromNode, toNode));
		}
		return new Graph<>(edges);
	}
	
	private static String readString(List<String> path, int k) {
		StringBuilder sb = new StringBuilder(path.get(0));
		for(int i=1;i<path.size();i++){
			sb.append(path.get(i).charAt(k-2));
		}
		return sb.toString();
	}
	
	private static String readCycle(List<String> path, int k) {
		StringBuilder sb = new StringBuilder(path.get(0));
		for(int i=1;i<path.size()-k+1;i++){
			sb.append(path.get(i).charAt(k-2));
		}
		return sb.toString();
	}
	
}
