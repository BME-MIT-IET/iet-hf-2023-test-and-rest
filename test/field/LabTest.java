package field;


import components.field.Lab;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LabTest {

    @Test
    public void TestGetBearAndSetBEar(){
        Lab lab = new Lab(true);
        assertEquals(true, lab.getHasBear());

        lab.setHasBear(false);
        assertEquals(false, lab.getHasBear());
    }

    /*
    @Test
    public void TestAddGeneticAndTouched(){
        Lab lab = new Lab(false);
        lab.add(new GeneticCode());
        ItemPackage itemPackage = lab.touched();
        assertEquals("Axe(1)", itemPackage.getGears().get(0).toString());
    }*/

}
