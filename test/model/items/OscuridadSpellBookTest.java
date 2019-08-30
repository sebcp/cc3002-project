package model.items;

import static org.junit.jupiter.api.Assertions.assertTrue;

import model.map.Location;
import model.units.Sorcerer;
import model.units.IUnit;
import org.junit.jupiter.api.Test;

/**
 * Test set for spell books
 *
 * @author Sebastián Contreras Phillippi
 */

public class OscuridadSpellBookTest extends AbstractTestItem{

    private OscuridadSpellBook spellbook;
    private OscuridadSpellBook wrongSpellbook;
    private Sorcerer sorcerer;

    /**
     * Sets up a correctly implemented item that's going to be tested.
     */
    @Override
    public void setTestItem() {
        expectedName = "Common oscuridad spell book";
        expectedPower = 8;
        expectedMinRange = 2;
        expectedMaxRange = 4;
        spellbook = new OscuridadSpellBook(expectedName, expectedPower, expectedMinRange, expectedMaxRange);
    }

    /**
     * Sets up an item with wrong ranges setted.
     */
    @Override
    public void setWrongRangeItem() {
        wrongSpellbook = new OscuridadSpellBook("Wrong spellbook", 10, 1, 1);
    }

    /**
     * Sets the unit that will be equipped with the test item
     */
    @Override
    public void setTestUnit() {
        sorcerer = new Sorcerer(10, 5, new Location(0, 0),"Sorcerer");
    }

    /**
     * Checks that the minimum range for a bow is greater than 1
     */
    @Override
    @Test
    public void incorrectRangeTest() {
        assertTrue(getWrongTestItem().getMinRange() > 1);
        assertTrue(getWrongTestItem().getMaxRange() >= getWrongTestItem().getMinRange());
    }

    @Override
    public IEquipableItem getWrongTestItem() { return wrongSpellbook; }

    /**
     * @return the item being tested
     */
    @Override
    public IEquipableItem getTestItem() {
        return spellbook;
    }

    /**
     * @return a unit that can equip the item being tested
     */
    @Override
    public IUnit getTestUnit() {
        return sorcerer;
    }

}
