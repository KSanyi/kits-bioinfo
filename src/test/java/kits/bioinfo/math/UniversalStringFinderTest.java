package kits.bioinfo.math;

import org.junit.Test;

import kits.bioinfo.math.UniversalStringFinder;

public class UniversalStringFinderTest {

	@Test
	public void test() {
		String universalString = UniversalStringFinder.findUniversalString(3);
		System.out.println(universalString);
	}
	
}
