package factory.unitFactory;

import model.map.Location;
import model.units.Fighter;
import model.units.IUnit;

public class FighterFactory implements unitFactoryInterface {
    private int maxHitPoints = 50;
    private int movementRange = 2;
    private Location initialLocation;
    private String name = "Fighter";

    @Override
    public IUnit create() {
        return new Fighter(maxHitPoints,movementRange,initialLocation,name);
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
