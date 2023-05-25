package gear;

import components.gear.Axe;
import components.gear.Gloves;
import components.scientist.ActnLabel;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GlovesTest {

    @Test
    public void TestGetAndSetDuration(){
        Gloves gloves = new Gloves();
        assertEquals(3, gloves.getDuration());

        gloves.setDuration(6);
        assertEquals(6, gloves.getDuration());
    }

    @Test
    public void TestActionMgmt(){
        Gloves gloves = new Gloves();

        //ellenőrzi, ha duration nem nulla, és a címke USED_ON,
        //akkor REFLECT-el kell visszatérjen és a duration-t csökkenteni kell
        assertEquals(ActnLabel.REFLECT, gloves.actionMgmt(ActnLabel.USED_ON));
        assertEquals(2, gloves.getDuration());

        //ellenőrzi, hogy ha duration 0, akkor delete true lesz és
        //a visszatérés pedig az eredeti címke
        gloves.setDuration(0);
        assertEquals(ActnLabel.CRAFT, gloves.actionMgmt(ActnLabel.CRAFT));
        assertEquals(true, gloves.getDelete());
    }

    @Test
    public void TestToString(){
        Gloves gloves = new Gloves();
        gloves.setDuration(5);
        assertEquals("Gloves(5)", gloves.toString());
    }
}
