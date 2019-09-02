package model.units;

import model.items.IEquipableItem;
import model.map.Location;

import java.util.List;

/**
 * This class represents an Archer type unit.
 *
 * This kind of unit can only use bows.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class Archer extends AbstractUnit {

  /**
   * Creates a new archer
   *
   *     maximum hit points of the unit
   * @param maxHitPoints
   *      the maximum amount of health points
   * @param movement
   *     the amount of cells this unit can move
   * @param position
   *     the initial position of this unit
   * @param items
   *     the items carried by this unit
   */
  public Archer(final int maxHitPoints, final int movement, final Location position, String name,
      final IEquipableItem... items) {
    super(maxHitPoints, movement, position, 3, name, items);
  }

  /**
   * Sets the currently equipped item of this unit.
   *
   * The Archer can only equip Bows.
   *
   * @param item
   *     the item to equip
   */
  @Override
  public void equipItem(IEquipableItem item) {
    if(this.getItems().contains(item) && this.getIsAlive()) {
      item.equipToArcher(this);
    }
  }

  @Override
  public boolean equals(Object obj){
    if(obj instanceof Archer){
      int unitHitPoints = ((IUnit) obj).getCurrentHitPoints();
      int unitMaxHitPoints = ((IUnit) obj).getMaxHitPoints();
      boolean unitIsAlive = ((IUnit) obj).getIsAlive();
      int unitMovement = ((IUnit) obj).getMovement();
      IEquipableItem unitEquippedItem = ((IUnit) obj).getEquippedItem();
      List<IEquipableItem> unitItems = ((Archer) obj).items;
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
