package components.field;

import components.agent.Bear;
import components.gear.Gear;
import components.scientist.ActnLabel;
import components.scientist.Scientist;
import components.utils.Game;
import controls.Skeleton;


import java.util.ArrayList;
import java.util.List;

/**
 * Mező ősosztály, mely az üres mezőt reprezentálja, melyen a játék kezdetekor nem lehet objektum, a Scientist-en kívül.
 */
public class Field {
    /**
     * A mezőn lévő Scientist-ek, akik maximum ketten lehetnek
     */
    protected List<Scientist> scientists;
    /**
     * A mező tetszőleges (legalább 1) számú szomszédjai
     */
    private List<String> neighbours;
    /**
     * A mezőn lévő védőfelszerelések, melyek rablás során kieshettek
     */
    protected List<Gear> gears;

    /**
     * A Field a konstruktorban inicializálja az általa tárolt listákat
     */
    public Field() {
        scientists = new ArrayList<>();
        neighbours = new ArrayList<>();
        gears = new ArrayList<>();
    }

    /**
     * Beállítja a megadott mezőt szomszédként
     * @param id A szomszédként beállítandó mező
     */
    public void setNeighbour(String id) {
        Skeleton.printCall("Field.setNeighbour()");
        neighbours.add(id);
        Skeleton.printReturn("void");
    }

    /**
     * A mező összes szomszédját visszaadó függvény
     * @return A mező szomszédjainak listája
     */
    public List<String> getNeighbours() {
        Skeleton.printCall("Field.getNeighbours()");
        Skeleton.printReturn(neighbours.size()+" db");
        return neighbours;
    }

    /**
     * A mezőn álló virológusokat adja vissza
     * @return A mezőn álló virológusok listája
     */
    public List<Scientist> getScientists() {
        Skeleton.printCall("Field.getScientists()");
        Skeleton.printReturn(scientists.size()+" db");
        return scientists;
    }

    /**
     * Megadja az adott mező nem foglalt szomszédjait
     * @return A mező azon szomszédjai, melyeken legfeljebb egy virológus áll
     */
    public List<Field> getEmptyNeighbours() {
        Skeleton.printCall("Field.getEmptyNeighbours()");
        List<Field> emptyNeighbours = new ArrayList<>();
        List<Field> nbs = new ArrayList<>();
        for(String id : neighbours){
            nbs.add(Game.findFieldOrNewField(id));
        }
        //megkeressük azokat a szomszédokat, ahol van hely, és belerakjuk egy új listába, amit végül visszaadunk
        for (Field f:nbs){
            if(f.getScientists().size() < 2) {
                emptyNeighbours.add(f);
            }
        }
        Skeleton.printReturn(emptyNeighbours.size()+" db");
        return emptyNeighbours;
    }

    /**
     * Rárakja a Scientist-et a mezőre, ekkorra már ellenőrizve van, hogy rá is fér
     * @param s A mezőre lépő virológus
     */
    public void accept(Scientist s) {
        Skeleton.printCall("Field.acceptScientists()");
        //Ha s medvevírusos, akkor mindenkit megfertőzünk a fielden medvevírussal
        if(s.validateAction(ActnLabel.NEW_FIELD) == ActnLabel.BEAR){
            for(Scientist sci:scientists)
                sci.usedOn(new Bear(-1));
        }else {
            //Ha már van medvevírusos a Fielden, akkor rárakjuk az újonnan érkezőre is a medvevírust.
            for (Scientist sci : scientists) {
                if (sci.validateAction(ActnLabel.NEW_FIELD) == ActnLabel.BEAR)
                    s.usedOn(new Bear(-1));
            }
        }
        s.setField(this);
        scientists.add(s);
        s.setField(this);
        Skeleton.printReturn("void");
    }

    /**
     * Rárakja a rablás során kieső védőfelszerelést a mezőre
     * @param g A mezőre kerülő védőfelszerelés
     */
    public void add(Gear g) {
        Skeleton.printCall("Field.add(Gear)");
        gears.add(g);
        Skeleton.printReturn("void");
    }

    /**
     * Eltávolítja az ellépő Scientist-et a mezőről
     * @param s Az ellépő Scientist
     */
    public void remove(Scientist s) {
        Skeleton.printCall("Field.removeScientists()");
        scientists.remove(s);
        Skeleton.printReturn("void");
    }

    /**
     * Eltávolítja a valaki által felvett védőfelszerelést a mezőről
     * @param g A felvett védőfelszerelés
     */
    public void remove(Gear g) {
        Skeleton.printCall("Field.remove(Gear)");
        gears.remove(g);
        Skeleton.printReturn("void");
    }

    /**
     * Visszaadja azt az ItemPackage objektumot, mely olyan objektumokat tartalmaz, melyet a mező tárol.
     * @return A mezőn tárolt objektumokat tartalmazó ItemPackage objektum
     */
    public ItemPackage touched() {
        Skeleton.printCall("Field.touched()");
        ItemPackage ip = new ItemPackage();
        ip.setGears(gears);
        Skeleton.printReturn("ItemPackage");
        return ip;
    }

}
