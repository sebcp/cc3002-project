package model.items;

import model.units.IUnit;

/**
 * This interface represents the weapons that the units of the game can use.
 *
 * The signature for all the common methods of the weapons are defined here. Every weapon has a
 * base damage and is strong or weak against other type of weapons.
 *
 * @author Ignacio Slater Muñoz
 * @author Sebastián Contreras Phillippi
 * @version 2.0
 * @since 1.0
 */
public interface IEquipableItem {

  /**
   * @return the unit that has currently equipped this item
   */
  IUnit getOwner();

  /**
   * Changes the item's owner.
   * @param unit
   *      the unit that's the new owner of the item.
   */
  void setOwner(IUnit unit);

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

  /**
   * Attacks the unit holding the specified item.
   * @param item
   *      the item that's held by the unit to be attacked.
   */
  void attack(IEquipableItem item);

  /**
   * Receives an attack from an axe.
   * @param axe
   *      the axe that's attacking.
   */
  void receiveAttackFromAxe(Axe axe);

  /**
   * Receives an attack from a spear.
   * @param spear
   *      the spear that's attacking.
   */
  void receiveAttackFromSpear(Spear spear);

  /**
   * Receives an attack from a sword.
   * @param sword
   *      the sword that's attacking.
   */
  void receiveAttackFromSword(Sword sword);

  /**
   * Receives an attack from a bow.
   * @param bow
   *      the bow that's attacking.
   */
  void receiveAttackFromBow(Bow bow);

  /**
   * Receives an attack from an anima spell book.
   * @param animaSpellBook
   *      the anima spell book that's attacking.
   */
  void receiveAttackFromAnimaSpellBook(AnimaSpellBook animaSpellBook);

  /**
   * Receives an attack from an oscuridad spell book.
   * @param oscuridadSpellBook
   *      the oscuridad spell book that's attacking.
   */
  void receiveAttackFromOscuridadSpellBook(OscuridadSpellBook oscuridadSpellBook);

  /**
   * Receives an attack from a luz spell book.
   * @param luzSpellBook
   *      the luz spell book that's attacking.
   */
  void receiveAttackFromLuzSpellBook(LuzSpellBook luzSpellBook);

  /**
   * Compares the item to an object.
   * @param obj
   *      the object to be compared.
   * @return true if the item and the object are the same; false if they're different.
   */
  boolean equals(Object obj);

  /**
   * Attacks back if possible.
   * @param item
   *      The item that's being attacked back.
   */
  void counterAttack(IEquipableItem item);
}


