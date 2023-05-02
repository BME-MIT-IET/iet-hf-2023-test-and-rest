package components.utils;

import controls.Skeleton;

import java.util.HashSet;

/**
 * Periodikus időzítőt reprezentál a játékban, a léptethető (Steppable) objektumokat
 * lépteti.
 */
public class Timer {
    private HashSet<Steppable> steppables;

    /**
     * Minden léptethető objektumot léptet
     */
    public void tick() {
        Skeleton.printCall("Timer.tick()");
        for (Steppable s: steppables) {
            s.step();
        }
        Skeleton.printReturn("void");
    }

    /**
     * A léptethető objektumok listájához hozzáadja az új
     * léptethető elemet
     * @param s hozzáadandó Steppable objektum
     */
    public void addSteppable(Steppable s) {
        Skeleton.printCall("Timer.addSteppable()");
        steppables.add(s);
        Skeleton.printReturn("void");
    }

    /**
     * Eltávolítja a megadott elemet a
     * léptethető objektumok listájából
     * @param s eltávolítandó Steppable objektum
     */
    public void removeSteppable(Steppable s) {
        Skeleton.printCall("Timer.removeSteppable()");
        steppables.remove(s);
        Skeleton.printReturn("void");
    }
}
