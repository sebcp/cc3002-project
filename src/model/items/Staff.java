package model.items;

import model.units.IUnit;

/**
 * This class represents a Staff type item.
 *
 * A staff is an item that can heal other units but cannot counter any attack
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class Staff extends AbstractItem {

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
    unit.setEquippedItem(this);
    unit.addItem(this);
  }

  @Override
  public void equipToSwordMaster(IUnit unit){
    System.out.println("Cannot equip " + this.getName() + ", sword masters can only equip swords.");
  }

  public void attack(IEquipableItem item){
    //Intentionally left empty.
  }
}
