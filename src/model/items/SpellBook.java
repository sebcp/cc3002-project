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
public class SpellBook extends AbstractItem {

    private String type;

    /**
     * Creates a new spell book.
     * Spell books cannot attack adjacent units, so it's minimum range must be greater than 1.
     * When spell books are created with a non-valid type, they're set to Anima by default.
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
        if(!type.equals("Anima") && !type.equals("Luz") && !type.equals("Oscuridad")){ this.type = "Anima"; }
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

    /**
     * Returns the type of the spell book (Anima, Luz, Oscuridad).
     * @return the type of the spell book.
     */
    public String getType() {
        return type;
    }

    @Override
    public void attack(IEquipableItem item){
        item.receiveAttackFromSpellBook(this);
    }

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
