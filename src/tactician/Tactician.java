package tactician;

import controller.GameController;
import handlers.LoseGameHandler;
import model.items.IEquipableItem;
import model.map.Location;
import model.units.IUnit;

import java.beans.PropertyChangeSupport;
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
    private String name;
    private IUnit selectedUnit;
    private PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    /**
     * Creates a tactician
     * @param name
     *      the name to be set for the tactician
     */
    public Tactician(String name){
        this.name=name;
    }

    /**
     * Returns the name of the tactician.
     * @return the name of the tactician.
     */
    public String getName(){
        return name;
    }

    /**
     * Selects a unit if it's owned by the tactician and can act.
     * @param unit
     *      the unit to be selected
     */
    public void selectUnit(IUnit unit){
        if(units.contains(unit) && unit.canAct() || unit==null ) {
            selectedUnit = unit;
        }
    }

    /**
     * Returns the units of the tactician.
     * @return he units of the tactician.
     */
    public List<IUnit> getUnits(){
        return units;
    }

    /**
     * Returns the currently selected unit.
     * @return the currently selected unit.
     */
    public IUnit getSelectedUnit(){
        return selectedUnit;
    }

    /**
     * Returns the selected unit's max hitpoints.
     * @return the selected unit's max hitpoints.
     */
    public int getSelectedMaxHitPoints(){
        if(selectedUnit!=null){
            return selectedUnit.getMaxHitPoints();
        }
        return 0;
    }

    /**
     * Returns the selected unit's current hitpoints.
     * @return the selected unit's current hitpoints.
     */
    public int getSelectedCurrentHitPoints(){
        if(selectedUnit!=null){
            return selectedUnit.getCurrentHitPoints();
        }
        return 0;
    }

    /**
     * Returns the selected unit's name.
     * @return the selected unit's name.
     */
    public String getSelectedName(){
        if(selectedUnit!=null){
            return selectedUnit.getName();
        }
        return "";
    }

    /**
     * Returns the selected unit's max movement range.
     * @return the selected unit's max movement range.
     */
    public int getSelectedMovement(){
        if(selectedUnit!=null){
            return selectedUnit.getMovement();
        }
        return 0;
    }

    /**
     * Returns the selected unit's current location.
     * @return the selected unit's current location.
     */
    public Location getSelectedLocation(){
        if(selectedUnit!=null){
            return selectedUnit.getLocation();
        }
        return null;
    }

    /**
     * Returns the selected unit's items.
     * @return the selected unit's items.
     */
    public List<IEquipableItem> getSelectedItems(){
        if(selectedUnit!=null){
            return selectedUnit.getItems();
        }
        return null;
    }

    /**
     * Adds a unit to the tactician's list.
     * @param unit
     *      the unit to be received.
     */
    public void receiveUnit(IUnit unit){
        units.add(unit);
    }

    /**
     * Removes a unit from the tactician's list.
     * @param unit
     *      the unit to be removed
     */
    public void removeUnit(IUnit unit){
        units.remove(unit);
        if(this.getUnits().size()==0){
            pcs.firePropertyChange("no units left", 1,0);
        }
    }

    /**
     * Resets all of the tactician's units' actions (canMove and canAct).
     */
    public void resetUnitActions(){
        for(int i=0; i<this.getUnits().size(); i++){
            IUnit unit = this.getUnits().get(i);
            unit.resetAction();
        }
    }

    /**
     * Sends a message to the controller's handler saying the tactician has lost a hero.
     */
    public void loseGame(){
        pcs.firePropertyChange("isHeroAlive",true,false);
    }

    /**
     * Adds a listener to the tactician's state.
     * @param controller
     *      the controller to observe the tactician
     */
    public void addListener(GameController controller){
        pcs.addPropertyChangeListener(new LoseGameHandler(controller,this));
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
