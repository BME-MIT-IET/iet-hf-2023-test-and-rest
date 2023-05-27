package agent;

import components.agent.Bear;
import components.agent.Material;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MaterialTest {


    @Test
    public void TestGetNameSetName(){
        Material material = new Material("Wood", 10);
        assertEquals("Wood", material.getName());

        material.setName("Steel");
        assertEquals("Steel", material.getName());
    }

    @Test
    public void TestGetQuantitySetQuantity(){
        Material material = new Material("Wood", 10);
        assertEquals(10, material.getQuantity());

        material.setQuantity(3);
        assertEquals(3, material.getQuantity());
    }

    @Test
    public void TestDecreaseQuantity(){
        Material material = new Material("Wood", 10);
        boolean reValue = material.decreaseQuantity(4);
        //visszatérés ellenörzés
        assertEquals(true, reValue);
        //quantity értékének csökkenése
        assertEquals(6, material.getQuantity());

        //túl sokkal csökken a quantity
        assertEquals(false, material.decreaseQuantity(7));
    }

    @Test
    public void TestIncreaseQuantity(){
        Material material = new Material("Wood", 10);
        material.increaseQuantity(6);
        assertEquals(16, material.getQuantity());
    }

    @Test
    public void TestToString(){
        Material material = new Material("Wood",5);
        assertEquals("Wood(5)", material.toString());
    }
}
