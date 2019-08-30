package model.items;

import model.units.IUnit;
/**
 * This class represents a Spell Book type item.
 *
 * Spell books are strong against all other weapons but they're also weak against all of the other
 * type of items.
 * Anima spell books are strong against Luz spell books and weak against Oscuridad spell books.
 * Oscuridad spell books are strong against Anima spell books and weak against Luz spell books.
 * Luz spell books are strong against Oscuridad spell books and weak against Anima spell books.
 *
 * @author Sebastián Contreras Phillippi
 */
public abstract class AbstractSpellBook extends AbstractItem {

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
     */
    public AbstractSpellBook(String name, int power, int minRange, int maxRange) {
        super(name, power, minRange, maxRange);
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
        this.setOwner(unit);
    }

    @Override
    public void equipToCleric(IUnit unit) {
        System.out.println("Cannot equip " + this.getName() + ", clerics can only equip staffs.");
    }

    @Override
    public void equipToSwordMaster(IUnit unit){
        System.out.println("Cannot equip " + this.getName() + ", sword masters can only equip swords.");
    }

    @Override
    public abstract void attack(IEquipableItem item);

    @Override
    public void receiveAttackFromAxe(Axe axe) {
        int damage = (int) Math.round(axe.getPower()*1.5);
        this.getOwner().receiveDamage(damage);
    }

    @Override
    public void receiveAttackFromSpear(Spear spear) {
        int damage = (int) Math.round(spear.getPower() * 1.5);
        this.getOwner().receiveDamage(damage);
    }

    @Override
    public void receiveAttackFromSword(Sword sword) {
        int damage = (int) Math.round(sword.getPower()*1.5);
        this.getOwner().receiveDamage(damage);
    }
}
