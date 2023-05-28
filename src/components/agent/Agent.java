package components.agent;

import components.scientist.ActnLabel;
import components.utils.Steppable;
import controls.Skeleton;

 /**
  * A játékban található különböző ágensek alapjául szolgáló ősosztály.
  * Tartalmazza a közös attribútumokat és metódusokat.
  */

public abstract class Agent implements Steppable {
    private int duration;

     /**
      * Konstruktor, ami beállítja a durationt.
      * @param d erre kell beállítani a duration
      */
    public Agent(int d){
        duration=d;
    }

    /**
     * Beállított duration lekérdezése
     */
    public int getDuration() {
        Skeleton.printCall("Agent.getDuration()");
        Skeleton.printReturn("duration");
        return duration;
    }

    /**
     * Beállított duration felülírása vagy beállítása
     */
    public void setDuration(int value) {
        Skeleton.printCall("Agent.setDuration()");
        duration = value;
        Skeleton.printReturn("void");
    }

     /**
      * Visszaadja az ágens nevét
      * @return az ágens neve
      */
    public abstract String getName();

    /**
     * Kezeli az ágenshez tartozó eseményeket,
     * a paraméterben megadott címkéjű akcióról visszaadja,
     * hogy végrehajtható-e, illetve milyen formában.
     * @param label Lekezelendő ActnLabel
     * @return ActnLabel, az előzőek szerint visszaadott id.
     */
    public abstract ActnLabel actionMgmt(ActnLabel label);

    /**
     * Tick hívja, csökkenti a durationt, amíg nem lesz 0
     */
    public void step() {
        Skeleton.printCall("Agent.step()");
        if(duration>0 ) {
            duration--;
        }
        Skeleton.printReturn("void");
    }

    public abstract String toString();
}
