package factory.itemFactory;

import model.items.Spear;
import model.units.Alpaca;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SpearFactoryTest extends ItemFactoryTestUnit {
    @Override
    @Test
    public void createDefault() {
        Spear spear = (Spear) spearFactory.create();
        assertEquals(spear.getName(),"Common Spear");
        assertEquals(spear.getPower(),10);
        assertEquals(spear.getMinRange(),1);
        assertEquals(spear.getMaxRange(),2);
        assertNull(spear.getOwner());
    }

    @Override
    @Test
    public void checkPropertyChange() {
        Alpaca alpaca = new Alpaca(50,5,null,"Alpaca");
        spearFactory.setOwner(alpaca);
        spearFactory.setName("Spear");
        spearFactory.setMaxRange(3);
        spearFactory.setMinRange(2);
        spearFactory.setPower(20);
        Spear spear = (Spear) spearFactory.create();
        assertTrue(alpaca.getItems().contains(spear));
        assertEquals(spear.getName(),"Spear");
        assertEquals(spear.getPower(),20);
        assertEquals(spear.getMinRange(),2);
        assertEquals(spear.getMaxRange(),3);
    }
}
