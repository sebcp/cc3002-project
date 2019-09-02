package model.items;

import model.map.Location;
import model.units.IUnit;
import model.units.SwordMaster;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test set for swords
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class SwordTest extends AbstractTestItem {

  private Sword sword;
  private Sword wrongSword;
  private SwordMaster swordMaster;

  /**
   * Sets which item is going to be tested
   */
  @Override
  public void setTestItem() {
    expectedName = "Common sword";
    expectedPower = 10;
    expectedMinRange = 1;
    expectedMaxRange = 2;
    sword = new Sword(expectedName, expectedPower, expectedMinRange, expectedMaxRange);
  }

  /**
   * Sets up an item with wrong ranges setted.
   */
  @Override
  public void setWrongRangeItem() {
    wrongSword = new Sword("Wrong sword", 0, -1, -2);
  }

  /**
   * Sets the unit that will be equipped with the test item
   */
  @Override
  public void setTestUnit() {
    swordMaster = new SwordMaster(50, 5, new Location(0, 0),"Swordmaster");
  }

  @Override
  public IEquipableItem getWrongTestItem() {
    return wrongSword;
  }

  /**
   * @return the item being tested
   */
  @Override
  public IEquipableItem getTestItem() {
    return sword;
  }

  @Test
  @Override
  public void checkWeakness() {
    getTestUnit().addItem(getTestItem());
    getTestUnit().equipItem(getTestItem());
    assertEquals(getTestItem(),getTestUnit().getEquippedItem());

    Spear item = new Spear("test",10,2,3);
    getTestItem().receiveAttackFromSpear(item);
    assertEquals(35,getTestUnit().getCurrentHitPoints());
  }

  @Test
  @Override
  public void checkResistance(){
    getTestUnit().addItem(getTestItem());
    getTestUnit().equipItem(getTestItem());
    assertEquals(getTestItem(),getTestUnit().getEquippedItem());

    Axe test = new Axe("test",10,2,3);
    getTestItem().receiveAttackFromAxe(test);
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

  @Test
  @Override
  public void equippedToTest() {
    getTestItem().equipToSwordMaster(getTestUnit());
    assertEquals(getTestItem(),getTestUnit().getEquippedItem());
  }

  /**
   * @return a unit that can equip the item being tested
   */
  @Override
  public IUnit getTestUnit() {
    return swordMaster;
  }
}
