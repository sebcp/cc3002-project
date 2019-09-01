package model.units;

import model.items.IEquipableItem;
import model.map.Location;

import java.util.List;

/**
 * A Hero is a special kind of unit, the player that defeats this unit wins the game.
 *
 * This unit can only use spear weapons.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class Hero extends AbstractUnit {

  /**
   * Creates a new Unit.
   *
   * @param maxHitPoints
   *     the maximum amount of damage a unit can sustain
   * @param movement
   *     the number of panels a unit can move
   */
  public Hero(final int maxHitPoints, final int movement, final Location location, String name,
      IEquipableItem... items) {
    super(maxHitPoints, movement, location, 3, name, items);
  }

  /**
   * Sets the currently equipped item of this unit.
   *
   * The hero can only equip spears.
   * @param item
   *     the item to equip
   */
  @Override
  public void equipItem(IEquipableItem item) {
    if(this.getItems().contains(item)) {
      item.equipToHero(this);
    }
  }

  @Override
  public boolean equals(Object obj){
    if(obj instanceof Hero){
      int unitHitPoints = ((IUnit) obj).getCurrentHitPoints();
      int unitMaxHitPoints = ((IUnit) obj).getMaxHitPoints();
      boolean unitIsAlive = ((IUnit) obj).getIsAlive();
      int unitMovement = ((IUnit) obj).getMovement();
      IEquipableItem unitEquippedItem = ((IUnit) obj).getEquippedItem();
      List<IEquipableItem> unitItems = ((IUnit) obj).getItems();
      Location unitLocation = ((IUnit) obj).getLocation();
      String unitName = ((IUnit) obj).getName();
      if(unitIsAlive==this.getIsAlive() && unitEquippedItem == this.getEquippedItem() &&
              unitHitPoints == this.getCurrentHitPoints() && unitMaxHitPoints == this.getMaxHitPoints()
              && unitItems == this.getItems() && unitMovement == this.getMovement() &&
              unitLocation == this.getLocation() && unitName == this.getName()){
        return true;
      }
    }
    return false;
  }
  }
