package model.items;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

public class AnimaSpellBookTest extends AbstractTestItem{

    private AnimaSpellBook spellbook;
    private AnimaSpellBook wrongSpellbook;
    private Sorcerer sorcerer;

    /**
     * Sets up a correctly implemented item that's going to be tested.
     */
    @Override
    public void setTestItem() {
        expectedName = "Common anima spell book";
        expectedPower = 8;
        expectedMinRange = 2;
        expectedMaxRange = 4;
        spellbook = new AnimaSpellBook(expectedName, expectedPower, expectedMinRange, expectedMaxRange);
    }

    /**
     * Sets up an item with wrong ranges setted.
     */
    @Override
    public void setWrongRangeItem() {
        wrongSpellbook = new AnimaSpellBook("Wrong spellbook", 10, 1, 1);
    }

    /**
     * Sets the unit that will be equipped with the test item
     */
    @Override
    public void setTestUnit() {
        sorcerer = new Sorcerer(50, 5, new Location(0, 0),"Sorcerer");
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

    @Test
    @Override
    public void checkWeakness() {
        getTestUnit().addItem(getTestItem());
        getTestUnit().equipItem(getTestItem());
        assertEquals(getTestItem(),getTestUnit().getEquippedItem());

        OscuridadSpellBook item = new OscuridadSpellBook("test",10,2,3);
        getTestItem().receiveAttackFromOscuridadSpellBook(item);
        assertEquals(35,getTestUnit().getCurrentHitPoints());
        Axe axe = new Axe("Axe",5,1,2);
        Spear spear = new Spear("Spear",5,1,2);
        Sword sword = new Sword("Sword",5,1,2);
        getTestItem().receiveAttackFromAxe(axe);
        assertEquals(27,getTestUnit().getCurrentHitPoints());
        getTestItem().receiveAttackFromSword(sword);
        assertEquals(19,getTestUnit().getCurrentHitPoints());
        getTestItem().receiveAttackFromSpear(spear);
        assertEquals(11,getTestUnit().getCurrentHitPoints());
    }

    @Test
    @Override
    public void checkResistance(){
        getTestUnit().addItem(getTestItem());
        getTestUnit().equipItem(getTestItem());
        assertEquals(getTestItem(),getTestUnit().getEquippedItem());

        LuzSpellBook test = new LuzSpellBook("test",10,2,3);
        getTestItem().receiveAttackFromLuzSpellBook(test);
        assertEquals(getTestUnit().getMaxHitPoints(),getTestUnit().getCurrentHitPoints());
    }

    /**
     * @return the item being tested
     */
    @Override
    public IEquipableItem getTestItem() {
        return spellbook;
    }

    @Test
    @Override
    public void equippedToTest() {
        getTestItem().equipToSorcerer(getTestUnit());
        assertEquals(getTestItem(),getTestUnit().getEquippedItem());
    }

    @Override
    @Test
    public void checkAttack(){
        getTestUnit().addItem(getTestItem());
        getTestUnit().equipItem(getTestItem());
        setTarget();
        cleric.moveTo(new Location(1,1));
        getTestItem().attack(staff);
        assertEquals(38,cleric.getCurrentHitPoints());
    }

    /**
     * @return a unit that can equip the item being tested
     */
    @Override
    public IUnit getTestUnit() {
        return sorcerer;
    }

}
