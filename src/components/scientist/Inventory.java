package components.scientist;

import components.agent.Agent;
import components.gear.Gear;
import static components.scientist.ActnLabel.*;
import static java.lang.Math.max;
import static java.lang.Math.min;

import components.agent.GeneticCode;
import components.agent.Material;
import controls.Skeleton;

import java.security.SecureRandom;
import java.util.*;

/**
 * Egyetlen Scientisthez tartozó objektum, mely tárolja a Scientist által felvett
 * objektumokat, illetve kezeli a rákent ágenseket és hatásukat.
 */
public class Inventory {
    /**
     * Megadja, hogy egy adott típusú materialból legfeljebb
     * mennyi lehet
     */
    private static final int maxMaterial = 55;
    /**
     * A Scientist anyagkészlete, melyeket ágensek
     * előállítására használ
     */
    private final Map<String, Material> materials;
    /**
     * A Scientist felszerelései, maximum három lehet nála
     */
    private final List<Gear> gears;
    /**
     * A Scientist tárolja, milyen ágensek hatása alatt van
     */
    private final List<Agent> activeAgents;
    /**
     * A Scientist tárolja, milyen ágenseket állított elő, vagyis
     * milyeneket tud éppen felkenni
     */
    private final List<Agent> crafted;
    /**
     * A Scientist tárolja, milyen genetikai kódokat
     * ismer
     */
    private final Set<GeneticCode> knownGeneticCodes;

    public Inventory() {
        materials = new HashMap<>();
        materials.put("nucleotide", new Material("nucleotide", 0));
        materials.put("aminoacid", new Material("aminoacid", 0));
        gears = new ArrayList<>();
        activeAgents = new ArrayList<>();
        crafted = new ArrayList<>();
        knownGeneticCodes = new HashSet<>();
    }

    public List<Agent> getCrafted(){
        return crafted;
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
        Skeleton.printCall("Inventory.validateAction()");

        //Megnézzük, hogy van-e olyan aktív ágens(activeAgents),
        //amely megállítaná a Scientistet, az adott akció végrehajtásában.
        //Ha van ilyen, akkor visszaadjuk az actionMgmt függvény válaszát.
        ActnLabel MgmtResponse=id;

        if(id == CRAFT_SKELETON) {
            Skeleton.printReturn("NO_ACTN");
            return ActnLabel.NO_ACTN;
        }
        for (Agent a:activeAgents) {
            MgmtResponse=a.actionMgmt(MgmtResponse);
        }
        //Megnézzük, hogy van-e olyan felszerelés(gears),
        //amely megállítaná a Scientistet, az adott akció végrehajtásában.
        //Ha van ilyen, akkor visszaadjuk az actionMgmt függvény válaszát.
        for (Gear g:gears) {
            MgmtResponse=g.actionMgmt(MgmtResponse);
        }
        //if(MgmtResponse==NEW_FIELD)
            //return BEAR;
        Skeleton.printReturn(MgmtResponse.toString());
        //Visszaadjuk az actionMgmteken végigfutott MgmtResponse változót.
        return MgmtResponse;
    }

    /**
     * Visszaadja az inventoryban lévő anyagokat
     * @return az inventoryban lévő anyagok
     */
    public Map<String,Material> getMaterials() {
        Skeleton.printCall("Inventory.getMaterials()");
        Skeleton.printReturn("materials");
        return materials;
    }

    /**
     * Hozzáad egy új felszerelést az inventoryhoz
     * @param g a felszerelés, amelyet hozzáadunk az inventoryhoz
     */
    public void add(Gear g) {
        Skeleton.printCall("Inventory.add(Gear)");

        // van elég hely?
        if (gears.size() < 3) {
            // van elég hely
            gears.add(g);
        }

        Skeleton.printReturn("void");
    }

    /**
     * Eltávolít egy felszerelést az inventoryból
     * @param g az eltávolítandó felszerelés
     */
    public void remove(Gear g) {
        Skeleton.printCall("Inventory.remove(Gear)");
        Skeleton.printReturn("void");
        gears.remove(g);
    }

    /**
     * Add GeneticCode to knownGeneticCodes
     * @param gc GeneticCode to learn
     */
    public boolean add(GeneticCode gc) {
        Skeleton.printCall("Inventory.add(GeneticCode)");

        //Eltárolom a HashSet hosszát, hogy később megvizsgálhassam, hogy nőtt-e ez az érték
        int gcOldSize = knownGeneticCodes.size();
        // genetic code hozzáadása a listához
        for (GeneticCode tgc : knownGeneticCodes) {
            if (tgc.equalsGeneticCode(gc)) {
                Skeleton.printReturn("false");
                return false;
            }
        }
        knownGeneticCodes.add(gc);
        //Amennyiben megegyezik a lista hossza az eredetivel, akkor hamis értéket ad vissza, tehát nem sikerült a hozzáadás
        //Amennyiben különbözik a két érték, akkor sikeres volt a hozzáadás, ezért igaz értékkel tér vissza
        Skeleton.printReturn("true");
        return true;
    }

    /**
     * Hozzáadja a paraméterül kapott anyagot a már meglévő anyagokhoz
     * @param m az anyag, amit hozzáadunk az inventoryhoz
     */
    public void add(Material m) {
        Skeleton.printCall("Inventory.add(Material)");

        // validate action
        ActnLabel loot = validateAction(LOOT_MAT);
        if (loot == LOOT_MAT) {
            // tud materialt gyűjteni
            int value = min(m.getQuantity(),maxMaterial-materials.get(m.getName()).getQuantity());
            boolean success = m.decreaseQuantity(value);
            if (success) {
                // saját materialhoz hozzáadás
                materials.get(m.getName()).increaseQuantity(value);
            }
        } else if(loot == LOOT_DBL) {
            int value = min(m.getQuantity(), maxMaterial*2);
            boolean success = m.decreaseQuantity(value);
            if (success) {
                // saját materialhoz hozzáadás
                materials.get(m.getName()).increaseQuantity(value);
            }
        }
        Skeleton.printReturn("void");
    }

    /**
     * Eltávolít egy anyagot az inventoryból
     * @param m az anyag, amelyet eltávolítunk az anyagok közül
     */
    public void remove(Material m) {
        Skeleton.printCall("Inventory.remove(Material)");
        Skeleton.printReturn("void");
        for(Map.Entry<String,Material> entry : materials.entrySet()) {
            if(entry.getKey().equals(m.getName())) {
                entry.getValue().decreaseQuantity(m.getQuantity());
                return;
            }
        }
    }

    /**
     * Hozzáadja a paraméterül kapott ágenst a már meglévő ágensekhez
     * @param a az ágens, amelyet hozzáadunk az inventoryhoz
     */
    public void add(Agent a) {
        Skeleton.printCall("Inventory.add(Agent)");
        crafted.add(a);
        Skeleton.printReturn("void");
    }

    /**
     * Eltávolítja a paraméterül kapott ágenst az inventoryból
     * @param a az inventoryból eltávolítandó ágens
     */
    public void remove(Agent a) {
        Skeleton.printCall("Inventory.remove(Agent)");
        Skeleton.printReturn("void");
        crafted.remove(a);
    }

    /**
     * Hozzáadunk egy új aktív ágenst, a már Scientisten aktív hatást kifejtő ágensekhez
     * @param a az új aktív ágens objektum
     */
    public void addActiveAgent(Agent a) {
        Skeleton.printCall("Inventory.addActiveAgent(Agent)");
        activeAgents.add(a);
        Skeleton.printReturn("void");
    }

    /**
     * Visszadja a scientist gearjeit
     * @return a scientist gears-ei.
     */
    public List<Gear> getGears() {
        Skeleton.printCall("Inventory.getGears()");
        Skeleton.printReturn("gears");
        return gears;
    }

    /**
     * Visszaad egy véletlenszerű Gear objektumot az és ki is törli onnan - a Scientist.rob metódus használja
     * inventory-ból
     * @return egy véletlenszerűen kiválasztott Gear objektum
     */
    public Gear getRandomGear() {
        if (!gears.isEmpty()) {
            Skeleton.printCall("Inventory.getRandomGear()");
            Skeleton.printReturn("Gear");
            SecureRandom random = new SecureRandom(); // Compliant for security-sensitive use cases
            int randIndex = (int)(random.nextInt()  * (gears.size()-1));
            return gears.get(randIndex);
        }
        return null;
    }

    /**
     * Visszaad egy véletlenszerű Material
     * objektumot az inventory-ból és csökkenti valamennyivel az értékét
     * @return egy véletlenszerűen kiválasztott Material objektum
     */
    public Material getRandomMaterial() {
        String[] types = {"nucleotide", "aminoacid"};
        SecureRandom random = new SecureRandom(); // Compliant for security-sensitive use cases
        int index = (int)Math.floor((random.nextFloat() * 2));
        Skeleton.printCall("Inventory.getRandomMaterial()");
        Skeleton.printReturn("Nucleotide");
        Material m = materials.get(types[index]);
        //visszaadunk mindent
        int quantity = m.getQuantity();
        m.decreaseQuantity(quantity);
        return new Material(m.getName(), quantity);
    }

    /**
     * Eltávolítja a lejárt hatásidejű ágenseket
     */
    public void removeInactiveAgents() {
        Skeleton.printCall("Inventory.removeInactiveAgents()");
        Skeleton.printReturn("void");
        activeAgents.removeIf(current -> current.getDuration() == 0);
    }

    /**
     * Amikor valamilyen tároló kapacitást növelő hatás lejár a Scientisten, akkor a maximális értéken túli anyagokat a függvény eltávolítja
     * @return a túlcsorduló anyagok listája
     */
    public Map<String, Material> removeExcessMaterial() {
        Skeleton.printCall("Inventory.removeExcessMaterial()");
        Skeleton.printReturn("null");
        Map<String, Material> removedMaterial = new HashMap<>();
        for(Map.Entry<String, Material> m : materials.entrySet()) {
            if(m.getValue().getQuantity() > maxMaterial) {
                removedMaterial.put(m.getKey(), new Material(m.getKey(),m.getValue().getQuantity()-maxMaterial));
                m.getValue().decreaseQuantity(m.getValue().getQuantity()-maxMaterial);
            }
        }

        return removedMaterial;
    }

    /**
     * Visszaadja a Scientist által már megtanult genetikai kódokat
     * @return Set<GeneticCode> objektum, amelyben a már megtanult genetikai kódok találhatóak
     */
    public Set<GeneticCode> getKnownGeneticCodes() {
        Skeleton.printCall("Inventory.getKnownGeneticCodes()");
        Skeleton.printReturn("knownGeneticCodes");
        return knownGeneticCodes;
    }

    /**
     * Eltávolítja az inventoryból az elhasználódott Gear-eket
     */
    public void removeWornOutGears() {

        gears.removeIf(Gear::getDelete);
    }

    public List<Agent> getActiveAgents() {
        return activeAgents;
    }

    /**
     * Kitörli az aktív hatásidejű ágenst az ezt tároló listából - halálkor eltávolítandó medvevírusnál használható
     * @param a eltávolítandó ágens
     */
    public void removeActiveAgent(Agent a) {
        activeAgents.removeIf(ai -> a.getDuration() == ai.getDuration());
    }
}
