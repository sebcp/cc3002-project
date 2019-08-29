package model.units;

import model.items.IEquipableItem;
import model.map.Location;

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
   * @param hitPoints
   *     the maximum amount of damage a unit can sustain
   * @param movement
   *     the number of panels a unit can move
   */
  public Hero(int hitPoints, final int movement, final Location location, String name,
      IEquipableItem... items) {
    super(hitPoints, movement, location, 3, items);
    this.setName(name);
  }

  /**
   * Sets the currently equipped item of this unit.
   *
   * The hero can only equip spears.
   * @param item
   *     the item to equip
   */
  @Override
  public void equipItem(final IEquipableItem item) {
    item.equipToHero(this);
    }
  }
