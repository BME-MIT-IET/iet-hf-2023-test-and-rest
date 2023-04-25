package components.agent;

import components.scientist.ActnLabel;
import controls.Skeleton;

/**
 * Az elfelejtő vírust megvalósító osztály.
 * Létrehozható, aktiválható, melynek hatására a játékos a hatás lejártáig elfelejti
 * a már korábban megtanult genetikai kódjait.
 */
public class Dementia extends Agent {

    /**
     * Konstruktor, ami az őskonstruktort hívja meg.
     * @param d erre kell beállítani a duration
     */
    public Dementia(int d){
        super(d);
    }

    /**
     * Visszaadja az ágens nevét
     * @return az ágens neve
     */
    @Override
    public String getName() {
        return "Dementia";
    }

    /**
     * Kezeli az elfelejtő ágenshez tartozó eseményeket, ha a paraméterben CRAFT vagy LEARN címkéjű akció érkezik,
     * visszaadja, hogy NO_AC, így jelezve a kód elfelejtését,
     * illetve a képtelenséget a tanulásra, hiszen rögtön elfelejtené, ha újat tanulna.
     * @param id Lekezelendő ActnLabel
     * @return ActnLabel, az előzőek szerint visszaadott id.
     */
    public ActnLabel actionMgmt(ActnLabel id) {
        Skeleton.printCall("Dementia.actionMgmt()");
        if (id == ActnLabel.CRAFT || id == ActnLabel.LEARN) {
            Skeleton.printReturn("NO_ACTN");
            return ActnLabel.NO_ACTN;
        } else {
            Skeleton.printReturn(id.toString());
            return id;
        }
    }

    public String toString() {
        return "Dementia(" + getDuration() + ")";
    }
}
