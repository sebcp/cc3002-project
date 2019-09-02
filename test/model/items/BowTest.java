package model.items;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import model.map.Location;
import model.units.Archer;
import model.units.IUnit;
import org.junit.jupiter.api.Test;

/**
 * Test set for bows
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class BowTest extends AbstractTestItem {

  private Bow bow;
  private Bow wrongBow;
  private Archer archer;

  /**
   * Sets up a correctly implemented item that's going to be tested.
   */
  @Override
  public void setTestItem() {
    expectedName = "Common bow";
    expectedPower = 8;
    expectedMinRange = 2;
    expectedMaxRange = 4;
    bow = new Bow(expectedName, expectedPower, expectedMinRange, expectedMaxRange);
  }

  /**
   * Sets up an item with wrong ranges setted.
   */
  @Override
  public void setWrongRangeItem() {
    wrongBow = new Bow("Wrong bow", 10, 1, 1);
  }

  /**
   * Sets the unit that will be equipped with the test item
   */
  @Override
  public void setTestUnit() {
    archer = new Archer(50, 5, new Location(0, 0),"Archer");
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
  public IEquipableItem getWrongTestItem() {
    return wrongBow;
  }

  @Test
  @Override
  public void checkWeakness(){
    //Bows don't have weaknesses
  }

  @Test
  @Override
  public void checkResistance(){
    //Bows don't have resistances
    getTestUnit().addItem(getTestItem());
    getTestUnit().equipItem(getTestItem());
    Axe axe = new Axe("Axe",5,1,2);
    Spear spear = new Spear("Spear",5,1,2);
    Sword sword = new Sword("Sword",5,1,2);
    getTestItem().receiveAttackFromAxe(axe);
    assertEquals(45,getTestUnit().getCurrentHitPoints());
    getTestItem().receiveAttackFromSword(sword);
    assertEquals(40,getTestUnit().getCurrentHitPoints());
    getTestItem().receiveAttackFromSpear(spear);
    assertEquals(35,getTestUnit().getCurrentHitPoints());
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
    return bow;
  }

  @Test
  @Override
  public void equippedToTest() {
    getTestItem().equipToArcher(getTestUnit());
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
    assertEquals(42,cleric.getCurrentHitPoints());
  }

  /**
   * @return a unit that can equip the item being tested
   */
  @Override
  public IUnit getTestUnit() {
    return archer;
  }
}
