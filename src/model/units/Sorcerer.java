package model.units;

import model.items.IEquipableItem;

import model.map.Location;

import java.util.List;

/**
 * This class represents a sorcerer type unit.
 * A sorcerer is a unit that can only use spell book type weapons.
 *
 * @author Sebastián Contreras Phillippi
 */
public class Sorcerer extends AbstractUnit{

    /**
     * Creates a new Unit.
     *
     * @param maxHitPoints
     *      the maximum amount of health points
     * @param movement
     *     the number of panels a unit can move
     * @param location
     *     the initial position of this unit
     * @param items
     *     the items carried by this unit
     * @param name
     *     the name of the sorcerer
     */
    public Sorcerer(final int maxHitPoints, final int movement, Location location,
                    String name, IEquipableItem... items) {
        super(maxHitPoints, movement, location, 3, name, items);
    }

    @Override
    public void equipItem(final IEquipableItem item) {
        if(this.getItems().contains(item) && this.getIsAlive()) {
            item.equipToSorcerer(this);
        }
    }

    @Override
    public boolean equals(Object obj){
        if(obj instanceof Sorcerer){
            int unitHitPoints = ((IUnit) obj).getCurrentHitPoints();
            int unitMaxHitPoints = ((IUnit) obj).getMaxHitPoints();
            boolean unitIsAlive = ((IUnit) obj).getIsAlive();
            int unitMovement = ((IUnit) obj).getMovement();
            IEquipableItem unitEquippedItem = ((IUnit) obj).getEquippedItem();
            List<IEquipableItem> unitItems = ((Sorcerer) obj).items;
            Location unitLocation = ((IUnit) obj).getLocation();
            String unitName = ((IUnit) obj).getName();
            if(unitIsAlive==this.getIsAlive() && unitEquippedItem == this.getEquippedItem() &&
                    unitHitPoints == this.getCurrentHitPoints() && unitMaxHitPoints == this.getMaxHitPoints()
                    && unitItems == this.items && unitMovement == this.getMovement() &&
                    unitLocation == this.getLocation() && unitName.equals(this.getName())){
                return true;
            }
        }
        return false;
    }

}
