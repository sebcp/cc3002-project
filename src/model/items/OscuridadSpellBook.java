package model.items;

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
        int damage = animaSpellBook.getPower()-20;
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
}
