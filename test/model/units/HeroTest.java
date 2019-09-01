package model.units;

import model.items.Bow;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * @author Ignacio Slater Muñoz
 */
public class HeroTest extends AbstractTestUnit {

  private Hero hero;

  /**
   * Set up the main unit that's going to be tested in the test set
   */
  @Override
  public void setTestUnit() {
    hero = new Hero(50, 2, field.getCell(0, 0),"Hero");
  }

  /**
   * @return the current unit being tested
   */
  @Override
  public IUnit getTestUnit() {
    return hero;
  }

  @Override
  @Test
  public void equipSpearTest() {
    assertNull(hero.getEquippedItem());
    hero.addItem(spear);
    hero.equipItem(spear);
    assertTrue(hero.getItems().contains(spear));
    assertEquals(spear, hero.getEquippedItem());
  }

  @Test
  @Override
  public void checkCombat() {
    hero.addItem(spear);
    hero.equipItem(spear);
    hero.combat(getTargetAlpaca());
    assertEquals(0,getTargetAlpaca().getCurrentHitPoints());
    assertFalse(getTargetAlpaca().getIsAlive());

    Bow bow = new Bow("Bow",10,2,3);
    Archer archer = new Archer(50,2,field.getCell(1,1),
            "Archer");
    archer.addItem(bow);
    archer.equipItem(bow);
    archer.combat(hero);
    assertEquals(0,hero.getCurrentHitPoints());
    assertFalse(hero.getIsAlive());
  }
}