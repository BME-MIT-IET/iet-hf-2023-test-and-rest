package scientist;

import components.agent.*;
import components.field.Field;
import components.field.ItemPackage;
import components.gear.Axe;
import components.gear.Coat;
import components.gear.Gloves;
import components.scientist.ActnLabel;
import components.scientist.Inventory;
import components.scientist.Scientist;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ScientistTest {

    @Test
    public void TestAddAndRemoveGear(){
        Scientist scientist = new Scientist();
        Field field = new Field();
        Gloves gloves = new Gloves();
        //ráhelyezi a mezőre
        field.accept(scientist);
        scientist.add(gloves);
        //scientist-nél van a kesztyű
        assertEquals("Gloves(3)", scientist.getGears().get(0).toString());

        scientist.remove(gloves);
        //mostmár nem szabad, hogy nála legyen
        assertEquals(0, scientist.getGears().size());

        //mezőre kellett kerülnie
        ItemPackage ip = field.touched();
        assertEquals("Gloves(3)", ip.getGears().get(0).toString());
    }

    @Test
    public void TestValidateAction(){
        Scientist scientist = new Scientist();
        //semmilyen hatás nincs a scientist-en így ugyanazt adja vissza, mint a bemenet
        assertEquals("CRAFT", scientist.validateAction(ActnLabel.CRAFT).toString());

        scientist.addActiveAgent(new Stun(2));
        //stun blokkolja a craft akciót, ezért NO_ACTN az elvárt kimenet
        assertEquals("NO_ACTN", scientist.validateAction(ActnLabel.CRAFT).toString());
    }

    @Test
    public void TestNewTurn(){
        Scientist scientist = new Scientist();
        scientist.addActiveAgent(new Bear(1));
        scientist.addActiveAgent(new Craziness(2));

        //elvárt eredmény, hogy a kör végével már a Bear nem része az inventorynak
        //Craziness csökkent a durration-je
        scientist.newTurn();
        assertEquals("Craziness(1)", scientist.getInventory().getActiveAgents().get(0).toString());
        //méretének 1-nek kell lennie
        assertEquals(1, scientist.getInventory().getActiveAgents().size());
    }

    @Test
    public void TestLearnInvalidAction(){
        Scientist scientist = new Scientist();
        scientist.addActiveAgent(new Dementia(2));
        boolean retValue = scientist.learn(new GeneticCode(new Bear(1)));
        //false-t várunk, mert a Dementia megakadályozza a tanulást
        //illetve azt, hogy a genetikai kódokat tartalmazó lista üres legyen
        assertEquals(false, retValue);
        assertEquals(0, scientist.getKnownGeneticCodes().size());
    }

    @Test
    public void TestLearn(){
        Scientist scientist = new Scientist();
        scientist.add(new Craziness(2));
        scientist.add(new Coat(1));
        boolean retValue = scientist.learn(new GeneticCode(new Bear(1)));

        //false-t kell visszaadjon, mert nincs még meg az összes kód
        //a genetikai kódokat tartalmazó listában ott kell, hogy legyen
        assertEquals(false, retValue);
        assertEquals(1, scientist.getKnownGeneticCodes().size());
    }

    @Test
    public void TestCraftFailure(){
        Scientist scientist = new Scientist();

    }

    @Test
    public void TestUsedOnWithGlove(){
        Scientist scientist = new Scientist();
        scientist.add(new Gloves());
        scientist.usedOn(new Bear(2));

        //ilyenkor az aktív agent-nek üresen kell maradnia
        assertEquals(0, scientist.getActiveAgents().size());
    }

    @Test
    public void TestUsedOnWithoutGlove(){
        Scientist scientist = new Scientist();
        scientist.usedOn(new Bear(2));

        //ilyenkor az aktív agent-nek üresen kell maradnia
        assertEquals(1, scientist.getActiveAgents().size());
    }

    @Test
    public void TestUseOnSuccess(){
        Scientist scientist1 = new Scientist();
        Scientist scientist2 = new Scientist();

        //eredetileg üres a scientist2 aktív agent listája
        assertEquals(0, scientist2.getActiveAgents().size());

        scientist1.add(new Craziness(2));
        scientist1.useOn(scientist2, scientist1.getInventory().getCrafted().get(0));

        //ennek hatására az aktív agent-be be kell kerüljön
        assertEquals(1, scientist2.getActiveAgents().size());
    }

    @Test
    public void TestUseOnFailure(){
        Scientist scientist1 = new Scientist();
        Scientist scientist2 = new Scientist();
        scientist1.addActiveAgent(new Stun(1));


        scientist1.add(new Craziness(2));
        scientist1.useOn(scientist2, scientist1.getInventory().getCrafted().get(0));

        //üres marad a scientist2 aktív agent listája
        assertEquals(0, scientist2.getActiveAgents().size());
    }

    @Test
    public void TestRobbedBy(){

    }

    @Test
    public void TestKillSuccess(){
        Scientist scientist1 = new Scientist();
        Scientist scientist2 = new Scientist();

        scientist1.add(new Axe());
        boolean reValue = scientist1.kill(scientist2);
        //true-nak kell lennie, mert van balta scientist1-nél
        assertEquals(true, reValue);
    }

    @Test
    public void TestKillFailure(){
        Scientist scientist1 = new Scientist();
        Scientist scientist2 = new Scientist();

        boolean reValue = scientist1.kill(scientist2);
        //false-nak kell lennie, mert nincs balta scientist1-nél
        assertEquals(false, reValue);
    }

    @Test
    public void TestKilled(){
        Scientist scientist1 = new Scientist();
        scientist1.addActiveAgent(new Bear(-1));

        //benne van még az aktív agent listában
        assertEquals("Bear(-1)", scientist1.getActiveAgents().get(0).toString());

        scientist1.killed();
        //nincs már benn a listában
        assertEquals(0, scientist1.getActiveAgents().size());
    }

    @Test
    public void TestTouch(){
        Scientist scientist = new Scientist();
        Field field = new Field();
        //ráhelyezi a mezőre
        field.accept(scientist);

        field.add(new Coat(2));
        field.add(new Gloves());

        ItemPackage ip = scientist.touch();
        assertEquals("Coat", ip.getGears().get(0).toString());
        assertEquals("Gloves(3)", ip.getGears().get(1).toString());
    }
}
