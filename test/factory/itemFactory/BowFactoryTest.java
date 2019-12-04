package factory.itemFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import model.items.Bow;
import org.junit.jupiter.api.Test;

public class BowFactoryTest extends ItemFactoryTestUnit {
    @Override
    @Test
    public void createDefault() {
        Bow bow = (Bow) bowFactory.create();
        assertEquals(bow.getName(),"Common Bow");
        assertEquals(bow.getPower(),10);
        assertEquals(bow.getMinRange(),2);
        assertEquals(bow.getMaxRange(),3);
    }

    @Override
    @Test
    public void checkPropertyChange() {
        bowFactory.setName("Bow");
        bowFactory.setMaxRange(4);
        bowFactory.setMinRange(3);
        bowFactory.setPower(20);
        Bow bow = (Bow) bowFactory.create();
        assertEquals(bow.getName(),"Bow");
        assertEquals(bow.getPower(),20);
        assertEquals(bow.getMinRange(),3);
        assertEquals(bow.getMaxRange(),4);
    }
}
