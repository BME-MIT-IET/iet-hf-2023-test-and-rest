package components.field;

import controls.Skeleton;

/**
 * Az óvóhelyeket reprezentáló osztály, a játék kezdetén ide kerülnek a védőfelszerelések.
 */
public class Shelter extends Field {
    /**
     * Visszaadja azt az ItemPackage objektumot, mely olyan objektumokat tartalmaz, melyet a mező tárol.
     * @return A mezőn tárolt objektumokat tartalmazó ItemPackage objektum
     */
    public ItemPackage touched() {
        Skeleton.printCall("Shelter.touched()");
        ItemPackage ip = new ItemPackage();
        ip.setGears(gears);
        Skeleton.printReturn("ItemPackage");
        return ip;
    }
}
