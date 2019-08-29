package model.items;

import model.units.Fighter;
import model.units.IUnit;

/**
 * @author Sebastián Contreras Phillippi
 */
public class SpellBook extends AbstractItem {

    private String type;

    /**
     * Creates a new spell book.
     * Spell books cannot attack adjacent units, so it's minimum range must be greater than 1.
     *
     * @param name
     *      the name of the spell book
     * @param power
     *      the damage power of the spell book
     * @param minRange
     *      the minimum range of the spell book
     * @param maxRange
     *      the maximum range of the spell book
     * @param type
     *      the type of magic of the spell book
     */
    public SpellBook(String name, int power, int minRange, int maxRange, String type) {
        super(name, power, minRange, maxRange);
        if(type!="Anima" && type!="Luz" && type!="Oscuridad"){ this.type = "Anima"; }
        else{ this.type = type; }
        this.minRange = Math.max(minRange, 2);
        this.maxRange = Math.max(maxRange, this.minRange);
    }

    @Override
    public void equipToFighter(IUnit unit) {
        System.out.println("Cannot equip " + this.getName() + ", fighters can only equip axes.");
    }

    @Override
    public void equipToArcher(IUnit unit) {
        System.out.println("Cannot equip " + this.getName() + ", archers can only equip bows." );
    }

    @Override
    public void equipToHero(IUnit unit) {
        System.out.println("Cannot equip " + this.getName() + ", heroes can only equip spears.");
    }

    @Override
    public void equipToSorcerer(IUnit unit){
        unit.setEquippedItem(this);
        unit.addItem(this);
    }

    @Override
    public void equipToCleric(IUnit unit) {
        System.out.println("Cannot equp " + this.getName() + ", clerics can only equip staffs.");
    }

    @Override
    public void equipToSwordMaster(IUnit unit){
        System.out.println("Cannot equip " + this.getName() + ", sword masters can only equip swords.");
    }
}
