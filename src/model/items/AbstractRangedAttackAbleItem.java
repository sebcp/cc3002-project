package model.items;

/**
 * Abstract class that defines some common information and behaviour between all ranged
 * attack-able items.
 *
 * @author Ignacio Slater Muñoz
 * @author Sebastián Contreras Phillippi
 * @version 2.0
 * @since 1.0
 */
public abstract class AbstractRangedAttackAbleItem extends AbstractAttackAbleItem{

    /**
     * Constructor for a default ranged item that can attack other units.
     * Ranged items must have a minimum range greater or equal than 2.
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
