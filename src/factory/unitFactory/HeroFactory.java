package factory.unitFactory;

import tactician.Tactician;
import model.map.Location;
import model.units.Hero;
import model.units.IUnit;

public class HeroFactory implements UnitFactoryInterface {
    private int maxHitPoints = 50;
    private int movementRange = 2;
    private Location initialLocation;
    private String name = "Hero";
    private Tactician owner;

    @Override
    public IUnit create() {
        Hero hero = new Hero(maxHitPoints,movementRange,initialLocation,name);
        if(owner!=null){
            owner.receiveUnit(hero);
            hero.addHeroListener(owner);
        }
        return hero;
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
