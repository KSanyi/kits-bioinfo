package kits.bioinfo.infrastructure;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import kits.bioinfo.base.Sequence;

public class SequenceReader {

	public static Sequence readFromFile(String path) throws IOException{
		List<String> lines = Files.readAllLines(Paths.get(path));
		return new Sequence(lines.get(0));
	}
	
	public static List<Sequence> readFromLines(String path) throws IOException{
		List<String> lines = Files.readAllLines(Paths.get(path));
		return lines.stream().map(l -> new Sequence(l)).collect(Collectors.toList());
	}
	
	public static Sequence readFromFastaFile(String path) throws IOException{
		List<String> lines = Files.readAllLines(Paths.get(path));
		List<String> linesWithoutHeaderLine = lines.subList(1, lines.size());
		return new Sequence(String.join("", linesWithoutHeaderLine));
	}
	
	public static List<Sequence> readFromFastaQFile(String path) throws IOException{
		List<String> allLines = Files.readAllLines(Paths.get(path));
		List<Sequence> sequences = new LinkedList<>(); 
		for(int i=0;i<allLines.size();i++) {
			if(i % 4 == 1) sequences.add(new Sequence(allLines.get(i)));
		}
		return sequences;
	}
	
}
