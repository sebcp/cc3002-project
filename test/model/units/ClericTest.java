package model.units;

import model.items.Sword;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Ignacio Slater Muñoz
 */
public class ClericTest extends AbstractTestUnit {

  private Cleric cleric;

  /**
   * Set up the main unit that's going to be tested in the test set
   */
  @Override
  public void setTestUnit() {
    cleric = new Cleric(50, 2, field.getCell(0, 0),"Cleric");
  }

  /**
   * @return the current unit being tested
   */
  @Override
  public IUnit getTestUnit() {
    return cleric;
  }

  @Test
  @Override
  public void equipStaffTest() {
    assertNull(cleric.getEquippedItem());
    cleric.addItem(staff);
    cleric.equipItem(staff);
    assertTrue(cleric.getItems().contains(staff));
    assertEquals(staff, cleric.getEquippedItem());
  }

  @Test
  @Override
  public void checkCombat() {
    Sword sword = new Sword("Sword",10,1,1);
    SwordMaster swordmaster = new SwordMaster(50,2,field.getCell(0,1),"Swordmaster");
    swordmaster.addItem(sword);
    swordmaster.equipItem(sword);
    cleric.combat(swordmaster);
    assertEquals(50,swordmaster.getCurrentHitPoints());
    swordmaster.combat(cleric);
    assertEquals(0,cleric.getCurrentHitPoints());
    assertFalse(cleric.getIsAlive());
  }
}