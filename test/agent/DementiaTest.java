package agent;

import components.agent.Bear;
import components.agent.Dementia;
import components.scientist.ActnLabel;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DementiaTest {

    @Test
    public void TestGetName(){
        Dementia dementia = new Dementia(10);
        assertEquals("Dementia", dementia.getName());
    }

    @Test
    public void TestActionMgmt(){
        Dementia dementia = new Dementia(5);
        //ellenőrzi, hogy ha nem CRAFT vagy LEARN akkor visszatér az eredeti címkével
        assertEquals(ActnLabel.AXE, dementia.actionMgmt(ActnLabel.AXE));
        //ellenőrzi, ha CRAFT, akkor NO_ACTN-el tér vissza
        assertEquals(ActnLabel.NO_ACTN, dementia.actionMgmt(ActnLabel.CRAFT));
        //ellenőrzi, ha LEARN, akkor NO_ACTN-el tér vissza
        assertEquals(ActnLabel.NO_ACTN, dementia.actionMgmt(ActnLabel.LEARN));
    }

    @Test
    public void TestToString(){
        Dementia dementia = new Dementia(5);
        assertEquals("Dementia(5)", dementia.toString());
    }
}
