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
public class Axe extends AbstractItem {

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
    unit.addItem(this);
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
    System.out.println("Cannot equip " + this.getName() + ", clerics can only equip staffs.");
  }

  @Override
  public void equipToSwordMaster(IUnit unit){
    System.out.println("Cannot equip " + this.getName() + ", sword masters can only equip swords.");
  }
}
