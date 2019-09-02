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
        sorcerer.addItem(luz);
        sorcerer.equipItem(luz);
        assertTrue(sorcerer.getItems().contains(luz));
        assertEquals(luz,sorcerer.getEquippedItem());
    }

    @Test
    @Override
    public void equipOscuridadSpellBook(){
        assertNull(sorcerer.getEquippedItem());
        sorcerer.addItem(oscuridad);
        sorcerer.equipItem(oscuridad);
        assertEquals(oscuridad,sorcerer.getEquippedItem());
    }

    @Test
    @Override
    public void equipAnimaSpellBook(){
        assertNull(sorcerer.getEquippedItem());
        sorcerer.addItem(anima);
        sorcerer.equipItem(anima);
        assertTrue(sorcerer.getItems().contains(anima));
        assertEquals(anima,sorcerer.getEquippedItem());
    }

    @Test
    @Override
    public void checkCombat() {
        sorcerer.addItem(luz);
        sorcerer.equipItem(luz);
        sorcerer.combat(getTargetAlpaca());
        assertEquals(50,getTargetAlpaca().getCurrentHitPoints());
        getTargetAlpaca().moveTo(field.getCell(1,1));
        sorcerer.combat(getTargetAlpaca());
        assertEquals(0,getTargetAlpaca().getCurrentHitPoints());
        assertFalse(getTargetAlpaca().getIsAlive());

        Bow bow = new Bow("Bow",10,2,3);
        Archer archer = new Archer(50,2,field.getCell(1,1),
                "Archer");
        archer.addItem(bow);
        archer.equipItem(bow);
        archer.combat(sorcerer);
        assertEquals(0,sorcerer.getCurrentHitPoints());
        assertFalse(sorcerer.getIsAlive());
        assertTrue(archer.getIsAlive());
    }
}
