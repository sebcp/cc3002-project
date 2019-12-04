package factory.itemFactory;

import model.items.IEquipableItem;

/**
 * This interface represents the factories that create items for the game.
 *
 * The signature for all the common methods of the factories are defined here.
 * Every factory has a set of parameters (name, power, minimum range and maximum range)
 * used for creating items that can be changed using the methods defined here.
 *
 * @author Sebastián Contreras Phillippi
 * @version 2.0
 * @since 2.0
 */

public interface itemFactoryInterface {
    /**
     * Creates a new item of the type that's specific to the facrtory.
     * @return the item created
     */
    IEquipableItem create();

    /**
     * Sets the name of the item to be created.
     * @param name
     *      the name given to the item
     */
    void setName(String name);

    /**
     * Sets the power of the item to be created.
     * @param power
     *      the power given to the item
     */
    void setPower(int power);

    /**
     * Sets the minimum range of the item to be created.
     * @param minRange
     *      the minimum range given to the item
     */
    void setMinRange(int minRange);

    /**
     * Sets the maximum range of the item to be created.
     * @param maxRange
     *      the maximum range given to the item
     */
    void setMaxRange(int maxRange);
}
