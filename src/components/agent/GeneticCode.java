package components.agent;

import components.scientist.Inventory;
import controls.Skeleton;

import java.util.HashMap;
import java.util.Map;

/**
 * A Scientist által megtanulható genetikai kódokat reprezentáló osztály, melyből
 * bizonyos anyagkészletért cserébe ágens készíthető.
 */
public class GeneticCode {
    /**
     * A craftolt ágens hatásának időtartama
     */
    private static final int duration = 3;
    /**
     * A genetikai kód által megtanult ágens
     */
    private Agent agent;
    /**
     * A genetikai kód által megtanult ágens craftolásához szükséges anyagok listája
     */
    private Map<String,Material> craftMaterials;

    // temporary
    public boolean equals(GeneticCode gc) {
        return this.agent.equals(gc.agent);
    }

    /**
     * Egy paraméteres konstruktor a genetikai kódhoz
     * @param agent a genetikai kód által eltárolt ágens
     */
    public GeneticCode(Agent agent) {
        this.agent = agent;
    }

    /**
     * Három paraméteres konstruktor a genetikai kódhoz
     * @param agent a genetikai kód által eltárolt ágens
     * @param nucleotideQuantity a genetikai kód által tárolt ágens előállításához szükséges nukleotid mennyisége
     * @param aminoAcidQuantity a genetikai kód által tárolt ágens előállításához szükséges aminosav mennyisége
     */
    public GeneticCode(Agent agent, int nucleotideQuantity, int aminoAcidQuantity) {
        this.agent = agent;
        this.craftMaterials = new HashMap<>();
        craftMaterials.put("nucleotide", new Material("nucleotide", nucleotideQuantity));
        craftMaterials.put("aminoacid", new Material("aminoacid", aminoAcidQuantity));
    }

    public GeneticCode(Agent agent, Material m1, Material m2) {
        this.agent = agent;
        this.craftMaterials = new HashMap<>();
        craftMaterials.put(m1.getName(), m1);
        craftMaterials.put(m2.getName(), m2);
    }

    /**
     * A függvény megpróbálja lecsökkenteni a Scientistnél lévő materialok mennyiségét a craftoláshoz szükséges materialok mennyiségével, amennyiben sikeres az összes művelet nullal tér vissza, egyébként a módosítások listájával
     * @param inventory a Scientist inventory objektuma, amelyben a materialok találhatóak
     * @return null, ha a csökkentések sikeresek, false, amennyiben a csökkentések sikertelenek
     */
    private Map<String, Integer> checkCraft(Inventory inventory) {
        //Új Map inicializálása, amelyben eltárolom, hogy melyik anyagot mennyivel csökkentettem sikeresen
        Map<String, Integer> returnModifies = new HashMap<>();
        //Nested foreach ciklus, amelyek végigmennek a Scientistnél lévő Materialok és a craftoláshoz szükséges Materialok listáján
        for (Map.Entry<String, Material> scientistsMats : inventory.getMaterials().entrySet()) {
            for(Map.Entry<String, Material> craftMats : craftMaterials.entrySet()) {
                //Összehasonlítom a Materialok nevét, hogy csak a megfelelő helyen történjen csökkentés
                if(scientistsMats.getKey().equals(craftMats.getKey())) {
                    //Csökkentem a Scientistnél lévő material mennyiségét a craftoláshoz szükséges Material mennyiségével
                    if(scientistsMats.getValue().decreaseQuantity(craftMats.getValue().getQuantity())) {
                        //A módosítást eltárolom egy listában, hogy amennyiben egy későbbi esetben nem igazzal tér vissza a csökkentés, akkor a módosítások vissza fordíthatóak legyenek
                        returnModifies.put(scientistsMats.getKey(), craftMats.getValue().getQuantity());
                    } else {
                        //Visszatérek a módosítások listájával, mivel nincs elég materialja a játékosnak
                        return returnModifies;
                    }
                }
            }
        }
        //Nullal térek vissza, mivel minden módosítás sikeres volt
        return null;
    }

    /**
     * A genetikai kódbol egy ágens craftolható, amelyet ez a függvény valósít meg
     * @param inventory a Scientist inventoryja, amelyben a megfelelő anaygok találhatóak
     * @return true, amennyiben sikeres volt a craftolás, false amennyiben nem
     */
    public boolean craft(Inventory inventory) {
        Skeleton.printCall("GeneticCode.craft()");
        //Visszatérési értéket tároló változó létrehozása
        boolean returnValue;
        //A módosításokról kapott lista eltárolása. Null, hogy ha sikeres volt a művelet, nem null amennyiben nem
        Map<String, Integer> modifies = checkCraft(inventory);
        //Ellenőrzöm, hogy a modifies lista null-e, tehát sikeres volt-e a craftolás
        if(modifies == null) {
            //Hozzáadjuk az inventoryhoz az újonnan lecraftolt ágenst
            switch (agent.getName()) {
                case "Bear": inventory.add(new Bear(duration)); break;
                case "Craziness": inventory.add(new Craziness(duration)); break;
                case "Dementia": inventory.add(new Dementia(duration)); break;
                case "Immunity": inventory.add(new Immunity(duration)); break;
                case "Stun": inventory.add(new Stun(duration)); break;
                default: break;
            }
            //A visszatérést igazra állítjuk
            returnValue = true;
        } else {
            //Nested foreach ciklus, amelyben végig megyünk a Scientist materiáljain és a módosítások listáján
            for (Map.Entry<String, Material> scientistsMats : inventory.getMaterials().entrySet()) {
                for(Map.Entry<String, Integer> modifiyList : modifies.entrySet()) {
                    //Ellenőrizzük, hogy a Scientist materialjának neve és a modifies lista elemének neve megegyezik-e
                    if(scientistsMats.getKey().equals(modifiyList.getKey())) {
                        //Megnövelem a Scientist materiáljának értékét a korábban módosított értékkel
                        scientistsMats.getValue().increaseQuantity(modifiyList.getValue());
                    }
                }
            }
            //A visszatérési értéket hamisra állítjuk
            returnValue = false;
        }
        Skeleton.printReturn(returnValue ? "true" : "false");
        return returnValue;
    }


    public String toString() {
        return agent.toString();
    }
    public String getDetails() {
        return agent.toString() + " nucleotide: " + craftMaterials.get("nucleotide").getQuantity() + " aminoacid: " + craftMaterials.get("aminoacid").getQuantity();
    }
    /**
     * Visszaadja a genetikai kód által craftolható ágens nevét
     * @return az ágens neve
     */
    public String getCraftableAgentName() {
        return agent.getName();
    }
}
