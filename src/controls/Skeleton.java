package controls;

import components.agent.*;
import components.field.Field;
import components.field.Lab;
import components.field.Storage;
import components.gear.Bag;
import components.gear.Coat;
import components.gear.Gear;
import components.gear.Gloves;
import components.scientist.Scientist;
import components.utils.Game;

import java.io.Console;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * A Skeleton osztály felelős a tesztesetek lefuttatásáért.
 * A felhasználó 5 nagy kategória közül választhat, amiken belül
 * egy újabb kérdés keretein belül választhat a konkrét tesztesetek közül.
 * Az egyes tesztesetek lefutását a metódushívások nevének kiírásával
 * (Osztály.metódus(paraméter)) formátumban, és a visszatérési érték
 * megjelenítésével érzékelteti. A metódushívások behúzása a hívási
 * szintnek megfelelően történik.
 */
public class Skeleton {
    /**
     * A behúzások/metódushívások szintjét nyilvántartó változó.
     */
    public static int tabs;
    /**
     * A setup mód állapotát nyilvántartó változó.
     * Setup módban nem íródnak ki a metódushívások és a visszatérések.
     * Erre a konkrét tesztesek előkészítésekor (kezdőállapot felállítása) van szükség.
     */
    public static boolean setup;

    /**
     * Statikus inicializáló blokk.
     */
    static {
        tabs = -1;
        setup = false;
    }

    /*
    public static void printTabs() {
        tabs++;
        for (int i = 0; i < tabs; i++){
            System.out.print("\t");
        }
    }
    */

    /**
     * Minden metódus meghívja a futásának az elején, és ez a megfelelő behúzással
     * kiírja, hogy melyik osztály melyik metódusa hívódott meg.
     * @param s - A megfelelő metódus hívja, megadja benne, hogy milyen osztály milyen metódusa
     */
    public static void printCall(String s) {
        if (setup) {
            return;
        }
        tabs++;
        for (int i = 0; i < tabs; i++){
            System.out.print("\t");
        }
        System.out.println(s);
    }

    /**
     * Minden metódus meghívja a futásának a végén, és ez a megfelelő behúzással
     * kiírja, hogy melyik osztály melyik metódusa tér vissza, milyen értékkel.
     * (A kollekciók esetén csak darabszámot jelöl meg, de ez a tesztek ellenőrzéséhez megfelelő.)
     * @param s - a visszatérési értéke az ezt hívó metódusnak
     */
    public static void printReturn(String s) {
        if (setup) {
            return;
        }
        for (int i = 0; i < tabs; i++){
            System.out.print("\t");
        }
        System.out.println("return: "+s);
        tabs--;
    }

    /**
     * A tesztesetek kiválasztásakor a felhasználó választásának értelmezését szolgálja.
     * @param mess - Az üzenet, amit kiírunk, és megjelöljük benne, hogy miből választhat a felhasználó
     * @return - A választott opció sorszáma
     */
    private int parseUserOption(String mess) {
        Scanner sc = new Scanner(System.in);
        System.out.println(mess);
        try {
            return sc.nextInt();
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * Az a metódus, ami egy végtelen ciklusban lehetővé teszi a tesztesetek kiválasztását (kilépésig).
     * Itt csak a tesztesetek kategóriáját választhatjuk ki, egy következő lépésben lesz lehetőség
     * a kategórián belül a konkrét tesztesetekből választani.
     */
    public void start() {
        while(true) {
            tabs = -1;
            switch (parseUserOption("Choose category:\n" +
                    "0 Exit\n" +
                    "1 Move\n" +
                    "2 Craft\n" +
                    "3 Field interaction\n" +
                    "4 Agent interaction\n" +
                    "5 Robbing")) {
                case (0):
                    return;
                case (1):
                    move();
                    break;
                case (2):
                    craft();
                    break;
                case (3):
                    field();
                    break;
                /*case (4):
                    agent();
                    break;*/
                case (5):
                    rob();
                    break;
                default:
                    System.out.println("Invalid input");
                    continue;
            }
            System.out.println("Press enter key to continue...");
            new Scanner(System.in).nextLine();
        }
    }

    // REGION MOVE -----------------------------------------------------------------------------------------------

    /**
     * 1. kategória
     * A mozgással kapcsolatos teszteseteket kezelő metódus.
     * Futása során a felhasználónak lehetősége van a konkrét tesztek közül választani.
     */
    private void move () {
        switch (parseUserOption("\nChoose test case:\n" +
                "0 cancel\n" +
                "1 Scientist steps on field\n" +
                "2 Scientist is unable to move because it is stunned\n" +
                "3 Unable to move because there is no empty field")) {
            case (1):
                move_stepOnField();
                break;
            case (2):
                move_stun();
                break;
            case (3):
                move_noSpace();
                break;
            default:
                break;
        }
    }

    /**
     * 1.1
     * Teszt: két szomszédos mező van, egyik en a Scientist.
     * A Scientist lép a saját mezőjéről a szomszédos mezőre.
     */
    private void move_stepOnField () {

    }

    /**
     * 1.2
     * Teszt: két szomszédos mező van, egyik en a Scientist.
     * A Scientist lépni akar a saját mezőjéről a szomszédos mezőre.
     * Stunnolva van, ezért nem tud lépni
     */
    private void move_stun () {

    }

    /**
     * 1.3
     * Teszt: két szomszédos mező van, egyik en a Scientist.
     * A Scientist lépni akar a saját mezőjéről a szomszédos mezőre.
     * A szomszédps mezőn két másik Scientist van, ezért nem tud lépni.
     */
    private void move_noSpace () {

    }
    // END REGION MOVE -----------------------------------------------------------------------------------------------


    // REGION CRAFT -----------------------------------------------------------------------------------------------
    /**
     * 2. kategória
     * A craftolással kapcsolatos teszteseteket kezelő metódus.
     * Futása során a felhasználónak lehetősége van a konkrét tesztek közül választani.
     */
    private void craft() {
        switch(parseUserOption("\nChoose test case:\n" +
                "0 cancel\n" +
                "1 Scientist crafts Stun agent\n" +
                "2 Unable to craft: Stunned\n" +
                "3 Unable to craft: Dementia\n" +
                "4 Unable to craft: no materials\n")) {
            case(1):
                craft_success();
                break;
            case(2):
                craft_stunned();
                break;
            case(3):
                craft_dementia();
                break;
            case(4):
                craft_noMaterial();
                break;
            default:
                break;
        }
    }

    /**
     * 2.1
     * Teszt: a Scientist sikeresen craftol egy ágenst
     */
    private void craft_success() {
        System.out.println("Test case: Scientist crafts Stun agent\n" +
                "(seq: 5.3.16; comm: 5.4.16)\n");
        setup = true;
        Scientist s = new Scientist();
        s.add(new Material("Nucleotide", 100000));
        setup = false;
        s.craft(new GeneticCode(new Stun(3)));
    }

    /**
     * 2.2
     *  Teszt: A Scientist craftolni próbál de stunnolva van
     */
    private void craft_stunned() {
        System.out.println("Test case: Scientist is unable to craft because it is stunned\n" +
                "(seq: 5.3.19; comm: 5.4.19)\n");
        setup = true;
        Scientist s = new Scientist();
        s.add(new Material("Nucleotide", 100000));
        s.usedOn(new Stun(100));
        setup = false;
        s.craft(new GeneticCode(new Stun(3)));
    }

    /**
     * 2.3
     *  Teszt: A Scientist craftolni próbál de demencia hatása miatt nem tud
     */
    private void craft_dementia() {
        System.out.println("Test case: Scientist is unable to craft because it has dementia\n" +
                "(seq: 5.3.18; comm: 5.4.18)\n");
        setup = true;
        Scientist s = new Scientist();
        s.add(new Material("Nucleotide", 100000));
        s.usedOn(new Dementia(100));
        setup = false;
        s.craft(new GeneticCode(new Stun(3)));
    }

    /**
     * 3.4
     *  Teszt: A Scientist craftolni próbál de demencia hatása miatt nem tud
     */
    private void craft_noMaterial() {
        System.out.println("Test case: Scientist is unable to craft because there is not enough material\n" +
                "(seq: 5.3.17; comm: 5.4.17)\n");
        setup = true;
        Scientist s = new Scientist();
        setup = false;
        s.craft(new GeneticCode(new Stun(3)));
    }
    // END CRAFT REGION -----------------------------------------------------------------------------------------------

    // REGION FIELD INTERACTION -----------------------------------------------------------------------------------------------
    /**
     * 3. kategória
     * A field interakciókkal kapcsolatos teszteseteket kezelő metódus.
     * Futása során a felhasználónak lehetősége van a konkrét tesztek közül választani.
     */
    private void field() {
        switch(parseUserOption("\nChoose test case:\n" +
                "0 cancel\n" +
                "1 Unable to touch\n" +
                "2 Picks up material\n" +
                "3 Picks up gear\n" +
                "4 Picks up gear and has to drop one\n" +
                "5 Scientist learns genetic code\n" +
                "6 Scientist learns all genetic codes and wins\n" +
                "7 Unable to learn: Dementia\n" +
                "8 Scientist unable to learn because it has learnt it already")) {
            case(1):
                field_noTouch();
                break;
            case(2):
                field_pickMaterial();
                break;
            case(3):
                field_pickGear();
                break;
            case(4):
                field_pickGearDrop();
                break;
            case(5):
                field_learnCode();
                break;
            case(6):
                field_learnCodeAndWin();
                break;
            case(7):
                field_noLearnDementia();
                break;
            case(8):
                field_noLearnAlready();
                break;
            default:
                break;
        }
    }

    /**
     * 3.1
     *  Teszt: A Scientist le akarja tapogatni a fieldet, de nem tudja
     */
    private void field_noTouch() {
        System.out.println("Test case: Scientist is unable to touch field because it is stunned\n" +
                "(seq: 5.3.12; comm: 5.4.12)\n");
        setup = true;
        Scientist s = new Scientist();
        Field f = new Field();
        f.accept(s);
        s.usedOn(new Stun(100));
        setup = false;
        s.touch();
    }

    /**
     * 3.2
     *  Teszt: A Scientist letapogatja a mezőt és felvesz egy materialt
     */
    private void field_pickMaterial() {
        System.out.println("Test case: Scientist picks up material\n" +
                "(seq: 5.3.13; comm: 5.4.13)\n");
        setup = true;
        Scientist s = new Scientist();
        Storage f = new Storage();
        f.accept(s);
        f.add(new Material("Nucleotide", 100));
        setup = false;
        s.touch();
    }

    /**
     * 3.3
     *  Teszt: A Scientist letapogatja a mezőt és felvesz egy geart
     */
    private void field_pickGear() {
        System.out.println("Test case: Scientist picks up gear and there is enough space\n" +
                "(seq: 5.3.14; comm: 5.4.14)\n");
        setup = true;
        Scientist s = new Scientist();
        Field f = new Field();
        f.accept(s);
        f.add(new Gloves());
        setup = false;
        s.touch();
    }

    /**
     * 3.4
     *  Teszt: A Scientist letapogatja a mezőt és felvesz egy geart
     *  de helyette el kell dobnia egy másikat mert betelt a helye
     */
    private void field_pickGearDrop() {
        System.out.println("Test case: Scientist picks up gear and there is not enough space\n" +
                "(seq: 5.3.15; comm: 5.4.15)\n");
        setup = true;
        Scientist s = new Scientist();
        s.add(new Gloves());
        s.add(new Coat(80));
        s.add(new Bag());
        Field f = new Field();
        f.accept(s);
        f.add(new Gloves());
        setup = false;
        s.touch();
    }

    /**
     * 3.5
     *  Teszt: A Scientist letapogatja a mezőt és megtanul egy genetic code-ot
     */
    private void field_learnCode() {
        System.out.println("Test case: Scientist learns genetic code\n" +
                "(seq: 5.3.4; comm: 5.4.4)\n");
        setup = true;
        Scientist s = new Scientist();
        Lab f = new Lab(false);
        f.accept(s);
        f.add(new GeneticCode(new Stun(100)));
        setup = false;
        s.touch();
    }

    /**
     * 3.6
     *  Teszt: A Scientist megtanulja az összes genetic code-ot és nyer
     */
    private void field_learnCodeAndWin() {
        System.out.println("Test case: Scientist learns all genetic codes and wins\n" +
                "(seq: 5.3.5; comm: 5.4.5)\n");
        setup = true;
        Scientist s = new Scientist();
        Lab f = new Lab(false);
        f.accept(s);
        f.add(new GeneticCode(new Stun(100)));
        Game.setNumGeneticCodes(1);
        setup = false;
        s.touch();
    }

    /**
     * 3.7
     *  Teszt: A Scientist nem tud genetic code-ot tanulni, mert demencia van rajta
     */
    private void field_noLearnDementia() {
        System.out.println("Test case: Scientist unable to learn because of Dementia\n" +
                "(seq: 5.3.7; comm: 5.4.7)\n");
        setup = true;
        Scientist s = new Scientist();
        s.usedOn(new Dementia(100));
        Lab f = new Lab(false);
        f.accept(s);
        f.add(new GeneticCode(new Stun(100)));
        setup = false;
        s.touch();
    }

    /**
     * 3.8
     *  Teszt: A Scientist nem tud genetic code-ot tanulni, mert már megtanulta
     */
    private void field_noLearnAlready() {
        System.out.println("Test case: Scientist unable to learn because it has learnt it already\n" +
                "(seq: 5.3.6; comm: 5.4.6)\n");
        setup = true;
        Scientist s = new Scientist();
        s.learn(new GeneticCode(new Stun(50)));
        Lab f = new Lab(false);
        f.accept(s);
        f.add(new GeneticCode(new Stun(50)));
        setup = false;
        s.touch();
    }
    // END REGION FIELD INTERACTION -----------------------------------------------------------------------------------------------

    // REGION AGENTS -----------------------------------------------------------------------------------------------
    /**
     * 4. kategória
     * Az ágens interakciókkal kapcsolatos teszteseteket kezelő metódus.
     * Futása során a felhasználónak lehetősége van a konkrét tesztek közül választani.
     */
/*    private void agent() {
        switch(parseUserOption("\nChoose test case:\n" +
                "0 cancel\n" +
                "1 Successful use on itself\n" +
                "2 Successful use on other scientist\n" +
                "3 Target is immune\n" +
                "4 Target uses Gloves\n" +
                "5 Target uses Coat\n" +
                "6 Attacker is stunned")) {
            case(1):
                agent_self();
                break;
            case(2):
                agent_other();
                break;
            case(3):
                agent_immune();
                break;
            case(4):
                agent_gloves();
                break;
            case(5):
                agent_coat();
                break;
            case(6):
                agent_stun();
                break;
            default:
                break;
        }
    }*/

/*    *//**
     * 4.1
     *  Teszt: A Scientist Stun agenst használ magán
     *//*
    private void agent_self() {
        System.out.println("Test case: Scientist uses agent on itself\n" +
                "(seq: 5.3.22; comm: 5.4.22)\n");
        setup = true;
        Scientist s = new Scientist();
        s.add(new Stun(100));
        setup = false;
        s.useOn(s);
    }

    *//**
     * 4.2
     *  Teszt: A Scientist Stun agenst használ egy mások scientisten
     *//*
    private void agent_other() {
        System.out.println("Test case: Scientist uses agent on other scientist\n" +
                "(seq: 5.3.21; comm: 5.4.21)\n");
        setup = true;
        Scientist s = new Scientist();
        Scientist s2 = new Scientist();
        s.add(new Stun(100));
        setup = false;
        s.useOn(s2);
    }

    *//**
     * 4.3
     *  Teszt: A Scientist Stun agenst használ egy másik scientisten, de az immunis
     *//*
    private void agent_immune() {
        System.out.println("Test case: Scientist uses agent on other scientist but it is immune\n" +
                "(seq: 5.3.25; comm: 5.4.25)\n");
        setup = true;
        Scientist s = new Scientist();
        Scientist s2 = new Scientist();
        s2.usedOn(new Immunity(100));
        s.add(new Stun(100));
        setup = false;
        s.useOn(s2);
    }

    *//**
     * 4.4
     *  Teszt: A Scientist Stun agenst használ egy másik scientisten, de az kesztyűvel védekezik
     *//*
    private void agent_gloves() {
        System.out.println("Test case: Scientist uses agent on other scientist but it blocks with gloves\n" +
                "(seq: 5.3.23; comm: 5.4.23)\n");
        setup = true;
        Scientist s = new Scientist();
        Scientist s2 = new Scientist();
        s2.add(new Gloves());
        s.add(new Stun(100));
        setup = false;
        s.useOn(s2);
    }

    *//**
     * 4.5
     *  Teszt: A Scientist Stun agenst használ egy másik scientisten, de az köpennyel védekezik
     *//*
    private void agent_coat() {
        System.out.println("Test case: Scientist uses agent on other scientist but it has a chance to block with Coat\n" +
                "(in this test case it is 100% chance)\n" +
                "(seq: 5.3.24; comm: 5.4.24)\n");
        setup = true;
        Scientist s = new Scientist();
        Scientist s2 = new Scientist();
        s2.add(new Coat(100));
        s.add(new Stun(100));
        setup = false;
        s.useOn(s2);
    }

    *//**
     * 4.6
     *  Teszt: A Scientist nem tud ágenst használni, mert stunolva van
     *//*
    private void agent_stun() {
        System.out.println("Test case: Scientist unable to use agent because it is stunned\n" +
                "(seq: 5.3.26; comm: 5.4.26)\n");
        setup = true;
        Scientist s = new Scientist();
        Scientist s2 = new Scientist();
        s.add(new Stun(100));
        s.usedOn(new Stun(100));
        setup = false;
        s.useOn(s2);
    }*/
    // END REGION AGENTS -----------------------------------------------------------------------------------------------

    // REGION ROB -----------------------------------------------------------------------------------------------
    /**
     * 5. kategória
     * A rablással kapcsolatos teszteseteket kezelő metódus.
     * Futása során a felhasználónak lehetősége van a konkrét tesztek közül választani.
     */
    private void rob() {
        switch (parseUserOption("\nChoose test case:\n" +
                "0 cancel\n" +
                "1 Rob material\n" +
                "2 Rob gear\n" +
                "3 Rob gear and drop one\n" +
                "4 Unable to rob: target not stunned")) {
            case (1):
                rob_material();
                break;
            case (2):
                rob_gear();
                break;
            case (3):
                rob_gearDrop();
                break;
            case (4):
                rob_notStunned();
                break;
            default:
                break;
        }
    }

    /**
     * 5.1
     *  Teszt: A Scientist materialt rabol
     */
    private void rob_material() {
        System.out.println("Test case: Scientist robs material from other scientist\n" +
                "(seq: 5.3.9; comm: 5.4.9)\n");
        setup = true;
        Scientist s = new Scientist();
        Scientist s2 = new Scientist();
        s2.add(new Material("Nucleotide", 100));
        s2.usedOn(new Stun(100));
        setup = false;
        s.rob(s2);
    }

    /**
     * 5.2
     *  Teszt: A Scientist geart rabol
     */
    private void rob_gear() {
        System.out.println("Test case: Scientist robs gear from other scientist\n" +
                "(seq: 5.3.10; comm: 5.4.10)\n");
        setup = true;
        Scientist s = new Scientist();
        Scientist s2 = new Scientist();
        s2.usedOn(new Stun(100));
        s2.add(new Coat(0));
        setup = false;
        s.rob(s2);
    }

    /**
     * 5.3
     *  Teszt: A Scientist geart rabol és el kell dobnia egy másikat helyette
     */
    private void rob_gearDrop() {
        System.out.println("Test case: Scientist robs gear from other scientist and there is not enough space\n" +
                "(seq: 5.3.11; comm: 5.4.11)\n");
        setup = true;
        Field f = new Field();
        Scientist s = new Scientist();
        f.accept(s);
        s.add(new Coat(100));
        s.add(new Bag());
        s.add(new Gloves());
        Scientist s2 = new Scientist();
        s2.usedOn(new Stun(100));
        s2.add(new Coat(0));
        setup = false;
        s.rob(s2);
    }

    /**
     * 5.4
     *  Teszt: A Scientist nem tud rabolni, mert a célpont nincs stunnolva
     */
    private void rob_notStunned() {
        System.out.println("Test case: Scientist unable to rob because victim is not stunned\n" +
                "(seq: 5.3.8; 5.4.8)\n");
        setup = true;
        Scientist s = new Scientist();
        Scientist s2 = new Scientist();
        setup = false;
        s.rob(s2);
    }
    // END REGION ROB -----------------------------------------------------------------------------------------------
}
