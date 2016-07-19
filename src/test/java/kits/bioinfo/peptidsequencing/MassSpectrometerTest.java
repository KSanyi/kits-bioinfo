package kits.bioinfo.peptidsequencing;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import kits.bioinfo.core.Peptid;

public class MassSpectrometerTest {

	@Test
	public void test1() {
		List<Integer> massSpectrum = new MassSpectrometer()
				.generateMassSpectrumForCyclidPeptid(new Peptid("MRGPGTTCSRISRGDVNPSGNHLVLVRLAATRPWVVRRAYDPRSDKVEQAHKRPG"));

		System.out.println(massSpectrum);
	}

	@Test
	public void test2() {
		List<Integer> massSpectrum = new MassSpectrometer().generateMassSpectrumForCyclidPeptid(new Peptid("LEQN"));

		Assert.assertEquals(Arrays.asList(0, 113, 114, 128, 129, 227, 242, 242, 257, 355, 356, 370, 371, 484), massSpectrum);
	}

}
