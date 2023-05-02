package components.agent;

import components.scientist.ActnLabel;
import controls.Skeleton;

/**
 * Az immunitást megvalósító osztály.
 * Létrehozható, aktiválható, majd ezt követően kezeli az immunitáshoz tartozó eseményeket,
 * megvédi a játékost a mások által rákent vírusoktól.
 */
public class Immunity extends Agent {

    /**
     * Konstruktor, ami az őskonstruktort hívja meg.
     * @param d erre kell beállítani a duration
     */
    public Immunity(int d){
        super(d);
    }

    /**
     * Visszaadja az ágens nevét
     * @return az ágens neve
     */
    @Override
    public String getName() {
        return "Immunity";
    }

    /**
     * Kezeli az immunitás ágenshez tartozó eseményeket,
     * ha a paraméterben USD_ON címkéjű akció érkezik,
     * visszaadja, hogy NO_AC, így jelezve a védettséget.
     */
    public ActnLabel actionMgmt(ActnLabel id) {
        Skeleton.printCall("Immunity.actionMgmt()");
        if (id == ActnLabel.USED_ON || id == ActnLabel.REFLECT) {
            Skeleton.printReturn("NO_ACTN");
            return ActnLabel.NO_ACTN;
        } else {
            Skeleton.printReturn(id.toString());
            return id;
        }
    }

    public String toString() {
        return "Immunity(" + getDuration() + ")";
    }
}
