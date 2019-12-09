package factory.itemFactory;

import model.units.Alpaca;
import org.junit.jupiter.api.Test;

import model.items.Axe;

import static org.junit.jupiter.api.Assertions.*;

public class AxeFactoryTest extends ItemFactoryTestUnit{
    @Override
    @Test
    public void createDefault() {
        Axe axe = (Axe) axeFactory.create();
        assertEquals(axe.getName(),"Common Axe");
        assertEquals(axe.getPower(),10);
        assertEquals(axe.getMinRange(),1);
        assertEquals(axe.getMaxRange(),2);
        assertNull(axe.getOwner());

    }

    @Override
    @Test
    public void checkPropertyChange() {
        Alpaca alpaca = new Alpaca(50,5,null,"Alpaca");
        axeFactory.setOwner(alpaca);
        axeFactory.setName("Axe");
        axeFactory.setMaxRange(3);
        axeFactory.setMinRange(2);
        axeFactory.setPower(20);
        Axe axe = (Axe) axeFactory.create();
        assertTrue(alpaca.getItems().contains(axe));
        assertEquals(axe.getName(),"Axe");
        assertEquals(axe.getPower(),20);
        assertEquals(axe.getMinRange(),2);
        assertEquals(axe.getMaxRange(),3);
    }
}
