package kits.bioinfo.infrastructure;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import kits.bioinfo.core.DnaSequence;
import kits.bioinfo.core.RnaSequence;

public class SequenceReader {

	public static DnaSequence readDnaSequenceFromFile(String path) throws IOException{
		List<String> lines = Files.readAllLines(Paths.get(path));
		try {
			return new DnaSequence(lines.get(0));
		} catch(IllegalArgumentException e) {
			// the first line contained some meta info
			return new DnaSequence(lines.get(1));
		}
	}
	
	public static RnaSequence readRnaSequenceFromFile(String path) throws IOException{
		List<String> lines = Files.readAllLines(Paths.get(path));
		try {
			return new RnaSequence(lines.get(0));
		} catch(IllegalArgumentException e) {
			// the first line contained some meta info
			return new RnaSequence(lines.get(1));
		}
	}
	
	public static List<DnaSequence> readDnaSequencesPerLine(String path) throws IOException{
		List<String> lines = Files.readAllLines(Paths.get(path));
		try {
			return lines.stream().map(line -> new DnaSequence(line)).collect(Collectors.toList());
		} catch(IllegalArgumentException e) {
			// the first line contained some meta info
			lines = lines.subList(1, lines.size());
			return lines.stream().map(line -> new DnaSequence(line)).collect(Collectors.toList());
		}
	}
	
	public static DnaSequence readFromFastaFile(String path) throws IOException{
		List<String> lines = Files.readAllLines(Paths.get(path));
		List<String> linesWithoutHeaderLine = lines.subList(1, lines.size());
		return new DnaSequence(String.join("", linesWithoutHeaderLine));
	}
	
	public static List<DnaSequence> readFromFastaQFile(String path) throws IOException{
		List<String> allLines = Files.readAllLines(Paths.get(path));
		List<DnaSequence> sequences = new LinkedList<>(); 
		for(int i=0;i<allLines.size();i++) {
			if(i % 4 == 1) sequences.add(new DnaSequence(allLines.get(i)));
		}
		return sequences;
	}
	
}
