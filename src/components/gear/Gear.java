package components.gear;

import components.scientist.ActnLabel;

/**
 * A játékban különböző felszerelések találhatóak, amelyeket a Scientist-ek viselhetnek.
 * Különböző hatásokat fejtenek ki viselőjükre. Gyűjthetőek, illetve más játékosoktól
 * elrabolhatóak. Az osztály ezeket a cselekvéseket leíró absztrakt ősosztály.
 */
public abstract class Gear {
    /**
     * A gear automatikus eltávolítását meghatározó változó, amely ha igaz,
     * akkor törlődik az inventoryból
     */
    protected boolean delete = false;
    /**
     * Kezeli a védőfelszereléshez tartozó eseményeket, a
     * paraméterben megadott címkéjű akcióról visszaadja, hogy végrehajtható-e, illetve
     * milyen formában.
     * @param label a megadott akció címkéje
     * @return a felszerelés által befolyásolt cselekmény címkéje
     */
    public abstract ActnLabel actionMgmt(ActnLabel label);

    /**
     * Visszaadja a delete attribútum aktuális értékét
     * @return a delete attribútum aktuális értéke (boolean)
     */
    public boolean getDelete() {
        return delete;
    }

    /**
     * Beállítja a Gear delete attribútumát a paraméterként kapott boolean értékre
     * @param _delete a delete attribútum új boolean értéke
     */
    public void setDelete(boolean _delete) {
        delete = _delete;
    }

    public abstract String toString();
}
