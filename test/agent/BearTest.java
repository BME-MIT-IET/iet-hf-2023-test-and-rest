package agent;

import components.agent.Bear;
import components.gear.Axe;
import components.scientist.ActnLabel;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BearTest {

    @Test
    public void TestGetName(){
        Bear bear = new Bear(10);
        assertEquals("Bear", bear.getName());
    }

    @Test
    public void TestActionMgmt(){
        Bear bear = new Bear(5);
        //ellenőrzi, hogy ha nem NEW_FIELD vagy NEW_TURN akkor visszatér az eredeti címkével
        assertEquals(ActnLabel.CRAFT, bear.actionMgmt(ActnLabel.CRAFT));
        //ellenőrzi, ha NEW_FIELD, akkor BEAR-el tér vissza
        assertEquals(ActnLabel.BEAR, bear.actionMgmt(ActnLabel.NEW_FIELD));
        //ellenőrzi, ha NEW_TURN, akkor BEAR-el tér vissza
        assertEquals(ActnLabel.BEAR, bear.actionMgmt(ActnLabel.NEW_TURN));
    }

    @Test
    public void TestToString(){
        Bear bear = new Bear(5);
        assertEquals("Bear(5)", bear.toString());
    }
}
