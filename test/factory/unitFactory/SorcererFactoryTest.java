package factory.unitFactory;

import model.units.Sorcerer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class SorcererFactoryTest extends UnitFactoryTestUnit{
    @Override
    @Test
    public void createDefault() {
        Sorcerer sorcerer = (Sorcerer) sorcererFactory.create();
        assertEquals(sorcerer.getName(),"Sorcerer");
        assertEquals(sorcerer.getMaxHitPoints(),50);
        assertEquals(sorcerer.getMovement(),2);
        assertNull(sorcerer.getLocation());
    }

    @Override
    @Test
    public void checkPropertyChanges() {
        sorcererFactory.setLocation(field.getCell(0,0));
        sorcererFactory.setMaxHitPoints(10);
        sorcererFactory.setMovement(3);
        sorcererFactory.setName("notSorcerer");
        Sorcerer sorcerer = (Sorcerer) sorcererFactory.create();
        assertEquals(sorcerer.getName(),"notSorcerer");
        assertEquals(sorcerer.getMaxHitPoints(),10);
        assertEquals(sorcerer.getMovement(),3);
        assertEquals(sorcerer.getLocation(),field.getCell(0,0));

        sorcererFactory.setLocation(field.getCell(0,0));
        Sorcerer sorcerer2 = (Sorcerer) sorcererFactory.create();
        assertNull(sorcerer2.getLocation());
    }
}
