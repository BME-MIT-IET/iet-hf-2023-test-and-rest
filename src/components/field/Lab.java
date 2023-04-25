package components.field;

import components.agent.Bear;
import components.agent.GeneticCode;
import components.scientist.ActnLabel;
import components.scientist.Scientist;
import controls.Skeleton;

/**
 * A laboratóriumot reprezentáló osztály, itt találhatók a genetikai kódok, melyekből ágens készíthető.
 */
public class Lab extends Field {
    /**
     * A játék kezdetén ide kerülő genetikai kód, amelyből ágens készíthető. Végig itt tárolódik
     */
    private GeneticCode gc;

    /**
     * Fertőz-e a field
     */
    private boolean hasBear;

    public Lab(boolean hasBear){
        this.hasBear=hasBear;
    }
    /**
     * Visszaadja azt az ItemPackage objektumot, mely olyan objektumokat tartalmaz, melyet a mező tárol.
     * @return A mezőn tárolt objektumokat tartalmazó ItemPackage objektum
     */
    public ItemPackage touched() {
        Skeleton.printCall("Lab.touched()");
        ItemPackage ip = new ItemPackage();
        ip.setGears(gears);
        ip.setCode(gc);
        Skeleton.printReturn("ItemPackage");
        return ip;
    }

    /**
     * Rárakja az általa tárolt genetikai kódot a Lab mezőre
     * @param genc A mezőre kerülő genetikai kód
     */
    public void add(GeneticCode genc) {
        Skeleton.printCall("Lab.add(GeneticCode)");
        gc = genc;
        Skeleton.printReturn("void");
    }

    /**
     * Getter a labor vírusosságához
     * @return hasbear
     */
    public boolean getHasBear(){
        return hasBear;
    }

    /**
     * Setter ahhoz, hogy beállítsuk medvevírussal fertőz-e a labor.
     * @param hb legyen-e medvevírusos a labor
     */
    public void setHasBear(boolean hb){
        hasBear=hb;
    }

    /**
     * Labornál felüldefiniált accept, hiszen ha medvevírusos a labor,
     * akkor automatikusan meg kell fertőzni a virológust.
     * @param s A mezőre lépő virológus
     */
    @Override
    public void accept(Scientist s) {
        Skeleton.printCall("Field.acceptScientists()");
        if(s.validateAction(ActnLabel.NEW_FIELD) == ActnLabel.BEAR){
            for(Scientist sci:scientists)
                sci.usedOn(new Bear(-1));
        }else if (hasBear) {
            s.usedOn(new Bear(-1));
        }else {
            for (Scientist sci : scientists) {
                if (sci.validateAction(ActnLabel.NEW_FIELD) == ActnLabel.BEAR)
                    s.usedOn(new Bear(-1));
            }
        }
        scientists.add(s);
        s.setField(this);
        Skeleton.printReturn("void");
    }
}
