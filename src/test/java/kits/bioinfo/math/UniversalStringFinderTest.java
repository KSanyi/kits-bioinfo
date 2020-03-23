package kits.bioinfo.math;

import org.junit.jupiter.api.Test;

public class UniversalStringFinderTest {

    @Test
    public void test() {
        String universalString = UniversalStringFinder.findUniversalString(3);
        System.out.println(universalString);
    }

}
