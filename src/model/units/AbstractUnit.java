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
 * @author Sebastián Contreras Phillippi
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
    assert maxHitPoints>0 : String.format("A unit's maximum hit points must be a value greater than 0",0);
    assert movement>0 : String.format("A unit's maximum hit points must be a value greater than 0",0);
    this.maxHitPoints = maxHitPoints;
    this.currentHitPoints = maxHitPoints;
    this.movement = movement;
    this.location = location;
    this.name = name;
    this.items.addAll(Arrays.asList(items).subList(0, min(maxItems, items.length)));
    this.maxItems = maxItems;
    this.isAlive = true;
  }

  @Override
  public String getName(){ return this.name; }

  @Override
  public int getCurrentHitPoints() {
    return currentHitPoints;
  }

  @Override
  public int getMaxHitPoints(){ return maxHitPoints; }

  @Override
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

  @Override
  public void setIsAlive(boolean bool){ this.isAlive = bool; }

  @Override
  public boolean getIsAlive(){ return this.isAlive; }

  @Override
  public void receiveDamage(int damage) {
    if (this.getIsAlive()) {
      if(damage < 0) {
        this.receiveHealing(Math.abs(damage));
        return;
      }
      int damageDealt;
      if (this.currentHitPoints - damage <= 0) {
        damageDealt = this.getCurrentHitPoints();
        this.setCurrentHitPoints(0);
      }
      else {
        damageDealt = damage;
        this.setCurrentHitPoints(this.getCurrentHitPoints() - damage);
      }
      System.out.println(this.getName() + " received " + damageDealt + " points of damage.");
    }
  }
  @Override
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

  @Override
  public List<IEquipableItem> getItems() { return List.copyOf(items); }

  @Override
  public void addItem(IEquipableItem item){
    item.setOwner(this);
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
            unit.receiveDamage(thisEquip.getPower());
          }
          else {
            int current = unit.getCurrentHitPoints();
            thisEquip.attack(unitEquip);
            if (unit.getCurrentHitPoints() != 0 && current > unit.getCurrentHitPoints()) {
              unitEquip.counterAttack(thisEquip);
            }
          }
        }
        else {
         System.out.println("The target is out of the " + thisEquip.getName() +  "'s range.");
        }
      }
    }
    else{
      System.out.println("You cannot choose a dead unit.");
    }
  }

  @Override
  public int getMaxItems(){ return maxItems; }

  @Override
  public abstract void equipItem(IEquipableItem item);

  @Override
  public IEquipableItem getEquippedItem() { return equippedItem; }

  @Override
  public void setEquippedItem(final IEquipableItem item) { this.equippedItem = item; }

  @Override
  public int countItems(){ return this.getItems().size(); }

  @Override
  public void giveItem(int pos, IUnit unit){
    if(unit.getItems().size()<unit.getMaxItems() &&
            this.getItems().get(pos)!=this.getEquippedItem() &&
            this.calculateDistance(unit)==1 && this.getIsAlive() && unit.getIsAlive()){
      unit.addItem(this.getItems().get(pos));
      this.getItems().get(pos).setOwner(unit);
      this.removeItem(this.getItems().get(pos));

    }
  }

  @Override
  public Location getLocation() { return location; }

  @Override
  public void setLocation(final Location location) { this.location = location; }

  @Override
  public int getMovement() { return movement; }

  @Override
  public void moveTo(final Location targetLocation) {
    if (getLocation().distanceTo(targetLocation) <= getMovement()
        && targetLocation.getUnit() == null) {
      setLocation(targetLocation);
    }
  }

  public abstract boolean equals(Object obj);
}
