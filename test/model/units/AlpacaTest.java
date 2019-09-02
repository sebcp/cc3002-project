package model.units;

import model.items.Sword;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Test set for the alpaca unit
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class AlpacaTest extends AbstractTestUnit {

  private Alpaca alpaca;

  @Override
  public void setTestUnit() {
    alpaca = new Alpaca(50, 2, field.getCell(0, 0),"Alpaca");
  }

  @Override
  public Alpaca getTestUnit() {
    return alpaca;
  }

  /**
   * Tries to give an item from the inventory of the test unit to the alpaca
   * and verifies whether it has been given or not.
   */
  @Override
  @Test
  public void checkGivenItem(){
    Sword exchangedSword = new Sword("exchangedSword",5,1,2);
    getTestUnit().addItem(exchangedSword);
    getTestUnit().giveItem(0, getTargetAlpaca());
    assertEquals(exchangedSword, getTargetAlpaca().getItems().get(0));
  }

  @Test
  @Override
  public void checkCombat() {
      Sword sword = new Sword("Sword",10,1,1);
      SwordMaster swordmaster = new SwordMaster(50,2,field.getCell(0,1),"Swordmaster");
      swordmaster.addItem(sword);
      swordmaster.equipItem(sword);
      alpaca.combat(swordmaster);
      assertEquals(50,swordmaster.getCurrentHitPoints());
      swordmaster.combat(alpaca);
      assertEquals(0,alpaca.getCurrentHitPoints());
      assertFalse(alpaca.getIsAlive());
  }
}