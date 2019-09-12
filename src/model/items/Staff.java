package model.items;

import model.units.IUnit;

/**
 * This class represents a Staff type item.
 *
 * A staff is an item that can heal other units but cannot counter any attack
 *
 * @author Ignacio Slater Muñoz
 * @author Sebastián Contreras Phillippi
 * @since 1.0
 */
public class Staff extends AbstractNonAttackAbleItem {

  /**
   * Creates a new Staff item.
   *
   * @param name
   *     the name of the staff
   * @param power
   *     the healing power of the staff, it has negative values.
   * @param minRange
   *     the minimum range of the staff
   * @param maxRange
   *     the maximum range of the staff
   */
  public Staff(final String name, final int power, final int minRange, final int maxRange) {
    super(name, power, minRange, maxRange);
  }

  @Override
  public void equipToCleric(IUnit unit) {
    unit.setEquippedItem(this);
    this.setOwner(unit);
  }

  public boolean equals(Object obj){
    if(obj instanceof Staff){
      String itemName = ((Staff) obj).getName();
      int itemPower = ((Staff) obj).getPower();
      int itemMinRange = ((Staff) obj).getMinRange();
      int itemMaxRange = ((Staff) obj).getMaxRange();
      IUnit itemOwner = ((Staff) obj).getOwner();
      if(itemName.equals(this.getName()) && itemOwner == this.getOwner() &&
              itemMaxRange == this.getMaxRange() && itemMinRange == this.getMinRange() &&
              itemPower == this.getPower()){
        return true;
      }
    }
    return false;
  }

  /**
   * Heals a unit. Healing depends on the staff's power. Clerics can only heal other
   * units if they're alive and if they're in range. Staffs MUST have negative power.
   * @param item
   *      the item that belongs to the unit to be healed.
   */
  public void heal(IEquipableItem item) {
    if (this.getOwner() != null && this.getOwner().getIsAlive()) {
      item.getOwner().receiveHealing(Math.abs(this.getPower()));
    }
  }

  @Override
  public void attack(IEquipableItem item){
    heal(item);
  }
}
