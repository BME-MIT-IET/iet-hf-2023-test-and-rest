package components.field;

import components.agent.Material;
import components.agent.GeneticCode;
import components.gear.Gear;
import controls.Skeleton;

import java.util.ArrayList;
import java.util.List;


/**
 * A Scientist által egy mezőről felvehető objektumokat tárolja.
 */
public class ItemPackage {
    /**
     * A felvehető genetikai kód - speciálisan Lab mezőről
     */
    private GeneticCode code;
    /**
     * A felvehető anyagok - speciálisan Storage mezőről.
     */
    private Material material;
    /**
     * A felvehető védőfelszerelések
     */
    private List<Gear> gears;

    /**
     * Visszaad egy (felvehető) Material objektumot az ItemPackage-ből
     * @return felvehető material
     */
    public Material getMaterial(){
        Skeleton.printCall("ItemPackage.getMaterials()");
        Skeleton.printReturn(material==null?"null":material.getName());
        return material;
    }

    /**
     * Visszaadja a (felvehető) Gear objektumokat az ItemPackage-ből
     * @return felvehető gearek
     */
    public List<Gear> getGears() {
        return gears;
    }

    /**
     * Visszaad egy (felvehető) GeneticCode objektumot az ItemPackage-ből
     * @return megtanulható genetikai kód
     */
    public GeneticCode getCode() {
        Skeleton.printCall("ItemPackage.getCode()");
        Skeleton.printReturn(code==null?"null":"code");
        return code;
    }

    /**
     * Beállítja az ItemPackage által tartalmazott GeneticCode objektumot
     * @param code Ezt a kódot fogja tartalmazni az ItemPackage
     */
    public void setCode(GeneticCode code) {
        Skeleton.printCall("ItemPackage.setCode()");
        this.code = code;
        Skeleton.printReturn("void");
    }

    /**
     * Beállítja az ItemPackage által tartalmazott Gear objektumokat
     * @param gears Ezeket a Geareket fogja tartalmazni az ItemPackage
     */
    public void setGears(List<Gear> gears) {
        Skeleton.printCall("ItemPackage.setGears()");
        this.gears = gears;
        Skeleton.printReturn("void");
    }

    /**
     * Beállítja az ItemPackage által tartalmazott Material objektumot
     * @param material Ezt az anyagot fogja tartalmazni az ItemPackage
     */
    public void setMaterial(Material material) {
        Skeleton.printCall("ItemPackage.setMaterials()");
        this.material = material;
        Skeleton.printReturn("void");
    }

}
