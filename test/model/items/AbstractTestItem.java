package model.items;

import model.map.Location;
import model.units.Cleric;
import model.units.IUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Defines some common methods for all the items tests
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
  public abstract class AbstractTestItem {

  protected String expectedName;
  protected int expectedPower;
  protected short expectedMinRange;
  protected short expectedMaxRange;
  protected Cleric cleric;
  protected Staff staff;

  /**
   * Sets up the items to be tested
   */
  @BeforeEach
  public void setUp() {
    setTestItem();
    setWrongRangeItem();
    setTestUnit();
  }

  /**
   * Sets up a correctly implemented item that's going to be tested
   */
  public abstract void setTestItem();

  /**
   * Sets up an item with wrong ranges setted.
   */
  public abstract void setWrongRangeItem();

  /**
   * Sets the unit that will be equipped with the test item
   */
  public abstract void setTestUnit();

  public void setTarget(){
    staff = new Staff("Staff",10,1,2);
    cleric = new Cleric(50,2,new Location(0,1),"Cleric");
    cleric.addItem(staff);
    cleric.equipItem(staff);
  }

  /**
   * Checks that the tested item cannot have ranges outside of certain bounds.
   */
  @Test
  public void incorrectRangeTest() {
    assertTrue(getWrongTestItem().getMinRange() >= 0);
    assertTrue(getWrongTestItem().getMaxRange() >= getWrongTestItem().getMinRange());
  }

  /**
   * @return the item with wrong ranges setted.
   */
  public abstract IEquipableItem getWrongTestItem();

  /**
   * Tests that the constructor for the tested item works properly
   */
  @Test
  public void constructorTest() {
    assertEquals(getExpectedName(), getTestItem().getName());
    assertEquals(getExpectedBasePower(), getTestItem().getPower());
    assertEquals(getExpectedMinRange(), getTestItem().getMinRange());
    assertEquals(getExpectedMaxRange(), getTestItem().getMaxRange());
  }

  public abstract void checkWeakness();

  public abstract void checkResistance();

  @Test
  public void checkAttack(){
    getTestUnit().addItem(getTestItem());
    getTestUnit().equipItem(getTestItem());
    setTarget();
    getTestItem().attack(staff);
    assertEquals(40,cleric.getCurrentHitPoints());
  }

  @Test
  public void compareItems(){
    assertFalse(getTestItem().equals(getWrongTestItem()));
  }

  /**
   * @return the expected name of the item
   */
  public String getExpectedName() {
    return expectedName;
  }

  /**
   * @return the item being tested
   */
  public abstract IEquipableItem getTestItem();

  /**
   * @return the expected power of the Item
   */
  public int getExpectedBasePower() {
    return expectedPower;
  }

  /**
   * @return the expected minimum range of the item
   */
  public int getExpectedMinRange() {
    return expectedMinRange;
  }

  /**
   * @return the expected maximum range of the item
   */
  public int getExpectedMaxRange() {
    return expectedMaxRange;
  }

  /**
   * Checks that the Item can be correctly equipped to a unit
   */
  public abstract void equippedToTest();

  /**
   * @return a unit that can equip the item being tested
   */
  public abstract IUnit getTestUnit();
}
