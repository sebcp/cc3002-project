package model.units;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertFalse;

import model.items.Sword;
import org.junit.jupiter.api.Test;

/**
 * Test set for the Archer unit.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class ArcherTest extends AbstractTestUnit {

  private Archer archer;

  /**
   * Set up the main unit that's going to be tested in the test set
   */
  @Override
  public void setTestUnit() {
    archer = new Archer(50, 2, field.getCell(0, 0),"Archer");
  }

  /**
   * @return the current unit being tested
   */
  @Override
  public IUnit getTestUnit() {
    return archer;
  }

  /**
   * Checks if the bow is equipped correctly to the unit
   */
  @Test
  @Override
  public void equipBowTest() {
    assertNull(archer.getEquippedItem());
    archer.equipItem(bow);
    assertEquals(bow, archer.getEquippedItem());
  }

  @Test
  public void checkCombat(){
    archer.equipItem(bow);
    assertEquals(bow, archer.getEquippedItem());

    archer.combat(getTargetAlpaca());
    assertEquals(50,getTargetAlpaca().getCurrentHitPoints());

    getTargetAlpaca().moveTo(field.getCell(1,1));
    archer.combat(getTargetAlpaca());
    assertEquals(0,getTargetAlpaca().getCurrentHitPoints());
    assertFalse(getTargetAlpaca().getIsAlive());

    Sword sword = new Sword("Sword",10,1,1);
    SwordMaster swordmaster = new SwordMaster(50,2,field.getCell(0,1),"Swordmaster");
    swordmaster.equipItem(sword);
    swordmaster.combat(archer);
    assertEquals(0,archer.getCurrentHitPoints());
    assertFalse(archer.getIsAlive());
  }
}