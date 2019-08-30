package model.units;

import model.items.Bow;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * @author Ignacio Slater Muñoz
 */
public class SwordMasterTest extends AbstractTestUnit {

  private SwordMaster swordMaster;

  /**
   * Set up the main unit that's going to be tested in the test set
   */
  @Override
  public void setTestUnit() {
    swordMaster = new SwordMaster(50, 2, field.getCell(0, 0),"Swordmaster");
  }

  /**
   * @return the current unit being tested
   */
  @Override
  public IUnit getTestUnit() {
    return swordMaster;
  }

  @Override
  public void equipSwordTest() {
    assertNull(swordMaster.getEquippedItem());
    swordMaster.equipItem(sword);
    assertEquals(sword, swordMaster.getEquippedItem());
  }

  @Test
  @Override
  public void checkCombat() {
    swordMaster.equipItem(sword);
    swordMaster.combat(getTargetAlpaca());
    assertEquals(0,getTargetAlpaca().getCurrentHitPoints());
    assertFalse(getTargetAlpaca().getIsAlive());

    Bow bow = new Bow("Bow",10,2,3);
    Archer archer = new Archer(50,2,field.getCell(1,1),
            "Archer");
    archer.equipItem(bow);
    archer.combat(swordMaster);
    assertEquals(0,swordMaster.getCurrentHitPoints());
    assertFalse(swordMaster.getIsAlive());
  }
}