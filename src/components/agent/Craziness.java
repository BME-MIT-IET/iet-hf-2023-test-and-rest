package components.agent;

import components.scientist.ActnLabel;
import controls.Skeleton;

/**
 * A vitustáncot megvalósító osztály.
 * Létrehozható, aktiválható, majd ezt követően kezeli a vitustánchoz tartozó eseményeket,
 * amely a játékos kontrollálhatatlan mozgását eredményezi.
 */
public class Craziness extends Agent {

    /**
     * Konstruktor, ami az őskonstruktort hívja meg.
     * @param d erre kell beállítani a duration
     */
    public Craziness(int d){
        super(d);
    }

    /**
     * Visszaadja az ágens nevét
     * @return az ágens neve
     */
    @Override
    public String getName() {
        return "Craziness";
    }

    /**
     * Kezeli a vitustánc ágenshez tartozó eseményeket,
     * ha a paraméterben MOVE címkéjű akció érkezik, visszaadja,
     * hogy NO_AC, hiszen ez esetben kontrollálhatatlan lesz a mozgás,
     * nem szokványosan lép.
     * @param id Lekezelendő ActnLabel
     * @return ActnLabel, az előzőek szerint visszaadott id.
     */
    public ActnLabel actionMgmt(ActnLabel id) {
        Skeleton.printCall("Craziness.actionMgmt()");
        if (id == ActnLabel.NEW_TURN) {
            Skeleton.printReturn("CRAZY");
            return ActnLabel.CRAZY;
        } else {
            Skeleton.printReturn(id.toString());
            return id;
        }
    }

    public String toString() {
        return "Craziness(" + getDuration() + ")";
    }
}
