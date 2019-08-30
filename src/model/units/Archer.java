package model.units;

import model.items.IEquipableItem;
import model.map.Location;

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
   * @param hitPoints
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
  public Archer(int maxHitPoints, final int movement, final Location position, String name,
      final IEquipableItem... items) {
    super(maxHitPoints, movement, position, 3, items);
    this.setName(name);
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
  public void equipItem(final IEquipableItem item) {
    item.equipToArcher(this);
  }
}
