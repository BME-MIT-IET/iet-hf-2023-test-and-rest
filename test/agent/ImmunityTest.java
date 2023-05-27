package agent;

import components.agent.Bear;
import components.agent.Immunity;
import components.scientist.ActnLabel;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ImmunityTest {

    @Test
    public void TestGetName(){
        Immunity immunity = new Immunity(10);
        assertEquals("Immunity", immunity.getName());
    }

    @Test
    public void TestActionMgmt(){
        Immunity immunity = new Immunity(5);
        //ellenőrzi, hogy ha nem USED_ON vagy REFLECT akkor visszatér az eredeti címkével
        assertEquals(ActnLabel.CRAFT, immunity.actionMgmt(ActnLabel.CRAFT));
        //ellenőrzi, ha USED_ON, akkor NO_ACTN-el tér vissza
        assertEquals(ActnLabel.NO_ACTN, immunity.actionMgmt(ActnLabel.USED_ON));
        //ellenőrzi, ha REFLECT, akkor NO_ACTN-el tér vissza
        assertEquals(ActnLabel.NO_ACTN, immunity.actionMgmt(ActnLabel.REFLECT));
    }

    @Test
    public void TestToString(){
        Immunity immunity = new Immunity(5);
        assertEquals("Immunity(5)", immunity.toString());
    }
}
