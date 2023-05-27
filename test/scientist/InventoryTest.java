package scientist;

import components.agent.Bear;
import components.agent.Craziness;
import components.agent.Material;
import components.gear.Axe;
import components.gear.Bag;
import components.gear.Coat;
import components.gear.Gloves;
import components.scientist.Inventory;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class InventoryTest {


    @Test
    public void TestValidateAction(){
        Inventory inventory = new Inventory();


    }

    @Test
    public void TestAddAndRemoveGear(){
        Inventory inventory = new Inventory();

        //teszteli a feltöltést
        inventory.add(new Axe());
        Bag bag = new Bag();
        inventory.add(bag);
        inventory.add(new Coat(2));
        assertEquals("Axe(1)", inventory.getGears().get(0).toString());

        //teszteli, hogy nem tesz bele valóban 3-nál több Geart
        inventory.add(new Gloves());
        assertEquals(3, inventory.getGears().size());

        //teszteli a kivételt
        inventory.remove(bag);
        assertEquals("Coat", inventory.getGears().get(1).toString());
    }

    @Test
    public void TestAddAndRemoveActiveAgent(){
        Inventory inventory = new Inventory();
        Bear bear = new Bear(-1);

        inventory.addActiveAgent(bear);
        assertEquals("Bear(-1)", inventory.getActiveAgents().get(0).toString());

        inventory.removeActiveAgent(bear);
        assertEquals(0, inventory.getActiveAgents().size());
    }

    @Test
    public void TestAddAndRemoveAgent(){
        Inventory inventory = new Inventory();
        Craziness craziness = new Craziness(3);

        inventory.add(craziness);
        assertEquals("Craziness(3)", inventory.getCrafted().get(0).toString());

        inventory.remove(craziness);
        assertEquals(0, inventory.getCrafted().size());
    }
}
