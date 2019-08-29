package model.units;

import model.items.IEquipableItem;
import model.map.Location;

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
   * @param hitPoints
   *     the amount of damage this unit can receive
   * @param movement
   *     number of cells the unit can move
   * @param location
   *     current position of the unit
   */
  public Alpaca(int hitPoints, final int movement, final Location location, String name,
      final IEquipableItem... items) {
    super(hitPoints, movement, location, Integer.MAX_VALUE, items);
    this.setName(name);
  }

  /**
   * {@inheritDoc}
   *
   * The Alpaca cannot equip any item.
   */
  @Override
  public void equipItem(final IEquipableItem item) {
    // Method body intentionally left empty
  }

  @Override
  public void combat(IUnit unit){
    //Do nothing
    System.out.println("Alpacas cannot combat other units.");
  }

}
