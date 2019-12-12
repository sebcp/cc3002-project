package model;

import model.items.IEquipableItem;
import model.map.Location;
import model.units.Archer;
import model.units.IUnit;

import java.util.ArrayList;
import java.util.List;

/**
 * The tactician represents a player of the game.
 * The tactician controls a set of chosen units and viciously fights other tacticians for triumph and honor.
 *
 * @author Sebastián Contreras Phillippi
 * @version 2.0
 * @since 2.0
 */
public class Tactician {
    private List<IUnit> units = new ArrayList<>();
    private List<IUnit> canAct = new ArrayList<>();
    private String name;
    private IUnit selectedUnit;

    public Tactician(String name){
        this.name=name;
    }

    public String getName(){
        return name;
    }

    public void selectUnit(IUnit unit){
        if(units.contains(unit) && unit.canAct() || unit==null ) {
            selectedUnit = unit;
        }
    }

    public List<IUnit> getUnits(){
        return units;
    }

    public IUnit getSelectedUnit(){
        return selectedUnit;
    }

    public int getSelectedMaxHitPoints(){
        if(selectedUnit!=null){
            return selectedUnit.getMaxHitPoints();
        }
        return 0;
    }

    public int getSelectedCurrentHitPoints(){
        if(selectedUnit!=null){
            return selectedUnit.getCurrentHitPoints();
        }
        return 0;
    }

    public String getSelectedName(){
        if(selectedUnit!=null){
            return selectedUnit.getName();
        }
        return "";
    }

    public int getSelectedMovement(){
        if(selectedUnit!=null){
            return selectedUnit.getMovement();
        }
        return 0;
    }

    public Location getSelectedLocation(){
        if(selectedUnit!=null){
            return selectedUnit.getLocation();
        }
        return null;
    }

    public List<IEquipableItem> getSelectedItems(){
        if(selectedUnit!=null){
            return selectedUnit.getItems();
        }
        return null;
    }

    public void receiveUnit(IUnit unit){
        units.add(unit);
    }

    public void removeUnit(IUnit unit){
        units.remove(unit);
    }

    public void resetUnitActions(){
        for(int i=0; i<this.getUnits().size(); i++){
            IUnit unit = this.getUnits().get(i);
            unit.resetAction();
        }
    }

    @Override
    public boolean equals(Object tactician){
        if(tactician instanceof Tactician){
            if(getName().equals(((Tactician) tactician).getName()) && getUnits().equals(((Tactician) tactician).getUnits())){
                return true;
            }
        }
        return false;
    }
}
