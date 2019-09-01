package model.units;

import model.items.IEquipableItem;
import model.map.Location;

import java.util.List;

/**
 * This class represents a fighter type unit.
 * A fighter is a unit that can only use axe type weapons.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class Fighter extends AbstractUnit {

  public Fighter(final int maxHitPoints, final int movement, Location location, String name,
      IEquipableItem... items) {
    super(maxHitPoints, movement, location, 3, name, items);
  }

  /**
   * Sets the currently equipped item of this unit.
   *
   * The fighter can only equip axes.
   * @param item
   *     the item to equip
   */
  @Override
  public void equipItem(final IEquipableItem item) {
    if(this.getItems().contains(item)) {
      item.equipToFighter(this);
    }
  }

  @Override
  public boolean equals(Object obj){
    if(obj instanceof Fighter){
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
