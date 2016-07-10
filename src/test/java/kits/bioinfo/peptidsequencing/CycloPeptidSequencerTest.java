package kits.bioinfo.peptidsequencing;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;

import kits.bioinfo.TestUtil.EqualsInAnyOrder;
import kits.bioinfo.core.Peptid;
import kits.bioinfo.util.RandomSequenceGenerator;

public class CycloPeptidSequencerTest {

	@Test
	public void test() {
		Set<Peptid> peptids = new SimpleCycloPeptidSequencer().sequencePeptids(Arrays.asList(0, 113, 128, 186, 241, 299, 314, 427));

		Set<String> massSequences = peptids.stream()
				.map(peptid -> peptid.stream().map(aminoAcid -> String.valueOf(aminoAcid.mass)).collect(Collectors.joining("-")))
				.collect(Collectors.toSet());

		Assert.assertThat(massSequences,
				new EqualsInAnyOrder<>("128-113-186", "186-113-128", "128-186-113", "113-186-128", "113-128-186", "186-128-113"));
	}

	@Test
	public void test2() {
		MassSpectrometer massSpectrometer = new MassSpectrometer();
		RandomSequenceGenerator randomSequenceGenerator = new RandomSequenceGenerator();
		Random random = new Random();
		for (int i = 0; i < 10; i++) {
			Peptid peptid = randomSequenceGenerator.generateRandomPeptid((random.nextInt(10) + 3));
			List<Integer> spectrum = massSpectrometer.generateMassSpectrumForCyclidPeptid(peptid);

			Set<Peptid> peptids = new SimpleCycloPeptidSequencer().sequencePeptids(spectrum);
			Assert.assertTrue("Failed for " + peptid, peptids.contains(peptid));
		}
	}

	@Test
	public void testWithImperfectData() {
		List<Integer> experimentalSpectrum = Arrays.asList(0, 71, 71, 71, 71, 87, 99, 99, 99, 103, 103, 113, 115, 115, 115, 128, 129, 131, 137, 142,
				142, 147, 158, 163, 163, 170, 184, 202, 202, 213, 214, 218, 228, 234, 240, 241, 244, 246, 250, 252, 255, 257, 262, 262, 275, 278, 289,
				299, 305, 312, 321, 326, 333, 333, 349, 351, 355, 355, 357, 361, 365, 370, 372, 374, 377, 381, 392, 404, 404, 404, 425, 428, 432, 436,
				441, 452, 454, 468, 470, 475, 476, 477, 480, 485, 491, 499, 503, 503, 514, 518, 519, 539, 540, 547, 551, 555, 556, 567, 569, 570, 574,
				588, 605, 606, 608, 613, 617, 618, 618, 627, 632, 633, 638, 654, 669, 670, 670, 676, 679, 684, 687, 689, 698, 703, 703, 716, 720, 721,
				731, 732, 732, 737, 755, 766, 769, 773, 774, 787, 791, 797, 801, 802, 802, 802, 807, 808, 819, 826, 831, 832, 834, 845, 852, 858, 872,
				873, 890, 895, 895, 902, 905, 910, 916, 917, 922, 923, 929, 929, 931, 939, 944, 944, 960, 965, 973, 976, 982, 994, 1009, 1010, 1019,
				1021, 1025, 1036, 1036, 1042, 1043, 1044, 1044, 1046, 1047, 1053, 1054, 1058, 1059, 1065, 1081, 1107, 1107, 1107, 1123, 1124, 1124,
				1136, 1146, 1147, 1152, 1152, 1156, 1157, 1157, 1173, 1173, 1174, 1178, 1178, 1183, 1184, 1194, 1206, 1206, 1207, 1223, 1223, 1223,
				1249, 1265, 1271, 1272, 1276, 1277, 1283, 1284, 1286, 1286, 1287, 1288, 1294, 1294, 1305, 1309, 1311, 1320, 1321, 1336, 1348, 1354,
				1357, 1365, 1370, 1386, 1386, 1391, 1399, 1401, 1401, 1407, 1408, 1413, 1414, 1420, 1425, 1428, 1435, 1435, 1457, 1458, 1472, 1478,
				1485, 1496, 1498, 1499, 1504, 1511, 1522, 1523, 1528, 1528, 1528, 1529, 1533, 1539, 1543, 1556, 1557, 1561, 1564, 1575, 1593, 1598,
				1598, 1599, 1609, 1610, 1614, 1627, 1627, 1632, 1641, 1643, 1646, 1651, 1654, 1660, 1660, 1661, 1676, 1692, 1697, 1698, 1703, 1712,
				1712, 1713, 1717, 1722, 1724, 1725, 1742, 1756, 1760, 1761, 1763, 1774, 1775, 1779, 1783, 1790, 1791, 1811, 1812, 1816, 1827, 1827,
				1831, 1839, 1845, 1850, 1853, 1854, 1855, 1860, 1862, 1876, 1878, 1889, 1894, 1898, 1902, 1905, 1926, 1926, 1926, 1938, 1949, 1953,
				1956, 1958, 1960, 1965, 1969, 1973, 1975, 1975, 1979, 1981, 1997, 1997, 2004, 2009, 2018, 2025, 2031, 2041, 2052, 2055, 2068, 2068,
				2073, 2075, 2078, 2080, 2084, 2086, 2089, 2090, 2096, 2102, 2112, 2116, 2117, 2128, 2128, 2146, 2160, 2167, 2167, 2172, 2183, 2188,
				2188, 2193, 2199, 2201, 2202, 2215, 2215, 2215, 2217, 2227, 2227, 2231, 2231, 2231, 2243, 2259, 2259, 2259, 2259, 2330);
		Set<Peptid> peptids = new CycloPeptidSequencer(366, 20).sequencePeptids(experimentalSpectrum);
		
		Assert.assertThat(peptids, new EqualsInAnyOrder<>(new Peptid("FVCMASYDCHDVYVAAAIDEGA"), new Peptid("FVCMASYDCHDVYVAAALDEGA")));
	}

	@Test
	public void testWithImperfectData2() {
		List<Integer> experimentalSpectrum = Arrays.asList(114, 965, 617, 245, 103, 921, 850, 1091, 692, 965, 807, 401, 646, 718, 57, 564, 128, 978,
				216, 548, 692, 229, 579, 373, 846, 674, 1093, 144, 630, 227, 793, 502, 286, 1081, 344, 749, 1093, 1066, 350, 342, 190, 216, 206, 445,
				406, 852, 1137, 476, 908, 901, 951, 949, 401, 319, 761, 158, 692, 1036, 793, 678, 1080, 330, 101, 103, 1079, 172, 591, 229, 967, 694,
				451, 603, 500, 273, 158, 821, 706, 243, 1137, 589, 113, 0, 247, 115, 635, 488, 749, 788, 844, 520, 115, 57, 293, 1079, 978, 605, 502,
				399, 692, 1022, 988, 388, 1091, 731, 445, 795, 577, 850, 908, 516, 273, 1050, 387, 87, 502, 743, 433, 1036, 806, 286, 1107, 463, 1004,
				344, 615, 502, 101, 348, 921, 864, 1194, 947, 559, 875);
		Set<Peptid> peptids = new CycloPeptidSequencer(357, 19).sequencePeptids(experimentalSpectrum);
		
		Assert.assertThat(peptids, new EqualsInAnyOrder<>(new Peptid("DTGDNLCCSGTAG"), new Peptid("DTGDNICCSGTAG")));
	}

}
