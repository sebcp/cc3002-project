package model.items;

import model.map.Location;
import model.units.Hero;
import model.units.IUnit;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test set for spears
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class SpearTest extends AbstractTestItem {

  private Spear javelin;
  private Spear wrongSpear;
  private Hero hero;

  /**
   * Sets which item is going to be tested
   */
  @Override
  public void setTestItem() {
    expectedName = "Javelin";
    expectedPower = 10;
    expectedMinRange = 1;
    expectedMaxRange = 3;
    javelin = new Spear(expectedName, expectedPower, expectedMinRange, expectedMaxRange);
  }

  /**
   * Sets up an item with wrong ranges setted.
   */
  @Override
  public void setWrongRangeItem() {
    wrongSpear = new Spear("Wrong spear", 0, -1, -2);
  }

  /**
   * Sets the unit that will be equipped with the test item
   */
  @Override
  public void setTestUnit() {
    hero = new Hero(50, 5, new Location(0, 0),"Hero");
  }

  @Override
  public IEquipableItem getWrongTestItem() {
    return wrongSpear;
  }

  @Test
  @Override
  public void checkWeakness() {
    getTestUnit().addItem(getTestItem());
    getTestUnit().equipItem(getTestItem());
    assertEquals(getTestItem(),getTestUnit().getEquippedItem());

    Axe item = new Axe("test",10,2,3);
    getTestItem().receiveAttackFromAxe(item);
    assertEquals(35,getTestUnit().getCurrentHitPoints());
  }

  @Test
  @Override
  public void checkResistance(){
    getTestUnit().addItem(getTestItem());
    getTestUnit().equipItem(getTestItem());
    assertEquals(getTestItem(),getTestUnit().getEquippedItem());

    Sword test = new Sword("test",10,2,3);
    getTestItem().receiveAttackFromSword(test);
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
   * @return the item being tested
   */
  @Override
  public IEquipableItem getTestItem() {
    return javelin;
  }

  @Test
  @Override
  public void equippedToTest() {
    getTestItem().equipToHero(getTestUnit());
    assertEquals(getTestItem(),getTestUnit().getEquippedItem());
  }

  /**
   * @return a unit that can equip the item being tested
   */
  @Override
  public IUnit getTestUnit() {
    return hero;
  }
}
