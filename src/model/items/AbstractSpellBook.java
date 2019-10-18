package model.items;

import model.units.IUnit;
/**
 * This class defines the common behaviour of all spell book items.
 *
 * Spell books are strong against all other weapons but they're also weak against all of the other
 * type of items. Each spellbook has their own magic weakness and strength.
 *
 * @author Sebastián Contreras Phillippi
 * @version 2.0
 * @since 1.0
 */
public abstract class AbstractSpellBook extends AbstractRangedAttackAbleItem {

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
    }

    @Override
    public void equipToSorcerer(IUnit unit){
        unit.setEquippedItem(this);
        this.setOwner(unit);
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

    @Override
    public void receiveAttackFromBow(Bow bow) {
        int damage = (int) Math.round(bow.getPower()*1.5);
        this.getOwner().receiveDamage(damage);
    }

    public abstract boolean equals(Object obj);
}
