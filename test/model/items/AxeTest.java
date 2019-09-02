package model.items;

import model.map.Location;
import model.units.Fighter;
import model.units.IUnit;
import model.units.Sorcerer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test set for Axes
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
class AxeTest extends AbstractTestItem {

  private Axe axe;
  private Axe wrongAxe;
  private Fighter fighter;

  /**
   * Sets up a correctly implemented item that's going to be tested.
   */
  @Override
  public void setTestItem() {
    expectedName = "Common axe";
    expectedPower = 10;
    expectedMinRange = 1;
    expectedMaxRange = 2;
    axe = new Axe(expectedName, expectedPower, expectedMinRange, expectedMaxRange);
  }

  /**
   * Sets up an item with wrong ranges setted.
   */
  @Override
  public void setWrongRangeItem() {
    wrongAxe = new Axe("Wrong axe", 0, -1, -2);
  }

  /**
   * Sets the unit that will be equipped with the test item
   */
  @Override
  public void setTestUnit() {
    fighter = new Fighter(50, 5, new Location(0, 0),"Fighter");
  }

  /**
   * @return the item with wrong range setted.
   */
  @Override
  public IEquipableItem getWrongTestItem() {
    return wrongAxe;
  }

  @Test
  @Override
  public void checkWeakness() {
    getTestUnit().addItem(getTestItem());
    getTestUnit().equipItem(getTestItem());
    assertEquals(getTestItem(),getTestUnit().getEquippedItem());

    Sword item = new Sword("test",10,2,3);
    getTestItem().receiveAttackFromSword(item);
    assertEquals(35,getTestUnit().getCurrentHitPoints());
  }

  @Test
  @Override
  public void checkResistance(){
    getTestUnit().addItem(getTestItem());
    getTestUnit().equipItem(getTestItem());
    assertEquals(getTestItem(),getTestUnit().getEquippedItem());

    Spear test = new Spear("test",10,2,3);
    getTestItem().receiveAttackFromSpear(test);
    assertEquals(getTestUnit().getMaxHitPoints(),getTestUnit().getCurrentHitPoints());
  }

  @Test
  public void receiveMagicAttack(){
    AnimaSpellBook item = new AnimaSpellBook("item",10,2,3);
    getTestUnit().addItem(getTestItem());
    getTestUnit().equipItem(getTestItem());
    getTestItem().receiveAttackFromAnimaSpellBook(item);
    assertEquals(35,getTestUnit().getCurrentHitPoints());
    OscuridadSpellBook item1 = new OscuridadSpellBook("item",10,2,3);
    getTestItem().receiveAttackFromOscuridadSpellBook(item1);
    assertEquals(20,getTestUnit().getCurrentHitPoints());
    LuzSpellBook item2 = new LuzSpellBook("item",10,2,3);
    getTestItem().receiveAttackFromLuzSpellBook(item2);
    assertEquals(5,getTestUnit().getCurrentHitPoints());
  }

  /**
   * @return the item being tested.
   */
  @Override
  public IEquipableItem getTestItem() {
    return axe;
  }

  @Test
  @Override
  public void equippedToTest() {
    getTestItem().equipToFighter(getTestUnit());
    assertEquals(getTestItem(),getTestUnit().getEquippedItem());
  }



  /**
   * @return a unit that can equip the item being tested
   */
  @Override
  public IUnit getTestUnit() {
    return fighter;
  }
}