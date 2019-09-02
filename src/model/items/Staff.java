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
   *     the healing power of the staff
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
}
