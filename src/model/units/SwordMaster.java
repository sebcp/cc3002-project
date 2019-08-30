package model.units;

import model.items.IEquipableItem;
import model.map.Location;

/**
 * This class represents a SwordMaster type unit.
 *
 * A SwordMaster is a unit that can only use sword type weapons.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class SwordMaster extends AbstractUnit {

  public SwordMaster(int maxHitPoints, final int movement, final Location location, String name,
      IEquipableItem... items) {
    super(maxHitPoints, movement, location, 3, items);
    this.setName(name);
  }

  /**
   * Sets the currently equipped item of this unit.
   *
   * The sword master can only equip swords.
   * @param item
   *     the item to equip
   */
  @Override
  public void equipItem(final IEquipableItem item) {
    item.equipToSwordMaster(this);
  }
}
