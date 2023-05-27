package agent;

import components.agent.Bear;
import components.agent.Craziness;
import components.scientist.ActnLabel;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CrazinessTest {

    @Test
    public void TestGetName(){
        Craziness craziness = new Craziness(10);
        assertEquals("Craziness", craziness.getName());
    }

    @Test
    public void TestActionMgmt(){
        Craziness craziness = new Craziness(5);
        //ellenőrzi, hogy ha nem NEW_TURN akkor visszatér az eredeti címkével
        assertEquals(ActnLabel.CRAFT, craziness.actionMgmt(ActnLabel.CRAFT));
        //ellenőrzi, ha NEW_TURN, akkor BEAR-el tér vissza
        assertEquals(ActnLabel.CRAZY, craziness.actionMgmt(ActnLabel.NEW_TURN));
    }

    @Test
    public void TestToString(){
        Bear bear = new Bear(5);
        assertEquals("Bear(5)", bear.toString());
    }
}
