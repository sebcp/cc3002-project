package model.units;

import model.items.Bow;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test set for the sorcerer unit.
 * @author Sebastián Contreras Phillippi
 */
public class SorcererTest extends AbstractTestUnit {
    private Sorcerer sorcerer;

    @Override
    public void setTestUnit() { sorcerer = new Sorcerer(50,2,field.getCell(0, 0),"Sorcerer"); }

    @Override
    public IUnit getTestUnit() { return sorcerer; }

    /**
     * Checks if the spell book is equipped correctly to the unit
     */
    @Test
    @Override
    public void equipLuzSpellBook(){
        assertNull(sorcerer.getEquippedItem());
        sorcerer.equipItem(luz);
        assertEquals(luz,sorcerer.getEquippedItem());
    }

    @Test
    @Override
    public void equipOscuridadSpellBook(){
        assertNull(sorcerer.getEquippedItem());
        sorcerer.equipItem(oscuridad);
        assertEquals(oscuridad,sorcerer.getEquippedItem());
    }

    @Test
    @Override
    public void equipAnimaSpellBook(){
        assertNull(sorcerer.getEquippedItem());
        sorcerer.equipItem(anima);
        assertEquals(anima,sorcerer.getEquippedItem());
    }

    @Test
    public void combatTest(){
        sorcerer.equipItem(luz);
        assertEquals(luz, sorcerer.getEquippedItem());

        sorcerer.combat(getTargetAlpaca());
        assertEquals(50,getTargetAlpaca().getCurrentHitPoints());

        getTargetAlpaca().moveTo(field.getCell(1,1));
        sorcerer.combat(getTargetAlpaca());
        assertEquals(0,getTargetAlpaca().getCurrentHitPoints());
        assertFalse(getTargetAlpaca().getIsAlive());
    }

    @Test
    @Override
    public void checkCombat() {
        sorcerer.equipItem(luz);
        getTargetAlpaca().moveTo(field.getCell(1,1));
        sorcerer.combat(getTargetAlpaca());
        assertEquals(0,getTargetAlpaca().getCurrentHitPoints());
        assertFalse(getTargetAlpaca().getIsAlive());

        Bow bow = new Bow("Bow",10,2,3);
        Archer archer = new Archer(50,2,field.getCell(1,1),
                "Archer");
        archer.equipItem(bow);
        archer.combat(sorcerer);
        assertEquals(0,archer.getCurrentHitPoints());
        assertFalse(archer.getIsAlive());
        assertTrue(sorcerer.getIsAlive());
    }
}
