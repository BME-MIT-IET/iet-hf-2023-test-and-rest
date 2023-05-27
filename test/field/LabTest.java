package field;


import components.agent.Bear;
import components.agent.Craziness;
import components.agent.GeneticCode;
import components.field.ItemPackage;
import components.field.Lab;
import components.gear.Coat;
import components.gear.Gloves;
import components.scientist.Scientist;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class LabTest {

    @Test
    public void TestGetBearAndSetBEar(){
        Lab lab = new Lab(true);
        assertEquals(true, lab.getHasBear());

        lab.setHasBear(false);
        assertEquals(false, lab.getHasBear());
    }

    @Test
    public void TestTouchedAndAddGeneticCode(){
        Lab lab = new Lab(false);
        lab.add(new GeneticCode(new Craziness(2), 3, 1));
        lab.add(new Coat(1));
        lab.add(new Gloves());

        //egy olyan ItemPackage-et kell, hogy visszaadjon, amely ezeket a hozzáadott elemeket tartalmazza
        ItemPackage itemPackage = lab.touched();
        assertEquals("Coat", itemPackage.getGears().get(0).toString());
        assertEquals("Gloves(3)", itemPackage.getGears().get(1).toString());
        assertEquals("Craziness(2) nucleotide: 3 aminoacid: 1", itemPackage.getCode().getDetails());
    }


    @Test
    public void TestAcceptWithNoBearLab(){
        Lab lab = new Lab(false);
        Scientist scientist = new Scientist();

        lab.accept(scientist);
        //mivel a labor nem volt fertőzött, ezért a scientist se vált azzá
        assertEquals(Collections.emptyList(), scientist.getActiveAgents());
    }

    @Test
    public void TestAcceptWithBearLab(){
        Lab lab = new Lab(true);
        Scientist scientist = new Scientist();

        lab.accept(scientist);
        //mivel a labor fertőzött, ezért a scientist is fertőzött lett
        assertEquals("Bear(-1)", scientist.getActiveAgents().get(0).toString());
    }

    @Test
    public void TestAcceptWithBearScientist(){
        Lab lab = new Lab(false);
        //egy fertőzött scientist áll a mezőn
        Scientist scientist1 = new Scientist();
        scientist1.addActiveAgent(new Bear(-1));
        lab.accept(scientist1);


        Scientist scientist2 = new Scientist();
        lab.accept(scientist2);

        //mivel a laboron fertőzött áll, ezért a scientist2 is fertőzött lett
        assertEquals("Bear(-1)", scientist2.getActiveAgents().get(0).toString());
    }

}
