package model.items;

import model.units.IUnit;

import java.lang.Math;

/**
 * This class represents a spear.
 *
 * Spears are strong against swords and weak against axes
 *
 * @author Ignacio Slater Muñoz
 * @author Sebastián Contreras Phillippi
 * @since 1.0
 */
public class Spear extends AbstractAttackAbleItem {

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
  public Spear(final String name, final int power, final int minRange, final int maxRange) {
    super(name, power, minRange, maxRange);
  }

  @Override
  public void equipToHero(IUnit unit) {
    unit.setEquippedItem(this);
    this.setOwner(unit);
  }

  @Override
  public void attack(IEquipableItem item){
    if (!this.equals(item) && item.getOwner() != null) {
      item.receiveAttackFromSpear(this);
    }
  }
  @Override
  public void receiveAttackFromAxe(Axe axe) {
    int damage = (int) Math.round(axe.getPower()*1.5);
    this.getOwner().receiveDamage(damage);
  }

  @Override
  public void receiveAttackFromSword(Sword sword) {
    int damage;
    if(sword.getPower()-20 > 0){
      damage = sword.getPower()-20;
    }
    else{
      damage= 0;
    }
    this.getOwner().receiveDamage(damage);
  }

  public boolean equals(Object obj){
    if(obj instanceof Spear){
      String itemName = ((Spear) obj).getName();
      int itemPower = ((Spear) obj).getPower();
      int itemMinRange = ((Spear) obj).getMinRange();
      int itemMaxRange = ((Spear) obj).getMaxRange();
      IUnit itemOwner = ((Spear) obj).getOwner();
      if(itemName.equals(this.getName()) && itemOwner == this.getOwner() &&
              itemMaxRange == this.getMaxRange() && itemMinRange == this.getMinRange() &&
              itemPower == this.getPower()){
        return true;
      }
    }
    return false;
  }
}
