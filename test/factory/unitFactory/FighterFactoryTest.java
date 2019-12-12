package factory.unitFactory;

import tactician.Tactician;
import model.units.Fighter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FighterFactoryTest extends UnitFactoryTestUnit {
    @Override
    @Test
    public void createDefault() {
        Fighter fighter = (Fighter) fighterFactory.create();
        assertEquals(fighter.getName(),"Fighter");
        assertEquals(fighter.getMaxHitPoints(),50);
        assertEquals(fighter.getMovement(),2);
        assertNull(fighter.getLocation());
    }

    @Override
    @Test
    public void checkPropertyChanges() {
        Tactician tactician = new Tactician("pepito");
        fighterFactory.setTactician(tactician);
        fighterFactory.setLocation(field.getCell(0,0));
        fighterFactory.setMaxHitPoints(10);
        fighterFactory.setMovement(3);
        fighterFactory.setName("notCleric");
        Fighter fighter = (Fighter) fighterFactory.create();
        assertTrue(tactician.getUnits().contains(fighter));
        assertEquals(fighter.getName(),"notCleric");
        assertEquals(fighter.getMaxHitPoints(),10);
        assertEquals(fighter.getMovement(),3);
        assertEquals(fighter.getLocation(),field.getCell(0,0));

        fighterFactory.setLocation(field.getCell(0,0));
        Fighter fighter2 = (Fighter) fighterFactory.create();
        assertNull(fighter2.getLocation());
    }
}
