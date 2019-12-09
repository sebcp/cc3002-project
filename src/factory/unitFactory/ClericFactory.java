package factory.unitFactory;

import model.Tactician;
import model.map.Location;
import model.units.Cleric;
import model.units.IUnit;

public class ClericFactory implements unitFactoryInterface {
    private int maxHitPoints = 50;
    private int movementRange = 2;
    private Location initialLocation;
    private String name = "Cleric";
    private Tactician owner;

    @Override
    public IUnit create() {
        Cleric cleric = new Cleric(maxHitPoints,movementRange,initialLocation,name);
        if(owner!=null){
            owner.receiveUnit(cleric);
        }
        return cleric;
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

    @Override
    public void setTactician(Tactician tactician){
        owner=tactician;
    }
}
