package components.gear;

import components.scientist.ActnLabel;
import components.utils.Game;
import controls.Skeleton;

import java.util.Random;

/**
 * A játékosok egy különleges köpenyt gyűjthetnek és használhatnak, amely a viselőjére
 * irányuló kenést a meghatározott százalékkal elhárítja. Az osztály leírja a felszerelés
 * működését.
 */
public class Coat extends Gear {

    /**
     * A köpeny védelmét meghatározó érték
     */
    private int effectiveness;

    /**
     * Egy paraéteres konstruktor a Coat-hoz, amely beállítja az effectiveness értékét
     * @param effectiveness a köpeny védelmét meghatározó érték
     */
    public Coat(int effectiveness) {
        this.effectiveness = effectiveness;
    }

    /**
     * Kezeli a köpeny védőfelszereléshez tartozó
     * eseményeket, ha a paraméterben USD_ON címkéjű akció érkezik, valamekkora
     * (pontosan effectiveness-nyi) eséllyel visszaadja, hogy NO_A, reprezentálva a
     * köpeny védelmezését
     * @param id a köpenyhez beérkező akció címkéje
     * @return a köpeny által befolyásolt cselekvés címkéje
     */
    public ActnLabel actionMgmt(ActnLabel id) {
        if (id == ActnLabel.USED_ON) {
            Random random = Game.random;
            if (random.nextInt(100) < effectiveness) {
                Skeleton.printReturn("NO_ACTN");
                return ActnLabel.NO_ACTN;
            }
        }
        return id;
    }

    public String toString() {
        return "Coat";
    }
}
