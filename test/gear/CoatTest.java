package gear;

import components.gear.Bag;
import components.gear.Coat;
import components.scientist.ActnLabel;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CoatTest {



    @Test
    public void TestActionMgmtWithOtherActnLabel(){
        int effectiveness = 50;
        Coat coat = new Coat(effectiveness);
        //ellenőrzi, hogy ha nem USED_ON akkor visszatér az eredeti címkével
        assertEquals(ActnLabel.CRAFT, coat.actionMgmt(ActnLabel.CRAFT));
    }

    @Test
    public void TestActionMgmtWithZeroEff(){
        int effectiveness = 0;
        Coat coat = new Coat(effectiveness);
        //mivel az effectiveness 0, ezért az eredetivel kell vissza térjen
        assertEquals(ActnLabel.USED_ON, coat.actionMgmt(ActnLabel.USED_ON));
    }



    @Test
    public void TestActionMgmtWithHundredEff(){
        int effectiveness = 100;
        Coat coat = new Coat(effectiveness);
        //mivel az effectiveness 100, ezért biztos a NO_ACTN-el kell vissza térjen
        assertEquals(ActnLabel.NO_ACTN, coat.actionMgmt(ActnLabel.USED_ON));
    }

    @Test
    public void TestToString(){
        Coat coat = new Coat(10);
        assertEquals("Coat", coat.toString());
    }
}
