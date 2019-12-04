package factory.unitFactory;

import model.map.Location;
import model.units.IUnit;
import model.units.SwordMaster;

public class SwordMasterFactory implements unitFactoryInterface {
    private int maxHitPoints = 50;
    private int movementRange = 2;
    private Location initialLocation;
    private String name = "SwordMaster";

    @Override
    public IUnit create() {
        return new SwordMaster(maxHitPoints,movementRange,initialLocation,name);
    }

    @Override
    public void setMaxHitPoints(int maxHitPoints) {
        this.maxHitPoints= maxHitPoints;
    }

    @Override
    public void setMovement(int movementRange) {
        this.movementRange=movementRange;
    }

    @Override
    public void setLocation(Location location) {
        this.initialLocation=location;
    }

    @Override
    public void setName(String name) {
        this.name=name;
    }
}
