package components.agent;

import controls.Skeleton;
import java.util.Random;

/**
 * A játékban található különböző anyagok megvalósításáért felelős osztály.
 * Az anyagok darabszáma változtatható, továbbá a Scientistek ezeket gyűjtik,
 * hogy különböző ágenseket tudjanak előállítani belőle.
 */
public class Material {
    /**
     * Az anyag neve
     * Ez alapján különböztetjük meg őket.
     */
    private String name;
    /**
     * Az anyagból tárolt mennyiség.
     */
    private int quantity;

    /**
     * Konstruktor ami beállítja az attribútumokat
     * @param name beállítandó material név
     * @param quantity beállítandó mennyiség a materialból
     */
    public Material(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    /**
     * Getter a material nevéhez
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter a material nevéhez
     * @param name material új neve
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter a material mennyiségéhez
     * @return quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Setter a mennyiséghez
     * @param quantity új mennyiség
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * A quantity értékét egy 0 és maxValue>qantity?quantity:maxValue közötti véletlenszerű értékkel csökkenti,
     * és visszaadja ezt az értéket.
     * @param maxValue A bekapott maximum érték.
     * @return random int
     */
    public int decreaseWithRandomValue(int maxValue){
        Skeleton.printCall("Material.decreaseWithRandomValue()");
        Random rand = new Random();
        Skeleton.printReturn("random value");
        return rand.nextInt(Math.max(maxValue,quantity));
    }
    public int decreaseBy(int value){
        Skeleton.printCall("Material.decreaseBy()");
        quantity -= value;
        Skeleton.printReturn("quantity");
        return quantity;
    }

    /**
     * A quantity értékét value értékkel növeli, ez akkor használjuk,
     * amikor a Scientist rabol, vagy Materilat vesz fel a földről
     * @param value az érték amivel növeljük a quantity-t
     */
    public void increaseQuantity(int value){
        Skeleton.printCall("Material.increaseQuantity()");
        quantity=quantity+value;
        Skeleton.printReturn("void");
    }

    /**
     * A quantity értékét value értékkel csökkenti, ha ezzel az nem csökken 0 alá. Igaz visszatérés, ha sikeres, hamis visszatérés,
     * ha a value értéke nagyobb, mint a quantity, ebben az esetben a quantity értéke nem változik.
     * @param value az érték amivel csökkentünk
     * @return sikeres volt-e a csökkentés
     */
    public boolean decreaseQuantity(int value){
        Skeleton.printCall("Material.decreaseQuantity()");

        if (quantity-value>=0){
            quantity -= value;
            Skeleton.printReturn("true");
            return true;
        }
        return false;
    }

    public String toString() {
        return name + "(" + quantity + ")";
    }
}
