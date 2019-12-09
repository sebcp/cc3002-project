package factory.unitFactory;

import model.Tactician;
import model.map.Location;
import model.units.IUnit;

/**
 * This interface represents the factories that create units for the game.
 *
 * The signature for all the common methods of the factories are defined here.
 * Every factory has a set of parameters (name, max health points, movement range and it's initial location)
 * used for creating units that can be changed using the methods defined here.
 *
 * @author Sebastián Contreras Phillippi
 * @version 2.0
 * @since 2.0
 */

public interface unitFactoryInterface {
    /**
     * Creates the unit specific to the factory.
     * @return the unit created.
     */
    IUnit create();

    /**
     * Sets the maximum ammount of health points of the unit to be created.
     */
    void setMaxHitPoints(int maxHitPoints);

    /**
     * Sets the maximum movement range of the unit to be created.
     */
    void setMovement(int movementRange);

    /**
     * Sets the initial location on the map of the unit to be created.
     */
    void setLocation(Location location);

    /**
     * Sets the name of the unit to be created.
     */
    void setName(String name);

    void setTactician(Tactician tactician);
}
