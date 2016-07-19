package kits.bioinfo.core;

import static kits.bioinfo.core.DnaBase.*;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.Assert;
import org.junit.Test;

import kits.bioinfo.core.DnaBase;

public class NucleotidTest {

	@Test
	public void others() {
		Assert.assertEquals(new HashSet<>(Arrays.asList(C, T, G)), DnaBase.others(A));
		Assert.assertEquals(new HashSet<>(Arrays.asList(A, T, G)), DnaBase.others(C));
		Assert.assertEquals(new HashSet<>(Arrays.asList(A, C, G)), DnaBase.others(T));
		Assert.assertEquals(new HashSet<>(Arrays.asList(A, C, T)), DnaBase.others(G));
	}

}
