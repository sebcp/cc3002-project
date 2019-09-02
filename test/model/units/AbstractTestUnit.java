package model.units;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import model.items.*;
import model.map.Field;
import model.map.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public abstract class AbstractTestUnit implements ITestUnit {

  protected Alpaca targetAlpaca;
  protected Cleric testCleric;

  protected Bow bow;
  protected Field field;
  protected Axe axe;
  protected Sword sword;
  protected Staff staff;
  protected Spear spear;
  protected AnimaSpellBook anima;
  protected OscuridadSpellBook oscuridad;
  protected LuzSpellBook luz;

  @Override
  public void setTargetAlpaca() {
    targetAlpaca = new Alpaca(50, 2, field.getCell(1, 0),"Alpaca");
  }

  /**
   * Set up the game field
   */
  @Override
  public void setField() {
    this.field = new Field();
    this.field.addCells(true, new Location(0, 0), new Location(0, 1), new Location(0, 2),
            new Location(1, 0), new Location(1, 1), new Location(1, 2), new Location(2, 0),
            new Location(2, 1), new Location(2, 2));
  }

  /**
   * Set up the main unit that's going to be tested in the test set
   */
  @Override
  public abstract void setTestUnit();

  /**
   * Creates a set of testing weapons
   */
  @Override
  public void setWeapons() {
    this.axe = new Axe("Axe", 10, 1, 2);
    this.sword = new Sword("Sword", 10, 1, 2);
    this.spear = new Spear("Spear", 10, 1, 2);
    this.staff = new Staff("Staff", 10, 1, 2);
    this.bow = new Bow("Bow", 10, 2, 3);
    this.anima = new AnimaSpellBook("AnimaSpellBook",10,2,3);
    this.oscuridad = new OscuridadSpellBook("OscuridadSpellBook",10,2,3);
    this.luz = new LuzSpellBook("LuzSpellBook",10,2,3);
  }

  public void setTestCleric(){
    testCleric = new Cleric(50,2,field.getCell(1,0),
          "TestCleric");
    Staff testStaff = new Staff("TestStaff",10,1,2);
    testCleric.addItem(testStaff);
    testCleric.equipItem(testStaff);
  }
  /**
   * Sets up the units and weapons to be tested
   */

  @BeforeEach
  public void setUp() {
    setField();
    setTestUnit();
    setTargetAlpaca();
    setWeapons();
    setTestCleric();
  }

  /**
   * Checks that the constructor works properly.
   */
  @Override
  @Test
  public void constructorTest() {
    assertEquals(50, getTestUnit().getCurrentHitPoints());
    assertEquals(50, getTestUnit().getMaxHitPoints());
    assertEquals(2, getTestUnit().getMovement());
    assertEquals(new Location(0, 0), getTestUnit().getLocation());
    assertTrue(getTestUnit().getItems().isEmpty());
    assertEquals(getTestUnit(), getTestUnit());
  }

  /**
   * @return the current unit being tested
   */
  @Override
  public abstract IUnit getTestUnit();

  /**
   * Checks if the axe is equipped correctly to the unit
   */
  @Override
  @Test
  public void equipAxeTest() {
    assertNull(getTestUnit().getEquippedItem());
    checkEquippedItem(getAxe());
  }

  /**
   * Tries to equip a weapon to the alpaca and verifies that it was not equipped
   *
   * @param item
   *     to be equipped
   */
  @Override
  public void checkEquippedItem(IEquipableItem item) {
    assertNull(getTestUnit().getEquippedItem());
    getTestUnit().addItem(item);
    getTestUnit().equipItem(item);
    assertNull(getTestUnit().getEquippedItem());
  }

  @Test
  public void checkCombatBorderCases(){
    SwordMaster test = new SwordMaster(50,2,field.getCell(0,1),"test");
    Sword testSword = new Sword("testSword",10,1,2);
    test.addItem(testSword);
    test.equipItem(testSword);
    test.receiveDamage(50);
    test.combat(getTestUnit());
    assertEquals(getTestUnit().getMaxHitPoints(),getTestUnit().getCurrentHitPoints());
  }

  /**
   * Tries to give an item from the inventory of the test unit to the alpaca
   * and verifies whether it has been given or not, then tries to give an item
   * to a unit with a full inventory and then tries to give an item to a dead unit.
   */
  @Override
  @Test
  public void checkGivenItem(){
    Sword exchangedSword = new Sword("exchangedSword",5,1,2);
    getTestUnit().addItem(exchangedSword);
    getTestUnit().giveItem(0, getTargetAlpaca());
    assertEquals(exchangedSword.getOwner(),getTargetAlpaca());
    assertEquals(exchangedSword, getTargetAlpaca().getItems().get(0));

    Axe axe = new Axe("Axe",10,1,2);
    Spear spear = new Spear("Spear",10,1,2);
    Sword sword = new Sword("Sword",10,1,2);
    getTestUnit().addItem(axe);
    getTestUnit().addItem(spear);
    getTestUnit().addItem(sword);
    getTargetAlpaca().giveItem(0,getTestUnit());
    assertEquals(exchangedSword,getTargetAlpaca().getItems().get(0));
    getTargetAlpaca().receiveDamage(50);
    getTestUnit().giveItem(0,getTargetAlpaca());
    assertTrue(getTestUnit().getItems().contains(axe));
  }

  public abstract void checkCombat();

  @Test
  public void checkHealing(){
    getTestUnit().receiveDamage(10);
    assertEquals(getTestUnit().getMaxHitPoints()-10,getTestUnit().getCurrentHitPoints());
    testCleric.heal(getTestUnit());
    assertEquals(getTestUnit().getMaxHitPoints(),getTestUnit().getCurrentHitPoints());
    getTestUnit().receiveDamage(15);
    testCleric.heal(getTestUnit());
    assertEquals(getTestUnit().getMaxHitPoints()-5,getTestUnit().getCurrentHitPoints());
    testCleric.heal(getTestUnit());
    assertEquals(getTestUnit().getMaxHitPoints(),getTestUnit().getCurrentHitPoints());
    getTestUnit().receiveDamage(50);
    testCleric.heal(getTestUnit());
    assertEquals(0,getTestUnit().getCurrentHitPoints());
  }

  /**
   * @return the test axe
   */
  @Override
  public Axe getAxe() {
    return axe;
  }

  @Override
  @Test
  public void equipSwordTest() {
    checkEquippedItem(getSword());
  }

  /**
   * @return the test sword
   */
  @Override
  public Sword getSword() {
    return sword;
  }

  @Override
  @Test
  public void equipSpearTest() {
    checkEquippedItem(getSpear());
  }

  /**
   * @return the test spear
   */
  @Override
  public Spear getSpear() {
    return spear;
  }

  @Override
  @Test
  public void equipStaffTest() {
    checkEquippedItem(getStaff());
  }

  /**
   * @return the test staff
   */
  @Override
  public Staff getStaff() {
    return staff;
  }

  @Override
  @Test
  public void equipBowTest() {
    checkEquippedItem(getBow());
  }

  /**
   * @return the test bow
   */
  @Override
  public Bow getBow() { return bow; }

  @Override
  @Test
  public void equipAnimaSpellBook() { checkEquippedItem(getAnimaSpellBook()); }

  /**
   * @return the test spell book
   */
  @Override
  public AnimaSpellBook getAnimaSpellBook(){
    return anima;
  }

  @Override
  @Test
  public void equipOscuridadSpellBook(){ checkEquippedItem(getOscuridadSpellBook());}

  @Override
  public OscuridadSpellBook getOscuridadSpellBook(){ return oscuridad; }

  @Override
  @Test
  public void equipLuzSpellBook(){ checkEquippedItem(getLuzSpellBook()); }

  @Override
  public LuzSpellBook getLuzSpellBook(){ return luz; }

  /**
   * Checks if the unit moves correctly
   */
  @Override
  @Test
  public void testMovement() {
    getTestUnit().moveTo(getField().getCell(2, 2));
    assertEquals(new Location(0, 0), getTestUnit().getLocation());

    getTestUnit().moveTo(getField().getCell(0, 2));
    assertEquals(new Location(0, 2), getTestUnit().getLocation());

    getField().getCell(0, 1).setUnit(getTargetAlpaca());
    getTestUnit().moveTo(getField().getCell(0, 1));
    assertEquals(new Location(0, 2), getTestUnit().getLocation());
  }

  /**
   * @return the test field
   */
  @Override
  public Field getField() {
    return field;
  }

  /**
   * @return the target Alpaca
   */
  @Override
  public Alpaca getTargetAlpaca() {
    return targetAlpaca;
  }
}
