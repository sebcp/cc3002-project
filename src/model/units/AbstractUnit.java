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

  protected List<IEquipableItem> items = new ArrayList<>();
  private int currentHitPoints;
  private final int maxHitPoints;
  private final int movement;
  private IEquipableItem equippedItem;
  private Location location;
  private final int maxItems;
  private boolean isAlive;
  private final String name;

  /**
   * Creates a new Unit.
   *  @param maxHitPoints
   *      the maximum amount of health points
   * @param movement
   *      the number of panels a unit can move
   * @param location
 *        the current position of this unit on the map
   * @param maxItems
   *      the maximum amount of items the unit can hold
   * @param name
   *      the name of the unit
   */
  protected AbstractUnit(final int maxHitPoints, final int movement,
                         Location location, final int maxItems, String name, IEquipableItem... items) {
    this.maxHitPoints = maxHitPoints;
    this.currentHitPoints = maxHitPoints;
    this.movement = movement;
    this.location = location;
    this.name = name;
    this.items.addAll(Arrays.asList(items).subList(0, min(maxItems, items.length)));
    this.maxItems = maxItems;
    this.isAlive = true;
  }

  public String getName(){ return this.name; }

  public int getCurrentHitPoints() {
    return currentHitPoints;
  }

  public int getMaxHitPoints(){ return maxHitPoints; }

  public void setCurrentHitPoints(int hp) {
    if (hp <= 0) {
      this.currentHitPoints = 0;
      this.setIsAlive(false);
    }
    else {
      if (hp <= this.getMaxHitPoints()) { this.currentHitPoints = hp; }
      else { this.currentHitPoints = this.getMaxHitPoints(); }
    }
  }

  public void setIsAlive(boolean bool){ this.isAlive = bool; }

  public boolean getIsAlive(){ return this.isAlive; }

  public void receiveDamage(int damage){
    int damageDealt;
    if(this.currentHitPoints-damage <= 0){
      damageDealt = this.getCurrentHitPoints();
      this.setCurrentHitPoints(0);
      this.setIsAlive(false);
    }
    else{
      damageDealt = damage;
      this.setCurrentHitPoints(this.getCurrentHitPoints()-damage);
    }
    System.out.println(this.getName() + " received " + damageDealt + " points of damage.");
  }

  public void receiveHealing(int healing){
    if(this.getIsAlive()){
      int current = this.getCurrentHitPoints();
      int max = this.getMaxHitPoints();
      int heal = 0;
      if(current<max){
        if(current + healing >= max){
          heal = max - current;
          this.setCurrentHitPoints(max);
        }
        else{
          heal = healing;
          this.setCurrentHitPoints(current + healing);
        }
      }
      System.out.println(this.getName() + " was healed for " + heal + " points.");
    }
  }

  public List<IEquipableItem> getItems() { return List.copyOf(items); }

  public void addItem(IEquipableItem item){
    this.items.add(item);
  }

  public void removeItem(IEquipableItem item){ this.items.remove(item); }

  public int calculateDistance(IUnit target) {
    return (int) this.getLocation().distanceTo(target.getLocation());
  }

  public void combat(IUnit unit){
    if(this.getIsAlive() && unit.getIsAlive()) {
      IEquipableItem unitEquip = unit.getEquippedItem();
      IEquipableItem thisEquip = this.getEquippedItem();
      int distance = this.calculateDistance(unit);
      if (thisEquip == null) {
        //Do nothing
        System.out.println("Units must have an item equipped to combat another unit.");
      }
      else {
        if (distance >= thisEquip.getMinRange() && distance <= thisEquip.getMaxRange()) {
          if (unitEquip == null || unitEquip.getMinRange() > distance ||
                  unitEquip.getMaxRange() < distance) {
            while (unit.getCurrentHitPoints() != 0) {
              unit.receiveDamage(thisEquip.getPower());
            }
          }
          while (this.currentHitPoints != 0 && unit.getCurrentHitPoints() != 0) {
            thisEquip.attack(unitEquip);
            if (unit.getCurrentHitPoints() != 0) {
              unitEquip.attack(thisEquip);
            }
          }
        }
      }
    }
  }

  public int getMaxItems(){ return maxItems; }

  public abstract void equipItem(IEquipableItem item);

  public IEquipableItem getEquippedItem() { return equippedItem; }

  public void setEquippedItem(final IEquipableItem item) { this.equippedItem = item; }

  public int countItems(){ return this.getItems().size(); }

  public void giveItem(int pos, IUnit unit){
    if(unit.getItems().size()<unit.getMaxItems() &&
            this.getItems().get(pos)!=this.getEquippedItem() &&
            this.calculateDistance(unit)==1){
      unit.addItem(this.getItems().get(pos));
      this.removeItem(this.getItems().get(pos));
    }
  }

  public Location getLocation() { return location; }

  public void setLocation(final Location location) { this.location = location; }

  public int getMovement() { return movement; }

  public void moveTo(final Location targetLocation) {
    if (getLocation().distanceTo(targetLocation) <= getMovement()
        && targetLocation.getUnit() == null) {
      setLocation(targetLocation);
    }
  }

  public abstract boolean equals(Object obj);
}
