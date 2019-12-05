package factory.unitFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import model.units.Alpaca;
import org.junit.jupiter.api.Test;

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
        alpacaFactory.setLocation(field.getCell(0,0));
        alpacaFactory.setMaxHitPoints(10);
        alpacaFactory.setMovement(3);
        alpacaFactory.setName("notAlpaca");
        Alpaca alpaca = (Alpaca) alpacaFactory.create();
        assertEquals(alpaca.getName(),"notAlpaca");
        assertEquals(alpaca.getMaxHitPoints(),10);
        assertEquals(alpaca.getMovement(),3);
        assertEquals(alpaca.getLocation(),field.getCell(0,0));

        alpacaFactory.setLocation(field.getCell(0,0));
        Alpaca alpaca2 = (Alpaca) alpacaFactory.create();
        assertNull(alpaca2.getLocation());
    }
}
