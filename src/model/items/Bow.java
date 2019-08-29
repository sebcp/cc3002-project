package model.items;

import model.units.Fighter;
import model.units.IUnit;

/**
 * @author Ignacio Slater Muñoz
 * @since
 */
public class Bow extends AbstractItem {

  /**
   * Creates a new bow.
   * <p>
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
    this.minRange = Math.max(minRange, 2);
    this.maxRange = Math.max(maxRange, this.minRange);
  }

  @Override
  public void equipToFighter(IUnit unit) {
    System.out.println("Cannot equip " + this.getName() + ", fighters can only equip axes.");
  }

  @Override
  public void equipToArcher(IUnit unit) {
    unit.setEquippedItem(this);
    unit.addItem(this);
  }

  @Override
  public void equipToHero(IUnit unit) {
    System.out.println("Cannot equip " + this.getName() + ", heroes can only equip spears.");
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
}
