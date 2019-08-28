package model.units;

import static java.lang.Math.min;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.items.IEquipableItem;
import model.map.Location;

/**
 * This class represents an abstract unit.
 * <p>
 * An abstract unit is a unit that cannot be used in the
 * game, but that contains the implementation of some of the methods that are common for most
 * units.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public abstract class AbstractUnit implements IUnit {

  protected final List<IEquipableItem> items = new ArrayList<>();
  private final int currentHitPoints;
  private final int movement;
  protected IEquipableItem equippedItem;
  private Location location;
  private int maxItems;

  /**
   * Creates a new Unit.
   *
   * @param hitPoints
   *     the maximum amount of damage a unit can sustain
   * @param movement
   *     the number of panels a unit can move
   * @param location
   *     the current position of this unit on the map
   * @param maxItems
   *     maximum amount of items this unit can carry
   */
  protected AbstractUnit(final int hitPoints, final int movement,
      final Location location, final int maxItems, final IEquipableItem... items) {
    this.currentHitPoints = hitPoints;
    this.movement = movement;
    this.location = location;
    this.items.addAll(Arrays.asList(items).subList(0, min(maxItems, items.length)));
    this.maxItems = maxItems;
  }

  /**
   * Returns the actual hit points of the unit.
   * @return the current hit points.
   */
  @Override
  public int getCurrentHitPoints() {
    return currentHitPoints;
  }

  /**
   * Returns the item list of the unit.
   * @return the item list.
   */
  //cambiado a la lista misma en vez de una copia
  @Override
  public List<IEquipableItem> getItems() {
    return items;
  }

  /**
   * Returns the max amount of items the unit can hold.
   * @return the max amount of items.
   */
  public int getMaxItems(){ return maxItems; }

  @Override
  public void equipItem(IEquipableItem item){
    this.setEquippedItem(item);
  }

  /**
   * Returns the equipped item.
   * @return the equipped item.
   */
  @Override
  public IEquipableItem getEquippedItem() {
    return equippedItem;
  }

  /**
   * Sets a new equipped item.
   * @param item
   *      the item to be equipped.
   */
  @Override
  public void setEquippedItem(final IEquipableItem item) {
    this.equippedItem = item;
  }


  /**
   * Counts how many items the unit is holding.
   * @return the number of item the unit is currently holding in its inventory.
   */
  @Override
  public int countItems(){ return this.getItems().size();
  }

  /**
   * Tries to give the unit the pos-th item of the inventory.
   * @param pos
   *      the position of the item to give.
   * @param unit
   *      the unit that receives the item.
   */
  public void giveItem(int pos, IUnit unit){
    if(unit.getItems().size()<unit.getMaxItems() && this.getItems().get(pos)!=this.getEquippedItem()){
      unit.getItems().add(this.getItems().get(pos));
      this.getItems().remove(pos);
    }
  }

  @Override
  public Location getLocation() {
    return location;
  }

  @Override
  public void setLocation(final Location location) {
    this.location = location;
  }

  @Override
  public int getMovement() {
    return movement;
  }

  @Override
  public void moveTo(final Location targetLocation) {
    if (getLocation().distanceTo(targetLocation) <= getMovement()
        && targetLocation.getUnit() == null) {
      setLocation(targetLocation);
    }
  }
}
