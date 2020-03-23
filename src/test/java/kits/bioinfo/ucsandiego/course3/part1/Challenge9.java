package kits.bioinfo.ucsandiego.course3.part1;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import kits.bioinfo.alignment.aligner.AlignmentResult;
import kits.bioinfo.alignment.aligner.AlignmentUtil;
import kits.bioinfo.alignment.aligner.OverlappingSequenceAligner;
import kits.bioinfo.alignment.scorefunction.ScoreFunction;
import kits.bioinfo.core.Sequence;

public class Challenge9 {

    /**
     * Code Challenge: Solve the Overlap Alignment Problem. Input: Two strings v
     * and w, each of length at most 1000. Output: The score of an optimal
     * overlap alignment of v and w, followed by an alignment of a suffix v' of
     * v and a prefix w' of w achieving this maximum score. Use an alignment
     * score in which matches count +1 and both the mismatch and indel penalties
     * are 2.
     */
    @Test
    public void test2() throws Exception {
        List<String> lines = Files.readAllLines(Paths.get("input/dataset_248_7.txt"));

        OverlappingSequenceAligner<Character> aligner = new OverlappingSequenceAligner<Character>(ScoreFunction.basic(1, 2, 2));
        AlignmentResult<Character> alignment = aligner.findOneAlignment(Sequence.of(lines.get(0)), Sequence.of(lines.get(1)));

        System.out.println(alignment.score);
        System.out.println(printSequence(alignment.sequence1));
        System.out.println(printSequence(alignment.sequence2));

        AlignmentUtil.checkAlignment(alignment, ScoreFunction.basic(1, 2, 2));

        List<String> resultLines = Files.readAllLines(Paths.get("output/output_248_7.txt"));
        assertEquals(resultLines.get(0), String.valueOf(alignment.score));
        assertEquals(resultLines.get(1), printSequence(alignment.sequence1));
        assertEquals(resultLines.get(2), printSequence(alignment.sequence2));
    }

    private static String printSequence(Sequence<Character> sequence) {
        return sequence.stream().map(n -> n != null ? n.toString() : "-").collect(Collectors.joining());
    }

}
