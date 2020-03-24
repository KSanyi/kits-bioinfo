package kits.bioinfo.peptidsequencing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import kits.bioinfo.core.Peptid;

public class MassSpectrometerTest {

    @Test
    public void test1() {
        List<Integer> massSpectrum = MassSpectrometer
                .generateMassSpectrumForCyclidPeptid(new Peptid("MRGPGTTCSRISRGDVNPSGNHLVLVRLAATRPWVVRRAYDPRSDKVEQAHKRPG"));

        System.out.println(massSpectrum);
    }

    @Test
    public void test2() {
        List<Integer> massSpectrum = MassSpectrometer.generateMassSpectrumForCyclidPeptid(new Peptid("LEQN"));

        assertEquals(List.of(0, 113, 114, 128, 129, 227, 242, 242, 257, 355, 356, 370, 371, 484), massSpectrum);
    }

}
