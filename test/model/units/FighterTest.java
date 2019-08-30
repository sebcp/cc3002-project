package model.units;

import model.items.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Ignacio Slater Muñoz
 */
public class FighterTest extends AbstractTestUnit {

  private Fighter fighter;

  /**
   * Set up the main unit that's going to be tested in the test set
   */
  @Override
  public void setTestUnit() {
    fighter = new Fighter(50, 2, field.getCell(0, 0),"Fighter");
  }

  /**
   * @return the current unit being tested
   */
  @Override
  public IUnit getTestUnit() {
    return fighter;
  }

  /**
   * Checks if the axe is equipped correctly to the unit
   */
  @Test
  @Override
  public void equipAxeTest() {
    assertNull(fighter.getEquippedItem());
    fighter.equipItem(axe);
    assertEquals(axe, fighter.getEquippedItem());
  }

  @Test
  @Override
  public void checkCombat() {
    fighter.equipItem(axe);
    fighter.combat(getTargetAlpaca());
    assertEquals(0,getTargetAlpaca().getCurrentHitPoints());
    assertFalse(getTargetAlpaca().getIsAlive());

    Bow bow = new Bow("Bow",10,2,3);
    Archer archer = new Archer(50,2,field.getCell(1,1),
            "Archer");
    archer.equipItem(bow);
    archer.combat(fighter);
    assertEquals(0,fighter.getCurrentHitPoints());
    assertFalse(fighter.getIsAlive());
  }

}