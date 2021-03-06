package kits.bioinfo.core;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class RibosomeTest {

    @Test
    public void test1() {
        RnaSequence rnaSequence = new RnaSequence("AUGGCCAUGGCGCCCAGAACUGAGAUCAAUAGUACCCGUAUUAACGGGUGA");

        Peptid peptid = Ribosome.translateToAminoAcidSequence(rnaSequence);

        assertEquals("MAMAPRTEINSTRING", peptid.toString());
    }

    @Test
    public void test2() {
        RnaSequence rnaSequence = new RnaSequence(
                "AUGAGAGGUCCCGGGACUACGUGUUCUAGAAUCAGUCGCGGGGAUGUCAAUCCAUCAGGGAAUCAUUUAGUACUGGUGCGAUUAGCAGCCACCAGGCCUUGGGUGGUUCGACGGGCUUACGACCCCCGAUCGGACAAAGUCGAGCAGGCACAUAAAAGACCAGGCGCAGUUCAGAUACGCGUGAGCAAAGUUUCGGCGAACCAUUUUCCCUUGCCUUGUAUGGAAGUGCUUCUAGUUGUAGCAUCGAGCUUACGAAAAGCGGGGAUUCUCUUGCACCGCUGGUCCAUGUCAGGGUCCUUUUUCAUUCCCUCAGAUACUUAUCCCCAAAUCACUUGCAGAG");

        Peptid peptid = Ribosome.translateToAminoAcidSequence(rnaSequence);

        assertEquals("MRGPGTTCSRISRGDVNPSGNHLVLVRLAATRPWVVRRAYDPRSDKVEQAHKRPGAVQIRVSKVSANHFPLPCMEVLLVVASSLRKAGILLHRWSMSGSFFIPSDTYPQITCR", peptid.toString());
    }

}
