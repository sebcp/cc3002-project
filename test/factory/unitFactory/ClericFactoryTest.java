package factory.unitFactory;

import model.Tactician;
import model.units.Cleric;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ClericFactoryTest extends UnitFactoryTestUnit {
    @Override
    @Test
    public void createDefault() {
        Cleric cleric = (Cleric) clericFactory.create();
        assertEquals(cleric.getName(),"Cleric");
        assertEquals(cleric.getMaxHitPoints(),50);
        assertEquals(cleric.getMovement(),2);
        assertNull(cleric.getLocation());
    }

    @Override
    @Test
    public void checkPropertyChanges() {
        Tactician tactician = new Tactician("pepito");
        clericFactory.setTactician(tactician);
        clericFactory.setLocation(field.getCell(0,0));
        clericFactory.setMaxHitPoints(10);
        clericFactory.setMovement(3);
        clericFactory.setName("notCleric");
        Cleric cleric = (Cleric) clericFactory.create();
        assertTrue(tactician.getUnits().contains(cleric));
        assertEquals(cleric.getName(),"notCleric");
        assertEquals(cleric.getMaxHitPoints(),10);
        assertEquals(cleric.getMovement(),3);
        assertEquals(cleric.getLocation(),field.getCell(0,0));

        clericFactory.setLocation(field.getCell(0,0));
        Cleric cleric2 = (Cleric) clericFactory.create();
        assertNull(cleric2.getLocation());
    }
}
