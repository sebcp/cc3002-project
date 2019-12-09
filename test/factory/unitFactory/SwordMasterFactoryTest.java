package factory.unitFactory;

import model.Tactician;
import model.items.Sword;
import model.units.SwordMaster;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SwordMasterFactoryTest extends UnitFactoryTestUnit {
    @Override
    @Test
    public void createDefault() {
        SwordMaster swordMaster = (SwordMaster) swordMasterFactory.create();
        assertEquals(swordMaster.getName(),"SwordMaster");
        assertEquals(swordMaster.getMaxHitPoints(),50);
        assertEquals(swordMaster.getMovement(),2);
        assertNull(swordMaster.getLocation());
    }

    @Override
    @Test
    public void checkPropertyChanges() {
        Tactician tactician = new Tactician("pepito");
        swordMasterFactory.setTactician(tactician);
        swordMasterFactory.setLocation(field.getCell(0,0));
        swordMasterFactory.setMaxHitPoints(10);
        swordMasterFactory.setMovement(3);
        swordMasterFactory.setName("notSwordMaster");
        SwordMaster swordMaster = (SwordMaster) swordMasterFactory.create();
        assertTrue(tactician.getUnits().contains(swordMaster));
        assertEquals(swordMaster.getName(),"notSwordMaster");
        assertEquals(swordMaster.getMaxHitPoints(),10);
        assertEquals(swordMaster.getMovement(),3);
        assertEquals(swordMaster.getLocation(),field.getCell(0,0));

        swordMasterFactory.setLocation(field.getCell(0,0));
        SwordMaster swordMaster2 = (SwordMaster) swordMasterFactory.create();
        assertNull(swordMaster2.getLocation());
    }
}
