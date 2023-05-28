package gear;

import components.gear.Axe;
import components.scientist.ActnLabel;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AxeTest {
    /**
     * Sample test, can be removed later
     */
    @Test
    public void TestConstructor() {
        Axe axe = new Axe();
        assertEquals(axe.getDuration(), 1);
    }

    @Test
    public void TestGetAndSetDuration(){
        Axe axe = new Axe();
        assertEquals(1, axe.getDuration());
        axe.setDuration(3);
        assertEquals(3, axe.getDuration());
    }

    @Test
    public void TestActionMgmt(){
        Axe axe = new Axe();
        //ellenőrzi, hogy ha nem KILL akkor visszatér az eredeti címkével
        assertEquals(ActnLabel.CRAFT, axe.actionMgmt(ActnLabel.CRAFT));
        //ellenőrzi, ha KILL, akkor AXE-el tér vissza
        assertEquals(ActnLabel.AXE, axe.actionMgmt(ActnLabel.KILL));
        //ellenőrzi, hogy a KILL hatására csökken-e a duration
        assertEquals(0, axe.getDuration());
    }

    @Test
    public void TestToString(){
        Axe axe = new Axe();
        axe.setDuration(5);
        assertEquals("Axe(5)", axe.toString());
    }

    @Test
    public void TestToString2(){
        Axe axe = new Axe();
        assertEquals("Axe(1)", axe.toString());
    }
}
