package kits.bioinfo.base;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.Assert;
import org.junit.Test;

import static kits.bioinfo.base.Nucleotid.*;

public class NucleotidTest {

	@Test
	public void others(){
		Assert.assertEquals(new HashSet<>(Arrays.asList(C, T, G)), Nucleotid.others(A));
		Assert.assertEquals(new HashSet<>(Arrays.asList(A, T, G)), Nucleotid.others(C));
		Assert.assertEquals(new HashSet<>(Arrays.asList(A, C, G)), Nucleotid.others(T));
		Assert.assertEquals(new HashSet<>(Arrays.asList(A, C, T)), Nucleotid.others(G));
	}
	
	
}
