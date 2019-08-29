package model.items;

import model.units.IUnit;

/**
 * This class represents a sword type item.
 *
 * Swords are strong against axes and weak against spears.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class Sword extends AbstractItem {

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
  public void equipToFighter(IUnit unit) {
    System.out.println("Cannot equip " + this.getName() + ", fighters can only equip axes.");
  }

  @Override
  public void equipToArcher(IUnit unit) {
    System.out.println("Cannot equip " + this.getName() + ", archers can only equip bows." );
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
    unit.setEquippedItem(this);
    unit.addItem(this);
  }
}
