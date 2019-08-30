package model.items;

import model.units.IUnit;

/**
 * Abstract class that defines some common information and behaviour between all items.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public abstract class AbstractItem implements IEquipableItem {

  private final String name;
  private final int power;
  protected int maxRange;
  protected int minRange;
  private IUnit owner;

  /**
   * Constructor for a default item without any special behaviour.
   *
   * @param name     the name of the item
   * @param power    the power of the item (this could be the amount of damage or healing the item does)
   * @param minRange the minimum range of the item
   * @param maxRange the maximum range of the item
   */
  public AbstractItem(final String name, final int power, final int minRange, final int maxRange) {
    this.name = name;
    this.power = power;
    this.minRange = Math.max(minRange, 1);
    this.maxRange = Math.max(maxRange, this.minRange);
  }

  @Override
  public IUnit getOwner() {
    return owner;
  }

  public void setOwner(IUnit unit){ this.owner = unit; }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public int getPower() {
    return power;
  }

  @Override
  public int getMinRange() {
    return minRange;
  }

  @Override
  public int getMaxRange() {
    return maxRange;
  }

  @Override
  public void equipToArcher(IUnit unit) {
    System.out.println("Cannot equip " + this.getName() + ", archers can only equip bows." );
  }

  @Override
  public void equipToHero(IUnit unit) {
    System.out.println("Cannot equip " + this.getName() + ", heroes can only equip spears.");
  }

  @Override
  public void equipToSorcerer(IUnit unit){
    System.out.println("Cannot equip " + this.getName() + ", sorcerers can only equip spell books.");
  }

  @Override
  public void equipToCleric(IUnit unit) {
    System.out.println("Cannot equip " + this.getName() + ", clerics can only equip staffs.");
  }

  @Override
  public void equipToSwordMaster(IUnit unit){
    System.out.println("Cannot equip " + this.getName() + ", sword masters can only equip swords.");
  }

  @Override
  public void equipToFighter(IUnit unit) {
    System.out.println("Cannot equip " + this.getName() + ", fighters can only equip axes.");
  }

  public abstract void attack(IEquipableItem item);

  @Override
  public void receiveAttackFromAxe(Axe axe) {
    int damage = axe.getPower();
    this.getOwner().receiveDamage(damage);
  }

  @Override
  public void receiveAttackFromSpear(Spear spear){
    int damage = spear.getPower();
    this.getOwner().receiveDamage(damage);
  }

  @Override
  public void receiveAttackFromSword(Sword sword) {
    int damage = sword.getPower();
    this.getOwner().receiveDamage(damage);
  }

  @Override
  public void receiveAttackFromBow(Bow bow){
    int damage = bow.getPower();
    this.getOwner().receiveDamage(damage);
  }

  @Override
  public void receiveAttackFromAnimaSpellBook(AnimaSpellBook animaSpellBook) {
    int damage = (int) Math.round(animaSpellBook.getPower()*1.5);
    this.getOwner().receiveDamage(damage);
  }

  @Override
  public void receiveAttackFromOscuridadSpellBook(OscuridadSpellBook oscuridadSpellBook) {
    int damage = (int) Math.round(oscuridadSpellBook.getPower()*1.5);
    this.getOwner().receiveDamage(damage);
  }

  @Override
  public void receiveAttackFromLuzSpellBook(LuzSpellBook luzSpellBook) {
    int damage = (int) Math.round(luzSpellBook.getPower()*1.5);
    this.getOwner().receiveDamage(damage);
  }
}