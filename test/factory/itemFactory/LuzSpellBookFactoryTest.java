package factory.itemFactory;

import model.items.LuzSpellBook;
import model.units.Alpaca;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LuzSpellBookFactoryTest extends ItemFactoryTestUnit{
    @Override
    @Test
    public void createDefault() {
        LuzSpellBook luzSpellBook = (LuzSpellBook) luzSpellBookFactory.create();
        assertEquals(luzSpellBook.getName(),"Common Luz SpellBook");
        assertEquals(luzSpellBook.getPower(),10);
        assertEquals(luzSpellBook.getMinRange(),2);
        assertEquals(luzSpellBook.getMaxRange(),3);
        assertNull(luzSpellBook.getOwner());
    }

    @Override
    @Test
    public void checkPropertyChange() {
        Alpaca alpaca = new Alpaca(50,5,null,"Alpaca");
        luzSpellBookFactory.setOwner(alpaca);
        luzSpellBookFactory.setName("Luz SpellBook");
        luzSpellBookFactory.setMaxRange(4);
        luzSpellBookFactory.setMinRange(3);
        luzSpellBookFactory.setPower(20);
        LuzSpellBook luzSpellBook = (LuzSpellBook) luzSpellBookFactory.create();
        assertTrue(alpaca.getItems().contains(luzSpellBook));
        assertEquals(luzSpellBook.getName(),"Luz SpellBook");
        assertEquals(luzSpellBook.getPower(),20);
        assertEquals(luzSpellBook.getMinRange(),3);
        assertEquals(luzSpellBook.getMaxRange(),4);
    }
}
