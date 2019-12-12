package factory.unitFactory;

import tactician.Tactician;
import model.map.Location;
import model.units.Archer;
import model.units.IUnit;

public class ArcherFactory implements UnitFactoryInterface {
    private int maxHitPoints = 50;
    private int movementRange = 2;
    private Location initialLocation;
    private String name = "Archer";
    private Tactician owner;

    @Override
    public IUnit create() {
        Archer archer = new Archer(maxHitPoints,movementRange,initialLocation,name);
        if(owner!=null){
            owner.receiveUnit(archer);
            archer.addListener(owner);
        }
        return archer;
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
