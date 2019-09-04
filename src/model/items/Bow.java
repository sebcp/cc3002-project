package model.items;

import model.units.IUnit;

/**
 * This class represents a bow.
 *
 * Bows deal the same damage to all weapons and receive the default ammount of damage from all of
 * them.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class Bow extends AbstractRangedAttackAbleItem {

  /**
   * Creates a new bow.
   *
   * Bows are weapons that can't attack adjacent units, so it's minimum range must be greater than
   * one.
   *
   * @param name
   *     the name of the bow
   * @param power
   *     the damage power of the bow
   * @param minRange
   *     the minimum range of the bow
   * @param maxRange
   *     the maximum range of the bow
   */
  public Bow(final String name, final int power, final int minRange, final int maxRange) {
    super(name, power, minRange, maxRange);
  }

  @Override
  public void equipToArcher(IUnit unit) {
    unit.setEquippedItem(this);
    this.setOwner(unit);
  }

  @Override
  public void attack(IEquipableItem item){
    if(!this.equals(item) && item.getOwner()!=null) {
      item.receiveAttackFromBow(this);
    }
  }

  public boolean equals(Object obj){
    if(obj instanceof Bow){
      String itemName = ((Bow) obj).getName();
      int itemPower = ((Bow) obj).getPower();
      int itemMinRange = ((Bow) obj).getMinRange();
      int itemMaxRange = ((Bow) obj).getMaxRange();
      IUnit itemOwner = ((Bow) obj).getOwner();
      if(itemName.equals(this.getName()) && itemOwner == this.getOwner() &&
              itemMaxRange == this.getMaxRange() && itemMinRange == this.getMinRange() &&
              itemPower == this.getPower()){
        return true;
      }
    }
    return false;
  }
}
