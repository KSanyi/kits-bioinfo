package kits.bioinfo.ucsandiego.course1.part1;

import java.util.List;

import kits.bioinfo.core.DnaSequence;
import kits.bioinfo.matcher.ApproximateSubSequenceMatcher;
import kits.bioinfo.skew.SkewFinder;

public class Quiz2 {

    public static void main(String[] args) {
        question1();
        question2();
        question3();
        question4();
    }

    private static void question1() {
        int distance = new DnaSequence("TGACCCGTTATGCTCGAGTTCGGTCAGAGCGTCATTGCGAGTAGTCGTTTGCTTTCTCAAACTCC").hammingDistance(
                       new DnaSequence("GAGCGATTAAGCGTGACAGCCCCAGGGAACCCACAAAACGTGATCGCAGTCCATCCGATCATACA"));
        System.out.println("Distance: " + distance);
    }

    private static void question2() {
        List<Integer> skewMinIndexes = SkewFinder.calculateSkewMin(new DnaSequence("CATTCCAGTACTTCGATGATGGCGTGAAGA"));
        System.out.println("Skew min indexes: " + skewMinIndexes);
    }

    private static void question3() {
        int matches = new ApproximateSubSequenceMatcher("TGT", 1).matchCount(new DnaSequence("CGTGACAGTGTATGGGCATCTTT"));
        System.out.println("Matches: " + matches);
    }

    private static void question4() {
        System.out.println("TGCAT 2 neighbourse size: " + new DnaSequence("TGCAT").neighbours(2).size());
    }

}
