package kits.bioinfo.ucsandiego.course2;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import kits.bioinfo.assembly.KmerCompositioner;
import kits.bioinfo.assembly.KmerGraph;
import kits.bioinfo.assembly.ReadPair;
import kits.bioinfo.assembly.ReadPairAssembler;
import kits.bioinfo.assembly.graph.EulerianPathFinder;
import kits.bioinfo.core.Sequence;

public class Quiz2 {

	public static void main(String[] args) {
		question1();
		question2();
	}
	
	private static void question1() {
		List<Sequence> sequences = Arrays.asList(
				"AAAT", "AATG", "ACCC", "ACGC", "ATAC", "ATCA", "ATGC",
				"CAAA", "CACC", "CATA", "CATC", "CCAG", "CCCA", "CGCT",
				"CTCA", "GCAT", "GCTC", "TACG", "TCAC", "TCAT", "TGCA").stream()
				.map(s -> new Sequence(s)).collect(Collectors.toList());
		KmerGraph graph = KmerGraph.buildDeBrujinGraph(sequences);
		List<Sequence> path = EulerianPathFinder.findEulerianPath(graph);
		System.out.println(KmerCompositioner.readSequenceFromComposition(path));
	}
	
	private static void question2() {
		List<ReadPair> readPairs = Arrays.asList(
				"ACC|ATA", "ACT|ATT", "ATA|TGA", "ATT|TGA", "CAC|GAT", "CCG|TAC", "CGA|ACT",
				"CTG|AGC", "CTG|TTC", "GAA|CTT", "GAT|CTG", "GAT|CTG", "TAC|GAT", "TCT|AAG",
				"TGA|GCT", "TGA|TCT", "TTC|GAA").stream()
				.map(s -> new ReadPair(new Sequence(s.split("\\|")[0]), new Sequence(s.split("\\|")[1]), 1)).collect(Collectors.toList());
		
		ReadPairAssembler.assembleSequence(readPairs).ifPresent(sequence -> System.out.println(sequence));
	}

}
