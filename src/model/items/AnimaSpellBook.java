package model.items;

public class AnimaSpellBook extends AbstractSpellBook {
    /**
     * Creates a new anima spell book.
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
    public AnimaSpellBook(String name, int power, int minRange, int maxRange) {
        super(name, power, minRange, maxRange);
    }

    @Override
    public void attack(IEquipableItem item) { item.receiveAttackFromAnimaSpellBook(this); }

    @Override
    public void receiveAttackFromAnimaSpellBook(AnimaSpellBook animaSpellBook) {
        int damage = animaSpellBook.getPower();
        this.getOwner().receiveDamage(damage);
    }

    @Override
    public void receiveAttackFromOscuridadSpellBook(OscuridadSpellBook oscuridadSpellBook) {
        int damage = (int) Math.round(oscuridadSpellBook.getPower()*1.5);
        this.getOwner().receiveDamage(damage);
    }

    @Override
    public void receiveAttackFromLuzSpellBook(LuzSpellBook luzSpellBook) {
        int damage = luzSpellBook.getPower()-20;
        this.getOwner().receiveDamage(damage);
    }
}
