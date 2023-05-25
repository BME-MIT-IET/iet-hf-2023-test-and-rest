package field;

import components.agent.Agent;
import components.agent.Bear;
import components.field.Field;
import components.field.ItemPackage;
import components.gear.Axe;
import components.gear.Coat;
import components.scientist.Scientist;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FieldTest {

    @Test
    public void TestAddAndRemoveGear(){
        Field field = new Field();
        Axe axe = new Axe();
        field.add(axe);
        field.add(new Coat(2));
        ItemPackage ip = field.touched();
        assertEquals("Axe(1)", ip.getGears().get(0).toString());

        field.remove(axe);
        ip = field.touched();
        assertEquals("Coat", ip.getGears().get(0).toString());
    }

    @Test
    public void TestAcceptAndRemoveScientist(){
        Field field = new Field();
        Scientist scientist = new Scientist();
        field.accept(scientist);

        //field scintists listában benne kell lennie
        assertEquals(1, field.getScientists().size());

        field.remove(scientist);
        //field scintists listának üresnek kell lennie
        assertEquals(true, field.getScientists().isEmpty());
    }

    @Test
    public void TestAcceptBearScientist(){
        Field field = new Field();
        field.accept(new Scientist());
        //egy fertőzött scientistet adok hozzá egy olyan mezőre, ahol áll egy nem fertőzött
        Scientist scientist = new Scientist();
        scientist.addActiveAgent(new Bear(-1));
        field.accept(scientist);
        //az eredetieleg fielden álló játékos Active Agents-t kéri le
        Agent agent = field.getScientists().get(0).getActiveAgents().get(0);
        assertEquals("Bear(-1)", agent.toString());
    }

    @Test
    public void TestAcceptScientistGetBear(){
        Field field = new Field();
        Scientist scientistWithBear = new Scientist();
        scientistWithBear.addActiveAgent(new Bear(-1));
        field.accept(scientistWithBear);
        //egy nem fertőzött scientistet adok hozzá egy olyan mezőre, ahol áll egy fertőzött
        Scientist scientist = new Scientist();
        field.accept(scientist);
        //az újonnan hozzáadott scientist Active Agents-t kéri le
        Agent agent = field.getScientists().get(1).getActiveAgents().get(0);
        assertEquals("Bear(-1)", agent.toString());
    }




}
