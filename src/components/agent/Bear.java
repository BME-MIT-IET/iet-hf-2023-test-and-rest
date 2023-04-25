package components.agent;

import components.scientist.ActnLabel;

/**
 * Medvevírust megvalósító osztály. Létrehozható, aktiválható, majd ezt követően kezeli a medvevírushoz tartozó eseményeket,
 * vagyis például azt, hogy az ezzel rendelkező virológus másokat megfertőz.
 */
public class Bear extends Agent {

    public Bear(int d){super(d);}

    /**
     * Visszaadja az ágens nevét
     * @return az ágens neve
     */
    @Override
    public String getName() {
        return "Bear";
    }

    /**
     *
     * @param label Lekezelendő ActnLabel
     * @return Ha új mezőre lépés van, akkor fertőzést(BEAR) adunk vissza,
     * amúgy pedig a kapot labelt.
     */
    @Override
    public ActnLabel actionMgmt(ActnLabel label) {
        if(label == ActnLabel.NEW_FIELD || label == ActnLabel.NEW_TURN)
            return ActnLabel.BEAR;
        return label;
    }

    /**
     * Visszadja stringbe az ágens nevét és időtartamát
     * @return stringbe pakolt ágens név és időtartam
     */
    public String toString() {
        return "Bear(" + getDuration() + ")";
    }
}
