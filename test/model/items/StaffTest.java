package model.items;

import model.map.Location;
import model.units.Cleric;
import model.units.IUnit;
import model.units.SwordMaster;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test set for staffs
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class StaffTest extends AbstractTestItem {

  private Staff staff;
  private Staff wrongStaff;
  private Cleric cleric;

  /**
   * Sets which item is going to be tested
   */
  @Override
  public void setTestItem() {
    expectedName = "Common staff";
    expectedPower = 5;
    expectedMinRange = 1;
    expectedMaxRange = 2;
    staff = new Staff(expectedName, expectedPower, expectedMinRange, expectedMaxRange);
  }

  /**
   * Sets up an item with wrong ranges setted.
   */
  @Override
  public void setWrongRangeItem() {
    wrongStaff = new Staff("Wrong staff", 0, -1, -2);
  }

  /**
   * Sets the unit that will be equipped with the test item
   */
  @Override
  public void setTestUnit() {
    cleric = new Cleric(50, 5, new Location(0, 0),"Cleric");
  }

  @Override
  public IEquipableItem getWrongTestItem() {
    return wrongStaff;
  }

  /**
   * @return the item being tested
   */
  @Override
  public IEquipableItem getTestItem() {
    return staff;
  }

  @Test
  @Override
  public void checkWeakness(){
    //Staffs don't have weaknesses
  }

  @Test
  @Override
  public void checkResistance(){
    //Staffs don't have resistances
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
    getTestItem().equipToCleric(getTestUnit());
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
    assertEquals(cleric.getMaxHitPoints(),cleric.getCurrentHitPoints());
  }

  /**
   * @return a unit that can equip the item being tested
   */
  @Override
  public IUnit getTestUnit() {
    return cleric;
  }
}
