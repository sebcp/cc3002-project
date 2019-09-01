package model.items;

import model.units.IUnit;

/**
 * This class represents an Axe.
 *
 * Axes are strong against spears but weak against swords.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class Axe extends AbstractAttackAbleItem {

  /**
   * Creates a new Axe item
   *
   * @param name
   *     the name of the Axe
   * @param power
   *     the damage of the axe
   * @param minRange
   *     the minimum range of the axe
   * @param maxRange
   *     the maximum range of the axe
   */
  public Axe(final String name, final int power, final int minRange, final int maxRange) {
    super(name, power, minRange, maxRange);
  }

  @Override
  public void equipToFighter(IUnit unit) {
    unit.setEquippedItem(this);
    this.setOwner(unit);
  }

  @Override
  public void attack(IEquipableItem item){
    item.receiveAttackFromAxe(this);
  }

  @Override
  public void receiveAttackFromSpear(Spear spear){
    int damage = spear.getPower()-20;
    this.getOwner().receiveDamage(damage);
  }

  @Override
  public void receiveAttackFromSword(Sword sword) {
    int damage = (int) Math.round(sword.getPower()*1.5);
    this.getOwner().receiveDamage(damage);
  }

  public boolean equals(Object obj){
    if(obj instanceof Axe){
      String itemName = ((Axe) obj).getName();
      int itemPower = ((Axe) obj).getPower();
      int itemMinRange = ((Axe) obj).getMinRange();
      int itemMaxRange = ((Axe) obj).getMaxRange();
      IUnit itemOwner = ((Axe) obj).getOwner();
      if(itemName.equals(this.getName()) && itemOwner == this.getOwner() &&
              itemMaxRange == this.getMaxRange() && itemMinRange == this.getMinRange() &&
              itemPower == this.getPower()){
        return true;
      }
    }
    return false;
  }
}
