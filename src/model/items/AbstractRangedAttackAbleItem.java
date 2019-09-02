package model.items;

public abstract class AbstractRangedAttackAbleItem extends AbstractAttackAbleItem{

    /**
     * Constructor for a default item that can attack other units.
     *
     * @param name     the name of the item
     * @param power    the power of the item (this could be the amount of damage or healing the item does)
     * @param minRange the minimum range of the item
     * @param maxRange the maximum range of the item
     */
    public AbstractRangedAttackAbleItem(String name, int power, int minRange, int maxRange) {
        super(name, power, minRange, maxRange);
        this.minRange = Math.max(minRange, 2);
        this.maxRange = Math.max(maxRange, this.minRange+1);
    }
}
