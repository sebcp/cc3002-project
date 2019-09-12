package model.items;

import model.units.IUnit;

/**
 * This class represents an Anima Spell Book type item.
 * Oscuridad spell books are strong against Anima spell books and weak against Luz spell books.
 *
 * @author Sebastián Contreras Phillippi
 * @since 1.0
 */
public class OscuridadSpellBook extends AbstractSpellBook {

    /**
     * Creates a new spell book.
     * Spell books cannot attack adjacent units, so it's minimum range must be greater than 1.
     *
     * @param name     the name of the spell book
     * @param power    the damage power of the spell book
     * @param minRange the minimum range of the spell book
     * @param maxRange
     */
    public OscuridadSpellBook(String name, int power, int minRange, int maxRange) {
        super(name, power, minRange, maxRange);
    }

    @Override
    public void attack(IEquipableItem item) {
        item.receiveAttackFromOscuridadSpellBook(this);
    }

    @Override
    public void receiveAttackFromAnimaSpellBook(AnimaSpellBook animaSpellBook) {
        int damage;
        if(animaSpellBook.getPower()-20>0){
            damage = animaSpellBook.getPower()-20;
        }
        else{
            damage = 0;
        }
        this.getOwner().receiveDamage(damage);
    }

    @Override
    public void receiveAttackFromOscuridadSpellBook(OscuridadSpellBook oscuridadSpellBook) {
        int damage = oscuridadSpellBook.getPower();
        this.getOwner().receiveDamage(damage);
    }

    @Override
    public void receiveAttackFromLuzSpellBook(LuzSpellBook luzSpellBook) {
        int damage = (int) Math.round(luzSpellBook.getPower()*1.5);
        this.getOwner().receiveDamage(damage);
    }

    public boolean equals(Object obj){
        if(obj instanceof OscuridadSpellBook){
            String itemName = ((OscuridadSpellBook) obj).getName();
            int itemPower = ((OscuridadSpellBook) obj).getPower();
            int itemMinRange = ((OscuridadSpellBook) obj).getMinRange();
            int itemMaxRange = ((OscuridadSpellBook) obj).getMaxRange();
            IUnit itemOwner = ((OscuridadSpellBook) obj).getOwner();
            if(itemName.equals(this.getName()) && itemOwner == this.getOwner() &&
                    itemMaxRange == this.getMaxRange() && itemMinRange == this.getMinRange() &&
                    itemPower == this.getPower()){
                return true;
            }
        }
        return false;
    }
}
