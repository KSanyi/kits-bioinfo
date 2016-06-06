package kits.bioinfo.assembly;

import org.junit.Test;

public class UniversalStringFinderTest {

	@Test
	public void test() {
		String universalString = UniversalStringFinder.findUniversalString(3);
		System.out.println(universalString);
	}
	
}
