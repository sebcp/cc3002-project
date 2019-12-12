package handlers;

import model.units.IUnit;
import tactician.Tactician;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Handler for the death of any unit besides the heroes.
 * It will send a message requesting the user to remove the unit from it's list when it dies.
 * @author Sebastián Contreras Phillippi
 * @version 2.0
 * @since 2.0
 */

public class UnitDeathHandler implements PropertyChangeListener {
    Tactician subscriber;
    IUnit unit;

    /**
     * Creates a handler for the tactician.
     * @param tactician
     *      the owner of the unit
     * @param unit
     *      the unit to be observed
     */
    public UnitDeathHandler(Tactician tactician, IUnit unit){
        subscriber = tactician;
        this.unit = unit;
    }

    /**
     * Requests the tactician to remove the unit from it's list.
     * @param evt
     *      A PropertyChangeEvent object describing the event source
     *      and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        subscriber.removeUnit(unit);
    }
}
