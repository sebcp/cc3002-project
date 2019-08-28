package model.units;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

/**
 * Test set for the sorcerer unit.
 * @author Sebastián Contreras Phillippi
 */
public class SorcererTest extends AbstractTestUnit {
    private Sorcerer sorcerer;

    @Override
    public void setTestUnit() { sorcerer = new Sorcerer(50,2,field.getCell(0, 0)); }

    @Override
    public IUnit getTestUnit() { return sorcerer; }

    /**
     * Checks if the spell book is equipped correctly to the unit
     */
    public void equipSpellBook(){
        assertNull(sorcerer.getEquippedItem());
        sorcerer.equipItem(spellbook);
        assertEquals(spellbook,sorcerer.getEquippedItem());
    }
}
