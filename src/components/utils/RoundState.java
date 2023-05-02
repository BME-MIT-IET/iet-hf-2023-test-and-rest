package components.utils;

/**
 * Az aktuális körben végrehajtott lépéseket számon tartó osztály.
 */
public class RoundState {
    /**
     * Az allapotokat regisztralo boolean valtozok.
     */
    boolean move, touch, rob, craft, use;

    /**
     * Konstruktor. Beallitja a valtozok kezdeti erteket hamisra -> a korben nem tortent semmi.
     */
    public RoundState() {
        reset();
    }

    /**
     * Az allapotvaltozokat alaphelyzetbe (hamisra) allito metodus.
     */
    public void reset() {
        move = touch = rob = craft = use = false;
    }

    /**
     * A move allapot erteket igazra allitja. Visszaadja, hogy korabban mi volt az erteke.
     * @return a beallitas elotti ertek
     */
    public boolean setMove() {
        boolean temp = move;
        move = true;
        return temp;
    }

    /**
     * A touch allapot erteket igazra allitja. Visszaadja, hogy korabban mi volt az erteke.
     * @return a beallitas elotti ertek
     */
    public boolean setTouch() {
        boolean temp = touch;
        touch = true;
        return temp;
    }

    /**
     * A rob allapot erteket igazra allitja. Visszaadja, hogy korabban mi volt az erteke.
     * @return a beallitas elotti ertek
     */
    public boolean setRob() {
        boolean temp = rob;
        rob = true;
        return temp;
    }

    /**
     * A craft allapot erteket igazra allitja. Visszaadja, hogy korabban mi volt az erteke.
     * @return a beallitas elotti ertek
     */
    public boolean setCraft() {
        boolean temp = craft;
        craft = true;
        return temp;
    }

    /**
     * A use allapot erteket igazra allitja. Visszaadja, hogy korabban mi volt az erteke.
     * @return a beallitas elotti ertek
     */
    public boolean setUse() {
        boolean temp = use;
        use = true;
        return temp;
    }

    public boolean getMove() {
        return move;
    }

    public boolean getRob() {
        return rob;
    }

    public boolean getCraft() {
        return craft;
    }

    public boolean getTouch() {
        return touch;
    }

}
