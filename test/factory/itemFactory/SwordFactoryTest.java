package factory.itemFactory;

import model.items.Sword;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SwordFactoryTest extends ItemFactoryTestUnit{
        @Override
        @Test
        public void createDefault() {
            Sword sword = (Sword) swordFactory.create();
            assertEquals(sword.getName(),"Common Sword");
            assertEquals(sword.getPower(),10);
            assertEquals(sword.getMinRange(),1);
            assertEquals(sword.getMaxRange(),2);
        }

        @Override
        public void checkPropertyChange() {
            swordFactory.setName("Sword");
            swordFactory.setMaxRange(3);
            swordFactory.setMinRange(2);
            swordFactory.setPower(20);
            Sword sword = (Sword) swordFactory.create();
            assertEquals(sword.getName(),"Sword");
            assertEquals(sword.getPower(),-20);
            assertEquals(sword.getMinRange(),2);
            assertEquals(sword.getMaxRange(),3);
        }
}
