package factory.itemFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import model.items.Spear;
import org.junit.jupiter.api.Test;

public class SpearFactoryTest extends ItemFactoryTestUnit {
    @Override
    @Test
    public void createDefault() {
        Spear spear = (Spear) spearFactory.create();
        assertEquals(spear.getName(),"Common Spear");
        assertEquals(spear.getPower(),10);
        assertEquals(spear.getMinRange(),1);
        assertEquals(spear.getMaxRange(),2);
    }

    @Override
    @Test
    public void checkPropertyChange() {
        spearFactory.setName("Spear");
        spearFactory.setMaxRange(3);
        spearFactory.setMinRange(2);
        spearFactory.setPower(20);
        Spear spear = (Spear) spearFactory.create();
        assertEquals(spear.getName(),"Spear");
        assertEquals(spear.getPower(),20);
        assertEquals(spear.getMinRange(),2);
        assertEquals(spear.getMaxRange(),3);
    }
}
