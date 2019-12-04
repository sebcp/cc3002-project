package factory.itemFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import model.items.Axe;

public class AxeFactoryTest extends ItemFactoryTestUnit{
    @Override
    @Test
    public void createDefault() {
        Axe axe = (Axe) axeFactory.create();
        assertEquals(axe.getName(),"Common Axe");
        assertEquals(axe.getPower(),10);
        assertEquals(axe.getMinRange(),1);
        assertEquals(axe.getMaxRange(),2);
    }

    @Override
    @Test
    public void checkPropertyChange() {
        axeFactory.setName("Axe");
        axeFactory.setMaxRange(3);
        axeFactory.setMinRange(2);
        axeFactory.setPower(20);
        Axe axe = (Axe) axeFactory.create();
        assertEquals(axe.getName(),"Axe");
        assertEquals(axe.getPower(),20);
        assertEquals(axe.getMinRange(),2);
        assertEquals(axe.getMaxRange(),3);
    }
}
