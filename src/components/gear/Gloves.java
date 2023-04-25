package components.gear;

import components.scientist.ActnLabel;
import controls.Skeleton;

/**
 * A  játékosok egy különleges kesztyűt gyűjthetnek és használhatnak, amely a viselőjére
 * irányuló kenést tudja visszadobni. Az osztály leírja a felszerelés működését.
 */
public class Gloves extends Gear {
    /**
     * A kesztyű élettartama, alapértelmezetten 3 használat után lebomlik
     */
    private int duration = 3;

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * Kezeli kesztyű védőfelszereléshez tartozó
     * eseményeket, ha a paraméterben USD_ON címkéjű akció érkezik, visszaadja,
     * hogy RFT, reprezentálva, hogy bekerül a craftolt ágensei közé
     * @param id a kesztyűhöz beérkező akció címkéje
     * @return a kesztyű által befolyásolt cselekvés címkéje
     */
    public ActnLabel actionMgmt(ActnLabel id) {
        Skeleton.printCall("Gloves.actionMgmt()");
        if(duration != 0 && id == ActnLabel.USED_ON) {
            Skeleton.printReturn(("REFLECT"));
            duration--;
            return ActnLabel.REFLECT;
        } else {
            if(duration == 0) {
                delete = true;
            }
            Skeleton.printReturn((id.toString()));
            return id;
        }
    }

    public String toString() {
        return "Gloves(" + duration + ")";
    }
}
