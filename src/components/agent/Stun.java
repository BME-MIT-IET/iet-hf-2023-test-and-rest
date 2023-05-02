package components.agent;

import components.scientist.ActnLabel;
import controls.Skeleton;

/**
 * A bénító vírust megvalósító osztály.
 * Létrehozható, aktiválható, majd ezt követően kezeli a bénuláshoz tartozó eseményeket,
 * melynek következtében a játékos cselekvésképtelenné válik.
 */
public class Stun extends Agent {

    /**
     * Konstruktor, ami az őskonstruktort hívja meg.
     * @param d erre kell beállítani a duration
     */
    public Stun(int d){
        super(d);
    }

    /**
     * Visszaadja az ágens nevét
     * @return az ágens neve
     */
    @Override
    public String getName() {
        return "Stun";
    }

    /**
     * Kezeli a vitustánc ágenshez tartozó eseményeket,
     * ha a paraméterben bármilyen címkéjű akció érkezik, akkor visszaadja,
     * hogy NO_AC, hiszen a Scientist le van bénulva.
     * @param id Lekezelendő ActnLabel
     * @return ActnLabel, az előzőek szerint visszaadott id.
     */
    public ActnLabel actionMgmt(ActnLabel id) {
        Skeleton.printCall("Stun.actionMgmt()");
        if (id == ActnLabel.STD_ACTN ||
                id == ActnLabel.CRAFT ||
                id == ActnLabel.LEARN ||
                id == ActnLabel.MOVE ||
                id == ActnLabel.NEW_TURN ||
                id == ActnLabel.CRAZY) {
            Skeleton.printReturn("NO_ACTN");
            return ActnLabel.NO_ACTN;
        } else if (id == ActnLabel.ROBBED){
            return ActnLabel.VALID_ROBBERY;
        } else {
            Skeleton.printReturn(id.toString());
            return id;
        }
    }

    public String toString() {
        return "Stun(" + getDuration() + ")";
    }
}
