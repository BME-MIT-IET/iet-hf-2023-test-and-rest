package field;

import components.agent.Material;
import components.field.ItemPackage;
import components.field.Storage;
import components.gear.Axe;
import components.gear.Coat;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StorageTest {


    @Test
    public void TestTouched(){
        Storage storage = new Storage();
        storage.add(new Axe());
        storage.add(new Coat(5));
        ItemPackage itemPackage = storage.touched();
        assertEquals("Axe(1)", itemPackage.getGears().get(0).toString());
        assertEquals("Coat", itemPackage.getGears().get(1).toString());
    }

    @Test
    public void TestAddMaterial(){
        Storage storage = new Storage();
        Material material = new Material("Wood", 2);
        storage.add(material);
        ItemPackage itemPackage = storage.touched();
        assertEquals("Wood(2)", itemPackage.getMaterial().toString());
    }
}
