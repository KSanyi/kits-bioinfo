package kits.bioinfo.alignment;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import kits.bioinfo.core.Sequence;

public class EditDistanceFinderTest {

	@Test
	public void test() {
		int editDistance = EditDistanceFinder.findEditDistance(Sequence.of("PLEASANTLY"), Sequence.of("MEANLY"));
		System.out.println(editDistance);

		assertEquals(5, editDistance);
	}

}
