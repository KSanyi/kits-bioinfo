package kits.bioinfo.peptidsequencing;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;

import kits.bioinfo.TestUtil;
import kits.bioinfo.core.Peptid;
import kits.bioinfo.util.RandomSequenceGenerator;

public class CycloPeptidSequencerTest {

	//@Test
	public void test(){
		List<Peptid> peptids = CycloPeptidSequencer.sequencePeptids(Arrays.asList(0, 113, 128, 186, 241, 299, 314, 427));
		
		Set<String> massSequences = peptids.stream()
			.map(peptid -> peptid.aminoAcids.stream()
					.map(aminoAcid -> String.valueOf(aminoAcid.mass)).collect(Collectors.joining("-")))
					.collect(Collectors.toSet());
		
		Assert.assertTrue(TestUtil.equalsInAnyOrder(
				Arrays.asList("128-113-186", "186-113-128", "128-186-113", "113-186-128", "113-128-186", "186-128-113"),
				massSequences));
		
	}
	
	@Test
	public void test2(){
		MassSpectrometer massSpectrometer = new MassSpectrometer();
		RandomSequenceGenerator randomSequenceGenerator = new RandomSequenceGenerator();
		Random random = new Random();
		for(int i=0;i<10;i++){
			Peptid peptid = randomSequenceGenerator.generateRandomPeptid((random.nextInt(10) + 3));
			List<Integer> spectrum = massSpectrometer.generateMassSpectrumForCyclidPeptid(peptid);
			
			List<Peptid> peptids = CycloPeptidSequencer.sequencePeptids(spectrum);
			System.out.println(peptid);
			Assert.assertTrue("Failed for " + peptid, peptids.contains(peptid));	
		}
	}
	
}
