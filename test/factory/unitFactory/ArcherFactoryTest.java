package factory.unitFactory;

import tactician.Tactician;
import model.units.Archer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArcherFactoryTest extends UnitFactoryTestUnit {

    @Override
    @Test
    public void createDefault() {
        Archer archer = (Archer) archerFactory.create();
        assertEquals(archer.getName(),"Archer");
        assertEquals(archer.getMaxHitPoints(),50);
        assertEquals(archer.getMovement(),2);
        assertNull(archer.getLocation());
    }

    @Override
    @Test
    public void checkPropertyChanges() {
        Tactician tactician = new Tactician("pepito");
        archerFactory.setTactician(tactician);
        archerFactory.setLocation(field.getCell(0,0));
        archerFactory.setMaxHitPoints(10);
        archerFactory.setMovement(3);
        archerFactory.setName("notArcher");
        Archer archer = (Archer) archerFactory.create();
        assertTrue(tactician.getUnits().contains(archer));
        assertEquals(archer.getName(),"notArcher");
        assertEquals(archer.getMaxHitPoints(),10);
        assertEquals(archer.getMovement(),3);
        assertEquals(archer.getLocation(),field.getCell(0,0));

        archerFactory.setLocation(field.getCell(0,0));
        Archer archer2 = (Archer) archerFactory.create();
        assertNull(archer2.getLocation());
    }
}
