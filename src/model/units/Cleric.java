package model.units;

import model.items.IEquipableItem;
import model.map.Location;

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
  public Cleric(int maxHitPoints, final int movement, final Location location, String name,
      IEquipableItem... items) {
    super(maxHitPoints, movement, location, 3, items);
    this.setName(name);
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
  public void equipItem(final IEquipableItem item) {
    item.equipToCleric(this);
  }

  @Override
  public void combat(IUnit unit){
    //Do nothing
    System.out.println("Clerics cannot combat other units.");
  }
}
