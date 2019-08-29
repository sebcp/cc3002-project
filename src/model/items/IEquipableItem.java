package model.items;

import model.units.Fighter;
import model.units.Hero;
import model.units.IUnit;

/**
 * This interface represents the <i>weapons</i> that the units of the game can use.
 * <p>
 * The signature for all the common methods of the weapons are defined here. Every weapon have a
 * base damage and is strong or weak against other type of weapons.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public interface IEquipableItem {

  /**
   * Equips this item to a unit.
   *
   * @param unit
   *     the unit that will be quipped with the item
   */
  void equipTo(IUnit unit);

  /**
   * @return the unit that has currently equipped this item
   */
  IUnit getOwner();

  /**
   * @return the name of the item
   */
  String getName();

  /**
   * @return the power of the item
   */
  int getPower();

  /**
   * @return the minimum range of the item
   */
  int getMinRange();

  /**
   * @return the maximum range of the item
   */
  int getMaxRange();

  /**
   * Tries to equip the item to a fighter unit.
   * @param unit
   *      the fighter to be equipped with the item.
   */
  void equipToFighter(IUnit unit);

  /**
   * Tries to equip the item to an archer unit.
   * @param unit
   *      the archer to be equipped with the item.
   */
  void equipToArcher(IUnit unit);

  /**
   * Tries to equip the item to a hero unit.
   * @param unit
   *      the hero to be equipped with the item.
   */
  void equipToHero(IUnit unit);

  /**
   * Tries to equip the item to a sorcerer unit.
   * @param unit
   *      the sorcerer to be equipped with the item.
   */
  void equipToSorcerer(IUnit unit);

  /**
   * Tries to equip the item to a cleric unit.
   * @param unit
   *      the cleric to be equipped with the item.
   */
  void equipToCleric(IUnit unit);

  /**
   * Tries to equip the item to a sword master unit.
   * @param unit
   *      the sword master to be equipped with the item.
   */
  void equipToSwordMaster(IUnit unit);
}


