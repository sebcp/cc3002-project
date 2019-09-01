package model.units;

import java.util.List;

import model.items.IEquipableItem;
import model.map.Location;

/**
 * This interface represents all units in the game.
 *
 * The signature of all the common methods that a unit can execute are defined here. All units
 * except some special ones can carry at most 3 weapons.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public interface IUnit {

  /**
   * Sets the currently equipped item of this unit.
   *
   * @param item
   *     the item to equip
   */
  void equipItem(IEquipableItem item);

  /**
   * @return hit points of the unit
   */
  int getCurrentHitPoints();

  /**
   * Returns the max amount of health points a unit can have.
   * @return the max amount of hp
   */
  int getMaxHitPoints();

  /**
   * @return the items carried by this unit
   */
  List<IEquipableItem> getItems();

  /**
   * @return the currently equipped item
   */
  IEquipableItem getEquippedItem();

  /**
   * @param item
   *     the item to be equipped
   */
  void setEquippedItem(IEquipableItem item);

  /**
   * @return the current location of the unit
   */
  Location getLocation();

  /**
   * Sets a new location for this unit,
   */
  void setLocation(final Location location);

  /**
   * @return the number of cells this unit can move
   */
  int getMovement();

  /**
   * Moves this unit to another location.
   *
   * If the other location is out of this unit's movement range, the unit doesn't move.
   */
  void moveTo(Location targetLocation);

  /**
   * Tries to give the unit the pos-th item of the inventory.
   * Only works if the targeted unit doesn't have a full inventory, is 1 cell away from the unit
   * that's giving the item and the item isn't equipped.
   * @param pos
   *      the position of the item to give.
   * @param unit
   *      the unit that receives the item.
   */
  void giveItem(int pos, IUnit unit);

  /**
   * Counts how many items the unit is holding.
   * @return the number of item the unit is currently holding in its inventory.
   */
  int countItems();

  /**
   * @return the maximum number of items the unit can hold.
   */
  int getMaxItems();

  /**
   * Adds an item to the unit's inventory.
   * @param item
   *      the item to be added.
   */
  void addItem(IEquipableItem item);

  /**
   * Removes an item from the unit's inventory.
   * @param item
   *      the item to be removed.
   */
  void removeItem(IEquipableItem item);

  /**
   * Calculates the distance from the unit to the target.
   * @param target
   *      the unit that's desired to know how far it is.
   * @return the distance from the unit to the target.
   */
  int calculateDistance(IUnit target);

  /**
   * Starts a combat with the targeted unit, it ends when one of the units HP fall to 0.
   * Units cannot enter combat if they don't have an item equipped or if the targeted unit
   * is outside of the range defined by the equipped item.
   * @param unit
   *      the unit to be fought.
   */
  void combat(IUnit unit);

  /**
   * Lowers the hp of the unit by an amount defined by the attacker's item power. If
   * the unit's hp were to fall bellow 0, it's set to 0 and set to dead.
   * @param damage
   */
  void receiveDamage(int damage);

  void receiveHealing(int heal);

  /**
   * Sets the current health points to a specified value.
   * @param hp
   *      the value to be setted as hp.
   */
  void setCurrentHitPoints(int hp);

  /**
   * Sets the current state of the unit.
   * @param bool
   *      boolean to be set, true if the unit is alive; false if the unit is dead.
   */
  void setIsAlive(boolean bool);

  /**
   * Returns the current state of the unit.
   * @return true if the unit's hp hasn't fell to 0; false if it has.
   */
  boolean getIsAlive();

  /**
   * Returns the name of the unit.
   * @return the name of the unit as a string.
   */
  String getName();

  /**
   * Checks if the object is equal to the unit.
   * @param obj
   *      object to be compared.
   * @return true if the unit and the object are the same; false if they're different.
   */
  boolean equals(Object obj);
}

