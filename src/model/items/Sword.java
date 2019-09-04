package model.items;

import model.units.IUnit;

/**
 * This class represents a sword type item.
 *
 * Swords are strong against axes and weak against spears.
 *
 * @author Ignacio Slater Muñoz
 * @author Sebastián Contreras Phillippi
 * @since 1.0
 */
public class Sword extends AbstractAttackAbleItem {

  /**
   * Creates a new Sword.
   *
   * @param name
   *     the name that identifies the weapon
   * @param power
   *     the base damage pf the weapon
   * @param minRange
   *     the minimum range of the weapon
   * @param maxRange
   *     the maximum range of the weapon
   */
  public Sword(final String name, final int power, final int minRange, final int maxRange) {
    super(name, power, minRange, maxRange);
  }

  @Override
  public void equipToSwordMaster(IUnit unit){
    unit.setEquippedItem(this);
    this.setOwner(unit);
  }

  @Override
  public void attack(IEquipableItem item){
    if(!this.equals(item) && item.getOwner()!=null) {
      item.receiveAttackFromSword(this);
    }
  }

  @Override
  public void receiveAttackFromAxe(Axe axe) {
    int damage = axe.getPower()-20;
    this.getOwner().receiveDamage(damage);
  }

  @Override
  public void receiveAttackFromSpear(Spear spear){
    int damage = (int) Math.round(spear.getPower()*1.5);
    this.getOwner().receiveDamage(damage);
  }

  public boolean equals(Object obj){
    if(obj instanceof Sword){
      String itemName = ((Sword) obj).getName();
      int itemPower = ((Sword) obj).getPower();
      int itemMinRange = ((Sword) obj).getMinRange();
      int itemMaxRange = ((Sword) obj).getMaxRange();
      IUnit itemOwner = ((Sword) obj).getOwner();
      if(itemName.equals(this.getName()) && itemOwner == this.getOwner() &&
              itemMaxRange == this.getMaxRange() && itemMinRange == this.getMinRange() &&
              itemPower == this.getPower()){
        return true;
      }
    }
    return false;
  }
}
