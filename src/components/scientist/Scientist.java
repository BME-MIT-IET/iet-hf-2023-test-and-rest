package components.scientist;

import components.agent.*;
import components.field.Field;
import components.field.ItemPackage;
import components.gear.Gear;
import components.scientist.ActnLabel;
import components.utils.Game;
import controls.Skeleton;
import main.Main;
import org.ietf.jgss.GSSContext;

import java.util.*;

import static components.scientist.ActnLabel.*;

/**
 * A játékos által irányítandó Scientist-et reprezentáló osztály. Léptethető, megszabható,
 * miket vegyen fel és milyen ágenseket, kin használjon.
 */
public class Scientist {
    /**
     * A Scientist által tárolt objektumokat kezeli
     */
    private final Inventory inventory;
    /**
     * A mező, melyen az adott pillanatban a Scientist áll
     */
    private Field field;

    /**
     * A varázsló neve
     */

    public Scientist() {
        inventory = new Inventory();
    }

    public Field getField(){return field;}

    public void setField(Field f) {
        field = f;
    }

    /**
     * Visszaadja az Inventoryt
     * @return a virológus Inventoryja
     */
    public Inventory getInventory() {
        return inventory;
    }

    /**
     * CSökkenti az ágensek időtartamát, eltávolítja a lejárt hatásidejű ágenseket
     */
    public void newTurn() {
        for(Agent a : getActiveAgents()) {
            a.step();
        }
        inventory.removeInactiveAgents();
    }

    /**
     * Egy véletlenszerű mezőre lép, ahol még van hely
     */
    public void move() {
        Skeleton.printCall("Scientist.move()");
        //Megnézzük, hogy mozoghatunk-e, ha nem azt az ACTION-t kapjuk vissza, amit beadtunk
        //akkor befejezzük a folyamatot.
        if(inventory.validateAction(MOVE)!=MOVE){
            Skeleton.printReturn("void");
            return;
        }
        List<Field> nextField = field.getEmptyNeighbours();
        if(nextField.isEmpty()) {
            Skeleton.printReturn("void");
            return;
        }
        //A mozgásunk alapból random, tehát a medvevírus nem befolyásolja ezt a részét a programnak.
        field.remove(this);
        Random rand = Game.random;
        nextField.get(rand.nextInt(nextField.size())).accept(this);
        Skeleton.printReturn("void");
    }

    /**
     * A Scientist megtanulja az adott ágens
     * elkészítéséhez szükséges genetikai kódot
     * @param gc GeneticCode objektum, amelyet a Scientist megtanul
     */
    public boolean learn(GeneticCode gc) {
        Skeleton.printCall("Scientist.learn()");
        if (inventory.validateAction(LEARN) == LEARN) {
            // Scientist tud tanulni
            // hozzáadás inventoryhoz. az inventory eldobja a duplikátumot
            if(inventory.add(gc)) {
                // az inventorytól elkéri a megtanult genetikai kódokat
                Set<GeneticCode> knownGeneticCodes = inventory.getKnownGeneticCodes();
                // a gamenek elküldi a megtanult kódok számát
                return Game.won(this);
            }
        }
        return false;
    }

    /**
     * A Scientist elkészíti a paraméterben megadott
     * és ismert kódú ágenst. Ha van hozzá elegendő anyaga, akkor azok le is vonódnak.
     */
    public boolean craft(GeneticCode gc) {
        if(inventory.validateAction(CRAFT)==CRAFT) {
            return gc.craft(inventory);
        }
        return false;
    }

    /**
     * A Scientist felhasznál egy ágenst a megadott
     * Scientist-en
     * @param s Scientist objektum, akin felhasználjuk az ágenst
     */
    public void useOn(Scientist s,Agent a) {
        /*
        Megnézzük, hogy végrehajthatjuk-e ezt a cselekvést, vagy valami blokkol,
        ha igen akkor vége a metódusnak.
         */
        if(inventory.validateAction(STD_ACTN)!=STD_ACTN){
            return;
        }
        s.usedOn(a);
        inventory.remove(a);
    }

    /**
     * Lekezeli, hogy mi történik, ha a Scientistre felkennek
     * egy paraméterben megadott ágenst
     * @param a ágens, amellyel megkenik a Scientistet
     */
    public void usedOn(Agent a) {
        Skeleton.printCall("Scientist.usedOn()");
        /*
        Eltároljuk, a cselekvőképességünket és megnézzük mit tehetünk,
        ha REFLECT érkezik, akkor van kesztyűnk,
        ezért eltároljuk a bekapott ágenst és befejezzük a metódust,
        ha USED-ON-t akkor aktiválódik rajtunk az ágens.
         */
        ActnLabel response = inventory.validateAction(USED_ON);
        if(response==REFLECT){
            inventory.add(a);
            return;
        } else if(response==USED_ON){
            inventory.addActiveAgent(a);
        }
    }

    /**
     * A Scientist a paraméterben megadott Scientistet
     * kirabolja, ha sikeres, akkor igazzal tér vissza, ha nem, akkor hamissal
     * @param s Scientist objektum, aki kirablásra kerül
     * @return egy boolean, amely ha igaz, akkor rabolható a paraméterül kapott Scientist, hamis, amennyiben nem
     */
    public boolean rob(Scientist s) {
        // validáció akció
        if (inventory.validateAction(STD_ACTN) != STD_ACTN) {
            return false;
        }

        return s.robbedBy(this);
    }

    /**
     * A Scientist véletlenszerűen anyagkészletet vagy
     * felszerelést veszít el, ha sikeres, akkor igazzal tér vissza, ha nem, akkor hamissal
     * @param s Scientist objektum, aki kirabol
     * @return egy boolean, amely ha igaz, akkor sikeres volt a rablás, hamis, amennyiben nem
     */
    public boolean robbedBy(Scientist s) {
        // validáció rablás
        if (inventory.validateAction(ROBBED) != VALID_ROBBERY) {
            return false;
        }
        // meg kell nézni, hogy lehet-e egyáltalán geart rabolni:
        if (this.getInventory().getGears().size() > 0) {
            // lehet tehát ha nincs elég hely, akkor helyet kell csinálni
            // egészen addig kell próbálkozni amíg nincs elég hely
            while (s.getInventory().getGears().size() >= 3) {
                Game.drop();
            }
        }
        // materialok megszerzése saját inventoryból
        // material helyhiány nem lehet probléma
        Material mat = inventory.getRandomMaterial();
        if (mat != null) {
            // material átadása a támadónak
            s.add(mat);
        }
        // gearek megszerzése
        Gear robbedgear = inventory.getRandomGear();
        if (robbedgear != null) {
            // gear átadása a támadónak
            // ha nincs hely false lesz
            s.add(robbedgear);
            inventory.remove(robbedgear);
        }
        return true;
    }

    public void remove(Gear g){
        inventory.remove(g);
        field.add(g);
    }

    /**
     * A Scientist felveszi a paraméterként megadott
     * védőfelszerelést, ha pedig elérné a maximáls mennyiséget, kidob egyet arra a
     * Field-re, amelyen az adott pillanatban áll.
     * @param g Gear objektum, amelyet a Scientist felvesz
     */
    public void add(Gear g) {
        Skeleton.printCall("Scientist.add(Gear)");
        //van elég hely?
        List<Gear> gears = inventory.getGears();
        if (gears.size() < 3) {
            // gear átadása az inventorynak
            inventory.add(g);
        }

        Skeleton.printReturn("void");
    }

    /**
     * A Scientist-hez hozzáadódik a paraméterben
     * megadott Material
     * @param m Material objektum, anyag, amelyet a Scientist összegyűjtött
     */
    public void add(Material m) {
        Skeleton.printCall("Scientist.add(Material)");
        // material átadása az inventorynak
        inventory.add(m);
        Skeleton.printReturn("void");
    }

    /**
     * Hozzáadja a paraméterben megadott ágenst az előállított
     * ágensek listájához
     * @param a Agent objektum, ágens, amelyet a Scientist előállított
     */
    public void add(Agent a) {
        Skeleton.printCall("Scientist.add(Agent)");
        inventory.add(a);
        Skeleton.printReturn("void");
    }

    /**
     * A Scientist-hez hozzáadódik a paraméterben
     * megadott Genetic Code
     * @param gc GeneticCode objektum, amelyet a Scientist megkap
     */
    public void add(GeneticCode gc) {
        Skeleton.printCall("Scientist.add(GeneticCode)");
        inventory.add(gc);
        Skeleton.printReturn("void");
    }

    public void addActiveAgent(Agent a) {
        Skeleton.printCall("Scientist.addActiveAgent(Agent)");
        inventory.addActiveAgent(a);
        Skeleton.printReturn("void");
    }

    /**
     * A paraméterben megadott akcióhoz tartozó címkét
     * az összes aktív ágensnek és felszerelésnek, amelyek a címke értékétől függően
     * változtathatnak azon. A metódus egy olyan címkét ad vissza, amely megadja, hogy
     * az aktív hatások mellett végrehajtható-e a cselekvés, illetve, ha igen, milyen
     * formában
     * @param id az akcióhoz tartozó címke
     * @return címke, ami a Scientist állapotától függően validálja a cselekvést
     */
    public ActnLabel validateAction(ActnLabel id) {
        return inventory.validateAction(id);
    }

    /**
     * A virológus megöli a paraméterben megadott virológust, amenniyben van, még használható baltája
     * @param s A megölendő virológus
     */
    public boolean kill(Scientist s) {
        return inventory.validateAction(KILL) == AXE;
    }

    /**
     * A holott virológus már nem fertőz, így ha medvevírussal fertőzött, azt el kell távolítani róla
     */
    public void killed() {
        inventory.removeActiveAgent(new Bear(-1));
    }

    // Additional getter delegates for proto testing. Might also need later-------------------------------------------

    /**
     * Megadja a virológus által ismert genetikai kódokat.
     */
    public Set<GeneticCode> getKnownGeneticCodes() {
        return inventory.getKnownGeneticCodes();
    }

    public List<Agent> getCrafted() {
        return inventory.getCrafted();
    }

    public List<Agent> getActiveAgents() {
        return inventory.getActiveAgents();
    }

    public Map<String, Material> getMaterials() {
        return inventory.getMaterials();
    }

    public List<Gear> getGears() {
        return inventory.getGears();
    }

    // Additional methods to get around random elements in game-----------------------------------------------------------

    /**
     * Egy véletlenszerű mezőre lép, ahol még van hely
     */
    public boolean move(Field f) {
        Skeleton.printCall("Scientist.move()");
        //Megnézzük, hogy mozoghatunk-e, ha nem azt az ACTION-t kapjuk vissza, amit beadtunk
        //akkor befejezzük a folyamatot.
        if(inventory.validateAction(MOVE)!=MOVE){
            Skeleton.printReturn("void");
            return false;
        }
        List<Field> nextField = field.getEmptyNeighbours();
        if(nextField.isEmpty()) {
            Skeleton.printReturn("void");
            return false;
        }
        if (!nextField.contains(f)) {
            return false;
        }
        field.remove(this);
        f.accept(this);
        Skeleton.printReturn("void");
        return true;
    }

    /**
     * Letapogatja a az aktuális pozícióján lévő mezőt és visszaadja a tartalmát.
     */
    public ItemPackage touch() {
        if (inventory.validateAction(STD_ACTN) == STD_ACTN) {
            ItemPackage ip = field.touched();
            Material m = ip.getMaterial();
            //Ha medvevírusos a virológus akkor elpusztítja a raktár anyagát
            if (inventory.validateAction(NEW_FIELD) == BEAR) {
                m.decreaseQuantity(m.getQuantity());
            }
            return ip;
        }
        return null;
    }
}
