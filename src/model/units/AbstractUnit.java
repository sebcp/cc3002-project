package model.units;

import static java.lang.Math.min;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.items.IEquipableItem;
import model.map.Location;

/**
 * This class represents an abstract unit.
 *
 * An abstract unit is a unit that cannot be used in the
 * game, but that contains the implementation of some of the methods that are common for most
 * units.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public abstract class AbstractUnit implements IUnit {

  protected final List<IEquipableItem> items = new ArrayList<>();
  private int currentHitPoints;
  private final int movement;
  private IEquipableItem equippedItem;
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

  @Override
  public int getCurrentHitPoints() {
    return currentHitPoints;
  }

  @Override
  public void setHp(int hp){ this.currentHitPoints = hp; }

  public void receiveDamage(int damage){
    int damageDealt;
    if(this.currentHitPoints-damage < 0){
      damageDealt = this.getCurrentHitPoints();
      this.setHp(0);
    }
    else{
      damageDealt = damage;
      this.setHp(this.getCurrentHitPoints()-damage);
    }
    System.out.println("The unit received " + damageDealt + " points of damage.");
  }

  @Override
  public List<IEquipableItem> getItems() {
    return List.copyOf(items);
  }

  @Override
  public void addItem(IEquipableItem item){
    this.items.add(item);
  }

  @Override
  public void removeItem(IEquipableItem item){ this.items.remove(item); }

  @Override
  public int calculateDistance(IUnit target) {
    return (int) this.getLocation().distanceTo(target.getLocation());
  }

  @Override
  public void combat(IUnit unit){
    IEquipableItem unitEquip = unit.getEquippedItem();
    IEquipableItem thisEquip = this.getEquippedItem();

    if(thisEquip==null){
      //Do nothing
      System.out.println("Units must have an item equipped to combat another unit.");
    }
    if(unitEquip==null){
      unit.setHp(0);
    }
    while(this.currentHitPoints!=0 && unit.getCurrentHitPoints()!=0 && thisEquip!=null &&
            unitEquip!=null) {
      thisEquip.attack(unitEquip);
      if(unit.getCurrentHitPoints()!=0){
        unitEquip.attack(thisEquip);
      }
    }
  }

  public int getMaxItems(){ return maxItems; }

  @Override
  public abstract void equipItem(IEquipableItem item);

  @Override
  public IEquipableItem getEquippedItem() {
    return equippedItem;
  }

  @Override
  public void setEquippedItem(final IEquipableItem item) {
    this.equippedItem = item;
  }

  @Override
  public int countItems(){ return this.getItems().size(); }

  public void giveItem(int pos, IUnit unit){
    if(unit.getItems().size()<unit.getMaxItems() &&
            this.getItems().get(pos)!=this.getEquippedItem() &&
            this.calculateDistance(unit)==1){
      unit.addItem(this.getItems().get(pos));
      this.removeItem(this.getItems().get(pos));
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
