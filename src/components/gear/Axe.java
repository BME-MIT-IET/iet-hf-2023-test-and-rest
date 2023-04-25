package components.gear;

import components.scientist.ActnLabel;
import controls.Skeleton;

/**
 * A játékosok gyűjthetnek és használhatnak egy balta eszközt,
 * amellyel megölhetnek más Scientist-et,
 * azonban ez egy használat után kicsorbul.
 * Az osztály leírja a felszerelés működését.
 */
public class Axe extends Gear {
    /**
     * A balta élettartama, alapértelmezetten 1 használat lehetséges, utána kicsorbul
     */
    private int duration = 1;

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * Kezeli a balta felszereléshez tartozó eseményeket, ha KILL érkezik a paraméterben,
     * AXE-t ad vissza, más esetben pedig a paraméterben érkező címkét.
     * @param id a baltához beérkező akció címkéje
     * @return a balta által befolyásolt cselekvés címkéje
     */
    public ActnLabel actionMgmt(ActnLabel id) {
        Skeleton.printCall("Axe.actionMgmt()");
        if(duration != 0 && id != ActnLabel.KILL) {
            Skeleton.printReturn(id.toString());
            return id;
        } else {
            Skeleton.printReturn("AXE");
            if(duration>0)
                duration--;
            return ActnLabel.AXE;
        }
    }

    public String toString() {
        return "Axe(" + duration + ")";
    }
}
