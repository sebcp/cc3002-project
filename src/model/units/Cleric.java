package model.units;

import model.items.IEquipableItem;
import model.map.Location;

import java.util.List;

/**
 * This class represents a cleric type unit. A cleric can only use staff type weapons, which means
 * that it can receive attacks but can't counter attack any of those.
 *
 * @author Ignacio Slater Muñoz
 * @author Sebastián Contreras Phillippi
 * @since 1.0
 */
public class Cleric extends AbstractUnit {

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
   *     the name of the cleric
   */
  public Cleric(final int maxHitPoints, final int movement, final Location location, String name,
      IEquipableItem... items) {
    super(maxHitPoints, movement, location, 3, name, items);
  }

  @Override
  public void equipItem(IEquipableItem item) {
    if(this.getItems().contains(item) && this.getIsAlive()) {
      item.equipToCleric(this);
    }
  }

  /**
   * Clerics cannot combat other units.
   * @param unit
   *      the unit to be targeted.
   */
  @Override
  public void combat(IUnit unit){
    //Do nothing
    System.out.println("Clerics cannot combat other units.");
  }

  /**
   * Heals a unit. Healing depends on the staff's power. Clerics can only heal other
   * units if they're alive.
   * @param unit
   *      the unit to be healed.
   */
  public void heal(IUnit unit) {
    if (this.getEquippedItem() != null && this.getIsAlive()) {
      unit.receiveHealing(this.getEquippedItem().getPower());
    }
  }

  @Override
  public boolean equals(Object obj){
    if(obj instanceof Cleric){
      int unitHitPoints = ((IUnit) obj).getCurrentHitPoints();
      int unitMaxHitPoints = ((IUnit) obj).getMaxHitPoints();
      boolean unitIsAlive = ((IUnit) obj).getIsAlive();
      int unitMovement = ((IUnit) obj).getMovement();
      IEquipableItem unitEquippedItem = ((IUnit) obj).getEquippedItem();
      List<IEquipableItem> unitItems = ((Cleric) obj).items;
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
