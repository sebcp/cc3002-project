package model.units;

import handlers.HeroDeathHandler;
import model.items.IEquipableItem;
import model.map.Location;
import tactician.Tactician;

import java.beans.PropertyChangeSupport;
import java.util.List;

/**
 * A Hero is a special kind of unit, the player that defeats this unit wins the game.
 *
 * This unit can only use spear weapons.
 *
 * @author Ignacio Slater Muñoz
 * @author Sebastián Contreras Phillippi
 * @since 1.0
 */
public class Hero extends AbstractUnit {
  private PropertyChangeSupport pcs = new PropertyChangeSupport(this);

  /**
   * Creates a new Unit.
   *
   * @param maxHitPoints
   *     the maximum amount of damage a unit can sustain
   * @param movement
   *     the number of panels a unit can move
   * @param location
   *     the initial position of this unit
   * @param items
   *     the items carried by this unit
   * @param name
   *     the name of the hero
   */
  public Hero(final int maxHitPoints, final int movement, final Location location, String name,
      IEquipableItem... items) {
    super(maxHitPoints, movement, location, 3, name, items);
  }

  @Override
  public void equipItem(IEquipableItem item) {
    if(this.getItems().contains(item) && this.getIsAlive()) {
      item.equipToHero(this);
    }
  }

  @Override
  public void receiveDamage(int damage) {
    if (this.getIsAlive()) {
      if(damage < 0) {
        this.receiveHealing(Math.abs(damage));
        return;
      }
      int damageDealt;
      if (this.getCurrentHitPoints() - damage <= 0) {
        damageDealt = this.getCurrentHitPoints();
        setCurrentHitPoints(0);
        setIsAlive(false);
        pcs.firePropertyChange("isAlive", true, false);
      }
      else {
        damageDealt = damage;
        setCurrentHitPoints(this.getCurrentHitPoints() - damage);
      }
      System.out.println(this.getName() + " received " + damageDealt + " points of damage.");
    }
  }

  public void addListener(Tactician tactician){
    pcs.addPropertyChangeListener(new HeroDeathHandler(tactician));
  }

  @Override
  public boolean equals(Object obj){
    if(obj instanceof Hero){
      int unitHitPoints = ((IUnit) obj).getCurrentHitPoints();
      int unitMaxHitPoints = ((IUnit) obj).getMaxHitPoints();
      boolean unitIsAlive = ((IUnit) obj).getIsAlive();
      int unitMovement = ((IUnit) obj).getMovement();
      IEquipableItem unitEquippedItem = ((IUnit) obj).getEquippedItem();
      List<IEquipableItem> unitItems = ((Hero) obj).items;
      Location unitLocation = ((IUnit) obj).getLocation();
      String unitName = ((IUnit) obj).getName();
      if(unitIsAlive==this.getIsAlive() && unitEquippedItem == this.getEquippedItem() &&
              unitHitPoints == this.getCurrentHitPoints() && unitMaxHitPoints == this.getMaxHitPoints()
              && unitItems == this.items && unitMovement == this.getMovement() &&
              unitLocation == this.getLocation() && unitName.equals(this.getName())){
        return true;
      }
    }
    return false;
  }
  }
