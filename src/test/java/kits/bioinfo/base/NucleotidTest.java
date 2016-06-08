package kits.bioinfo.base;

import static kits.bioinfo.core.Nucleotid.*;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.Assert;
import org.junit.Test;

import kits.bioinfo.core.Nucleotid;

public class NucleotidTest {

	@Test
	public void others(){
		Assert.assertEquals(new HashSet<>(Arrays.asList(C, T, G)), Nucleotid.others(A));
		Assert.assertEquals(new HashSet<>(Arrays.asList(A, T, G)), Nucleotid.others(C));
		Assert.assertEquals(new HashSet<>(Arrays.asList(A, C, G)), Nucleotid.others(T));
		Assert.assertEquals(new HashSet<>(Arrays.asList(A, C, T)), Nucleotid.others(G));
	}
	
	
}
