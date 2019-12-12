package handlers;

import tactician.Tactician;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Handler for the death of a hero class unit.
 * It will send a message requesting the user to lose whenever one of it's heroes dies.
 * @author Sebastián Contreras Phillippi
 * @version 2.0
 * @since 2.0
 */

public class HeroDeathHandler implements PropertyChangeListener {
    Tactician subscriber;

    /**
     * Creates a handler for the tactician.
     * @param tactician
     *      the tactician to be the owner of the hero
     */
    public HeroDeathHandler(Tactician tactician){
        subscriber = tactician;
    }


    /**
     * Makes the subscriber/owner lose the game (be removed from the GameController).
     * @param evt
     *      A PropertyChangeEvent object describing the event source
     *      and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        subscriber.loseGame();
    }
}
