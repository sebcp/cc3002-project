package model.units;

import model.items.IEquipableItem;
import model.map.Location;

import java.util.List;

/**
 * This class represents an Alpaca type unit.
 *
 * This are a special kind of unit that can carry an unlimited amount of items but can't use any of
 * them.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class Alpaca extends AbstractUnit {

  /**
   * Creates a new Alpaca.
   *
   * @param maxHitPoints
   *      the maximum amount of health points
   * @param movement
   *     number of cells the unit can move
   * @param location
   *     current position of the unit
   */
  public Alpaca(final int maxHitPoints, final int movement, final Location location, String name,
      final IEquipableItem... items) {
    super(maxHitPoints, movement, location, Integer.MAX_VALUE, name, items);
  }

  /**
   * {@inheritDoc}
   *
   * The Alpaca cannot equip any item.
   */
  @Override
  public void equipItem(IEquipableItem item) {
    // Method body intentionally left empty
  }

  @Override
  public void combat(IUnit unit){
    //Do nothing
    System.out.println("Alpacas cannot combat other units.");
  }

  @Override
  public boolean equals(Object obj){
    if(obj instanceof Alpaca){
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
