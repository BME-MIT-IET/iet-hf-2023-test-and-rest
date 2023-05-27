package agent;

import components.agent.Bear;
import components.agent.GeneticCode;
import components.agent.Material;
import components.scientist.Inventory;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GeneticCodeTest {

    @Test
    public void TestConstructor(){
        GeneticCode geneticCode = new GeneticCode(new Bear(2), 1, 5);

        assertEquals("Bear(2) nucleotide: 1 aminoacid: 5", geneticCode.getDetails());
    }

    @Test
    public void TestCraftFail(){
        //ehhez 1 nucleotide és 5 aminoacid kell
        GeneticCode geneticCode = new GeneticCode(new Bear(2), 1, 5);
        Inventory inventory1 = new Inventory();
        inventory1.add(new Material("nucleotide", 3));
        inventory1.add(new Material("aminoacid", 4));

        //hamissal kell, hogy visszatérjen, mert nincs elég nyersanyag
        boolean reValue = geneticCode.craft(inventory1);
        assertEquals(false, reValue);
        //az inventory tartalmának változatlannak kell maradnia
        assertEquals("nucleotide(3)", inventory1.getMaterials().get("nucleotide").toString());
        assertEquals("aminoacid(4)", inventory1.getMaterials().get("aminoacid").toString());
    }

    @Test
    public void TestCraftSuccess(){
        //ehhez 1 nucleotide és 5 aminoacid kell
        GeneticCode geneticCode = new GeneticCode(new Bear(2), 1, 5);
        Inventory inventory1 = new Inventory();
        inventory1.add(new Material("nucleotide", 3));
        inventory1.add(new Material("aminoacid", 6));

        //igazzal kell, hogy visszatérjen, mert van elég nyersanyag
        boolean reValue = geneticCode.craft(inventory1);
        assertEquals(true, reValue);
        //az inventory tartalmát csökkenteni kell
        assertEquals("nucleotide(2)", inventory1.getMaterials().get("nucleotide").toString());
        assertEquals("aminoacid(1)", inventory1.getMaterials().get("aminoacid").toString());
    }
}
