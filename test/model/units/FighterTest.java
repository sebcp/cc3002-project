package model.units;

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
    fighter.addItem(axe);
    fighter.equipItem(axe);
    assertTrue(fighter.getItems().contains(axe));
    assertEquals(axe, fighter.getEquippedItem());
  }

  @Test
  @Override
  public void checkCombat() {
    fighter.addItem(axe);
    fighter.equipItem(axe);
    fighter.combat(getTargetAlpaca());
    assertEquals(0,getTargetAlpaca().getCurrentHitPoints());
    assertFalse(getTargetAlpaca().getIsAlive());
  }

}