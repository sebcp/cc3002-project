package factory.unitFactory;

import model.map.Location;
import model.units.Hero;
import model.units.IUnit;

public class HeroFactory implements unitFactoryInterface{
    private int maxHitPoints = 50;
    private int movementRange = 2;
    private Location initialLocation;
    private String name = "Hero";

    @Override
    public IUnit create() {
        return new Hero(maxHitPoints,movementRange,initialLocation,name);
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
        if(location.isEmpty()) {
            this.initialLocation = location;
        }
        else{
            initialLocation = null;
        }
    }

    @Override
    public void setName(String name) {
        this.name=name;
    }
}
