package field;

import components.field.ItemPackage;
import components.field.Shelter;
import components.gear.Axe;
import components.gear.Coat;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ShelterTest {

    @Test
    public void TestTouched(){
        Shelter shelter = new Shelter();
        shelter.add(new Axe());
        shelter.add(new Coat(5));
        ItemPackage itemPackage = shelter.touched();
        assertEquals("Axe(1)", itemPackage.getGears().get(0).toString());
        assertEquals("Coat", itemPackage.getGears().get(1).toString());
    }


}
