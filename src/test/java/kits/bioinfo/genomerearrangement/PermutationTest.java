package kits.bioinfo.genomerearrangement;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


public class PermutationTest {

    @Test
    public void test1(){
        Permutation permutation = Permutation.parse("(+6 -12 -9 +17 +18 -4 +5 -3 +11 +19 +20 +10 +8 +15 -14 -13 +2 +7 -16 -1)");
        assertEquals(18, permutation.numberOfBreakPoints());
    }
    
}
