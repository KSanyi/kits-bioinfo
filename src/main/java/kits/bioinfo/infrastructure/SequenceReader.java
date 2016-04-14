package kits.bioinfo.infrastructure;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
	
}
