package gear;

import components.gear.Axe;
import org.junit.Assert;
import org.junit.Test;

public class AxeTest {
    /**
     * Sample test, can be removed later
     */
    @Test
    public void TestConstructor() {
        Axe axe = new Axe();
        Assert.assertEquals(axe.getDuration(), 1);
    }
}
