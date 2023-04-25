package components.field;

import components.agent.Material;
import controls.Skeleton;

/**
 * A raktárakat reprezentáló osztály, a játék kezdetén ide kerülnek az anyagok, melyekből ágens készíthető.
 */
public class Storage extends Field {
    /**
     * A játék kezdetén ide kerülő anyag
     */
    private Material material;

    /**
     * Visszaadja azt az ItemPackage objektumot, mely olyan objektumokat tartalmaz, melyet a mező tárol.
     * @return A mezőn tárolt objektumokat tartalmazó ItemPackage objektum
     */
    public ItemPackage touched() {
        Skeleton.printCall("Storage.touched()");
        ItemPackage ip = new ItemPackage();
        ip.setGears(gears);
        ip.setMaterial(material);
        Skeleton.printReturn("ItemPackage");
        return ip;
    }

    /**
     * Rárakja a paraméterben megadott Material objektumot a Storage mezőre
     * @param m A mezőre kerülő anyag
     */
    public void add(Material m) {
        Skeleton.printCall("Storage.add(Material)");
        material = m;
        Skeleton.printReturn("void");
    }
}
