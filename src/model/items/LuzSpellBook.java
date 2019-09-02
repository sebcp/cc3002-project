package model.items;

import model.units.IUnit;

/**
 * This class represents an Anima Spell Book type item.
 * Luz spell books are strong against Oscuridad spell books and weak against Anima spell books.
 *
 * @author Sebastián Contreras Phillippi
 * @since 1.0
 */
public class LuzSpellBook extends AbstractSpellBook {
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
    public LuzSpellBook(String name, int power, int minRange, int maxRange) {
        super(name, power, minRange, maxRange);
    }

    @Override
    public void attack(IEquipableItem item) {
        item.receiveAttackFromLuzSpellBook(this);
    }

    @Override
    public void receiveAttackFromAnimaSpellBook(AnimaSpellBook animaSpellBook) {
        int damage = (int) Math.round(animaSpellBook.getPower()*1.5);
        this.getOwner().receiveDamage(damage);
    }

    @Override
    public void receiveAttackFromOscuridadSpellBook(OscuridadSpellBook oscuridadSpellBook) {
        int damage = oscuridadSpellBook.getPower()-20;
        this.getOwner().receiveDamage(damage);
    }

    @Override
    public void receiveAttackFromLuzSpellBook(LuzSpellBook luzSpellBook) {
        int damage = luzSpellBook.getPower();
        this.getOwner().receiveDamage(damage);
    }

    public boolean equals(Object obj){
        if(obj instanceof LuzSpellBook){
            String itemName = ((LuzSpellBook) obj).getName();
            int itemPower = ((LuzSpellBook) obj).getPower();
            int itemMinRange = ((LuzSpellBook) obj).getMinRange();
            int itemMaxRange = ((LuzSpellBook) obj).getMaxRange();
            IUnit itemOwner = ((LuzSpellBook) obj).getOwner();
            if(itemName.equals(this.getName()) && itemOwner == this.getOwner() &&
                    itemMaxRange == this.getMaxRange() && itemMinRange == this.getMinRange() &&
                    itemPower == this.getPower()){
                return true;
            }
        }
        return false;
    }
}
