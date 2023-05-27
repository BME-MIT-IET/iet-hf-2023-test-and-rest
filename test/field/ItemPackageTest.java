package field;

import components.agent.Bear;
import components.agent.GeneticCode;
import components.agent.Material;
import components.field.ItemPackage;
import components.gear.*;
import components.scientist.Inventory;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ItemPackageTest {

    @Test
    public void TestGetAndSetGeneticCode(){
        ItemPackage ip = new ItemPackage();

        Bear bear = new Bear(2);
        GeneticCode geneticCode = new GeneticCode(bear);
        ip.setCode(geneticCode);
        assertEquals("Bear(2)", ip.getCode().toString());
    }

    @Test
    public void TestGetAndSetMaterial(){
        ItemPackage ip = new ItemPackage();

        ip.setMaterial(new Material("nucleotide", 0));
        assertEquals("nucleotide(0)", ip.getMaterial().toString());
    }

    @Test
    public void TestGetAndSetGear(){
        ItemPackage ip = new ItemPackage();
        List<Gear> list = new ArrayList<>();
        list.add(new Axe());
        list.add(new Coat(2));

        ip.setGears(list);
        assertEquals("Axe(1)", ip.getGears().get(0).toString());
        assertEquals("Coat", ip.getGears().get(1).toString());
    }


}
