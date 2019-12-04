package factory.itemFactory;

import model.items.LuzSpellBook;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LuzSpellBookFactoryTest extends ItemFactoryTestUnit{
    @Override
    @Test
    public void createDefault() {
        LuzSpellBook luzSpellBook = (LuzSpellBook) luzSpellBookFactory.create();
        assertEquals(luzSpellBook.getName(),"Common Luz SpellBook");
        assertEquals(luzSpellBook.getPower(),10);
        assertEquals(luzSpellBook.getMinRange(),2);
        assertEquals(luzSpellBook.getMaxRange(),3);
    }

    @Override
    @Test
    public void checkPropertyChange() {
        luzSpellBookFactory.setName("Luz SpellBook");
        luzSpellBookFactory.setMaxRange(4);
        luzSpellBookFactory.setMinRange(3);
        luzSpellBookFactory.setPower(20);
        LuzSpellBook luzSpellBook = (LuzSpellBook) luzSpellBookFactory.create();
        assertEquals(luzSpellBook.getName(),"Luz SpellBook");
        assertEquals(luzSpellBook.getPower(),20);
        assertEquals(luzSpellBook.getMinRange(),3);
        assertEquals(luzSpellBook.getMaxRange(),4);
    }
}
