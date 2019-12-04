package factory.unitFactory;

import model.units.IUnit;

public interface unitFactoryInterface {
    IUnit create();
    void setMaxHitPoints();
    void setMovement();
    void setLocation();
    void setName();
}
