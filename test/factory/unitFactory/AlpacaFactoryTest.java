package factory.unitFactory;

import tactician.Tactician;
import model.units.Alpaca;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AlpacaFactoryTest extends UnitFactoryTestUnit {
    @Override
    @Test
    public void createDefault() {
        Alpaca alpaca = (Alpaca) alpacaFactory.create();
        assertEquals(alpaca.getName(),"Alpaca");
        assertEquals(alpaca.getMaxHitPoints(),50);
        assertEquals(alpaca.getMovement(),2);
        assertNull(alpaca.getLocation());
    }

    @Override
    @Test
    public void checkPropertyChanges() {
        Tactician tactician = new Tactician("pepito");
        alpacaFactory.setTactician(tactician);
        alpacaFactory.setLocation(field.getCell(0,0));
        alpacaFactory.setMaxHitPoints(10);
        alpacaFactory.setMovement(3);
        alpacaFactory.setName("notAlpaca");
        Alpaca alpaca = (Alpaca) alpacaFactory.create();
        assertTrue(tactician.getUnits().contains(alpaca));
        assertEquals(alpaca.getName(),"notAlpaca");
        assertEquals(alpaca.getMaxHitPoints(),10);
        assertEquals(alpaca.getMovement(),3);
        assertEquals(alpaca.getLocation(),field.getCell(0,0));

        alpacaFactory.setLocation(field.getCell(0,0));
        Alpaca alpaca2 = (Alpaca) alpacaFactory.create();
        assertNull(alpaca2.getLocation());
    }
}
