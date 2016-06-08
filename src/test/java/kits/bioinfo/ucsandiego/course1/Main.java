package kits.bioinfo.ucsandiego.course1;

import java.io.IOException;
import java.util.Set;

import kits.bioinfo.clump.FastClumpFinder;
import kits.bioinfo.core.Sequence;
import kits.bioinfo.infrastructure.SequenceReader;
import kits.bioinfo.matcher.SubSequenceMatcher;

public class Main {

	public static void main(String[] args) throws IOException {
		clupmsInGenome("input/Vibrio_cholerae.txt");
		//clupmsInGenome("input/E-coli.txt");
	}
	
	private static void patternInCholeraeGenome() throws IOException {
		Sequence choleraeGenome = SequenceReader.readFromFile("input/Vibrio_cholerae.txt");
		Sequence oriCSequence = new Sequence("ATGATCAAG");
		
		System.out.println(new SubSequenceMatcher(oriCSequence).matchStartIndexes(choleraeGenome));
		System.out.println(new SubSequenceMatcher(oriCSequence.reverseComplement()).matchStartIndexes(choleraeGenome));
	}
	
	private static void clupmsInGenome(String path) throws IOException {
		Sequence genome = SequenceReader.readFromFile(path);
		long start = System.currentTimeMillis();
		Set<Sequence> kmersFormingClump = new FastClumpFinder().findKmersFormingClumps(genome, 500, 9, 5);
		long end = System.currentTimeMillis();
		System.out.println("Kmers forming clumps in genome file " + path + ": " + kmersFormingClump);
		System.out.println("Time:" + (end - start)/1000 + " secs");
	}
	
}
