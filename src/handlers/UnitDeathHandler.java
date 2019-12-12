package handlers;

import model.units.IUnit;
import tactician.Tactician;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class UnitDeathHandler implements PropertyChangeListener {
    Tactician subscriber;
    IUnit unit;

    public UnitDeathHandler(Tactician tactician, IUnit unit){
        subscriber = tactician;
        this.unit = unit;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        subscriber.removeUnit(unit);
    }
}
