package model.items;

import model.units.IUnit;

import java.lang.Math;

/**
 * This class represents a spear.
 *
 * Spears are strong against swords and weak against axes
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class Spear extends AbstractItem {

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
  public void equipToFighter(IUnit unit) {
    System.out.println("Cannot equip " + this.getName() + ", fighters can only equip axes.");
  }

  @Override
  public void equipToArcher(IUnit unit) {
    System.out.println("Cannot equip " + this.getName() + ", archers can only equip bows." );
  }

  @Override
  public void equipToHero(IUnit unit) {
    unit.setEquippedItem(this);
    unit.addItem(this);
    this.setOwner(unit);
  }

  @Override
  public void equipToSorcerer(IUnit unit){
    System.out.println("Cannot equip " + this.getName() + ", sorcerers can only equip spell books.");
  }

  @Override
  public void equipToCleric(IUnit unit) {
    System.out.println("Cannot equp " + this.getName() + ", clerics can only equip staffs.");
  }

  @Override
  public void equipToSwordMaster(IUnit unit){
    System.out.println("Cannot equip " + this.getName() + ", sword masters can only equip swords.");
  }

  @Override
  public void attack(IEquipableItem item){
    item.receiveAttackFromSpear(this);
  }
  @Override
  public void receiveAttackFromAxe(Axe axe) {
    int damage = (int) Math.round(axe.getPower()*1.5);
    this.getOwner().receiveDamage(damage);
  }

  @Override
  public void receiveAttackFromSword(Sword sword) {
    int damage = sword.getPower()-20;
    this.getOwner().receiveDamage(damage);
  }
}
