package factory.itemFactory;

import model.items.Bow;
import model.units.Alpaca;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BowFactoryTest extends ItemFactoryTestUnit {
    @Override
    @Test
    public void createDefault() {
        Bow bow = (Bow) bowFactory.create();
        assertEquals(bow.getName(),"Common Bow");
        assertEquals(bow.getPower(),10);
        assertEquals(bow.getMinRange(),2);
        assertEquals(bow.getMaxRange(),3);
        assertNull(bow.getOwner());
    }

    @Override
    @Test
    public void checkPropertyChange() {
        Alpaca alpaca = new Alpaca(50,5,null,"Alpaca");
        bowFactory.setOwner(alpaca);
        bowFactory.setName("Bow");
        bowFactory.setMaxRange(4);
        bowFactory.setMinRange(3);
        bowFactory.setPower(20);
        Bow bow = (Bow) bowFactory.create();
        assertTrue(alpaca.getItems().contains(bow));
        assertEquals(bow.getName(),"Bow");
        assertEquals(bow.getPower(),20);
        assertEquals(bow.getMinRange(),3);
        assertEquals(bow.getMaxRange(),4);
    }
}
