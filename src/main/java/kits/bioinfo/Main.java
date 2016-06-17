package kits.bioinfo;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

	public static void main(String[] args) throws Exception {
		List<String> lines = Files.readAllLines(Paths.get("input/dataset_104_4.txt"));
		List<Integer> spectrum = Arrays.asList(lines.get(0).split(" ")).stream()
				.map(mass -> Integer.parseInt(mass))
				.collect(Collectors.toList());
		
		Collections.sort(spectrum);
	}

}
