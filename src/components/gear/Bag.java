package components.gear;

import components.scientist.ActnLabel;
import controls.Skeleton;

/**
 * A játékosok egy különleges zsákot gyűjthetnek és használhatnak, amely megduplázza
 * az azt birtokló Scientist tároló kapacitását. Az osztály leírja a felszerelés működését.
 */
public class Bag extends Gear {
    /**
     * Kezeli a zsák védőfelszereléshez tartozó
     * eseményeket, ha a paraméterben L_MAT címkéjű akció érkezik, visszaadja, hogy
     * L_DBL, reprezentálva a kapacitás duplázását
     * @param id a zsákhoz beérkező akció címkéje
     * @return a zsák által befolyásolt cselekvés címkéje
     */
    public ActnLabel actionMgmt(ActnLabel id) {
        Skeleton.printCall("Bag.actionMgmt()");
        if (id == ActnLabel.LOOT_MAT) {
            Skeleton.printReturn("LOOT_DBL");
            return ActnLabel.LOOT_DBL;
        } else {
            Skeleton.printReturn(id.toString());
            return id;
        }
    }

    public String toString() {
        return "Bag";
    }
}
