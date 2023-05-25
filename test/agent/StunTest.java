package agent;

import components.agent.Bear;
import components.agent.Stun;
import components.scientist.ActnLabel;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StunTest {

    @Test
    public void TestGetName(){
        Stun stun = new Stun(10);
        assertEquals("Stun", stun.getName());
    }

    @Test
    public void TestActionMgmt(){
        Stun stun = new Stun(5);
        /*
        ha nem ezekközül valamelyik, akkor vissza adja az eredetit
        STD_ACTN
        CRAFT
        LEARN
        MOVE
        NEW_TURN
        CRAZY
         */
        assertEquals(ActnLabel.AXE, stun.actionMgmt(ActnLabel.AXE));

        //ha a halmazból valamelyik, akkor NO_ACTN
        assertEquals(ActnLabel.NO_ACTN, stun.actionMgmt(ActnLabel.CRAFT));
        assertEquals(ActnLabel.NO_ACTN, stun.actionMgmt(ActnLabel.CRAZY));

        //ellenőrzi, ha ROBBED, akkor VALID_ROBBERY-el tér vissza
        assertEquals(ActnLabel.VALID_ROBBERY, stun.actionMgmt(ActnLabel.ROBBED));
    }

    @Test
    public void TestToString(){
        Stun stun = new Stun(5);
        assertEquals("Stun(5)", stun.toString());
    }
}
