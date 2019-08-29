package model.units;

import model.items.IEquipableItem;
import model.map.Location;

/**
 * This class represents a sorcerer type unit.
 * A sorcerer is a unit that can only use spell book type weapons.
 *
 * @author Sebastián Contreras Phillippi
 */
public class Sorcerer extends AbstractUnit{

    public Sorcerer(int hitPoints, final int movement, Location location, String name, IEquipableItem... items) {
        super(hitPoints, movement, location, 3, items);
        this.setName(name);
    }

    /**
     * Sets the currently equipped item of this unit.
     *
     * The sorcerer can only equip spell books.
     * @param item
     *     the item to equip
     */
    @Override
    public void equipItem(final IEquipableItem item) {
        item.equipToSorcerer(this);
    }

}
