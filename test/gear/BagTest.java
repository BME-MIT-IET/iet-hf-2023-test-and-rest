package gear;

import components.gear.Axe;
import components.gear.Bag;
import components.scientist.ActnLabel;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class BagTest {

    @Test
    public void TestToString(){
        Bag bag = new Bag();
        assertEquals("Bag", bag.toString());
    }

    @Test
    public void TestActionMgmt(){
        Bag bag = new Bag();
        //ellenőrzi, hogy ha nem LOOT_MAT akkor visszatér az eredeti címkével
        assertEquals(ActnLabel.CRAFT, bag.actionMgmt(ActnLabel.CRAFT));
        //ellenőrzi, ha LOOT_MAT, akkor AXE-el tér vissza LOOT_DBL
        assertEquals(ActnLabel.LOOT_DBL, bag.actionMgmt(ActnLabel.LOOT_MAT));
    }
}
