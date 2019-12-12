package handlers;

import tactician.Tactician;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class HeroDeathHandler implements PropertyChangeListener {
    Tactician subscriber;

    public HeroDeathHandler(Tactician tactician){
        subscriber = tactician;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        subscriber.loseGame();
    }
}
