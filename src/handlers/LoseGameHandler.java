package handlers;

import controller.GameController;
import tactician.Tactician;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


/**
 * Handler for when you lose the game, either by losing all your units or losing any of your heroes.
 * It will send a message to the Game Controller requesting to remove the tactician.
 * @author Sebastián Contreras Phillippi
 * @version 2.0
 * @since 2.0
 */
public class LoseGameHandler implements PropertyChangeListener {
    GameController observer;
    Tactician observed;

    /**
     * Creates a new handler for the controller.
     * @param controller
     *      the controller that's managing the game
     * @param tactician
     *      the tactician to be observed
     */
    public LoseGameHandler(GameController controller, Tactician tactician){
        observer = controller;
        observed = tactician;
    }

    /**
     * Requests the controller to remove the tactician from the game. If the current turn it's the
     * tactician's it will end the turn before removing it.
     * @param evt
     *      A PropertyChangeEvent object describing the event source
     *      and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(observer.getTurnOwner()!=observed){
            observer.removeTactician(observed.getName());
        }
        else{
            observer.endTurn();
            observer.removeTactician(observed.getName());
        }
    }
}
