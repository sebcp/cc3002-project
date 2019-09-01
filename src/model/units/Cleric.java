package model.units;

import model.items.IEquipableItem;
import model.map.Location;

import java.util.List;

/**
 * This class represents a cleric type unit. A cleric can only use staff type weapons, which means
 * that it can receive attacks but can't counter attack any of those.
 *
 * @author Ignacio Slater Muñoz
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
   */
  public Cleric(final int maxHitPoints, final int movement, final Location location, String name,
      IEquipableItem... items) {
    super(maxHitPoints, movement, location, 3, name, items);
  }

  /**
   * Sets the currently equipped item of this unit.
   *
   * The Cleric can only equip staffs.
   *
   * @param item
   *     the item to equip
   */
  @Override
  public void equipItem(IEquipableItem item) {
    if(this.getItems().contains(item)) {
      item.equipToCleric(this);
    }
  }

  @Override
  public void combat(IUnit unit){
    //Do nothing
    System.out.println("Clerics cannot combat other units.");
  }

  public void heal(IUnit unit) {
    if (this.getEquippedItem() != null) {
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
      List<IEquipableItem> unitItems = ((IUnit) obj).getItems();
      Location unitLocation = ((IUnit) obj).getLocation();
      String unitName = ((IUnit) obj).getName();
      if(unitIsAlive==this.getIsAlive() && unitEquippedItem == this.getEquippedItem() &&
              unitHitPoints == this.getCurrentHitPoints() && unitMaxHitPoints == this.getMaxHitPoints()
              && unitItems == this.getItems() && unitMovement == this.getMovement() &&
              unitLocation == this.getLocation() && unitName == this.getName()){
        return true;
      }
    }
    return false;
  }
}
