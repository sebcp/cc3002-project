package handlers;

import controller.GameController;
import tactician.Tactician;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LoseGameHandler implements PropertyChangeListener {
    GameController observer;
    Tactician observed;

    public LoseGameHandler(GameController controller, Tactician tactician){
        observer = controller;
        observed = tactician;
    }

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
