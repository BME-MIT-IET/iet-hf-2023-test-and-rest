package gear;

import components.gear.Axe;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GearTest {

    @Test
    public void TestGetAndSetDelete(){
        Axe axe = new Axe();
        assertEquals(false, axe.getDelete());

        axe.setDelete(true);
        assertEquals(true, axe.getDelete());
    }
}
