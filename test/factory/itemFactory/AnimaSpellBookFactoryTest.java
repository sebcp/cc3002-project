package factory.itemFactory;

import model.items.AnimaSpellBook;
import model.units.Alpaca;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AnimaSpellBookFactoryTest extends ItemFactoryTestUnit {
    @Override
    @Test
    public void createDefault() {
        AnimaSpellBook animaSpellBook = (AnimaSpellBook) animaSpellBookFactory.create();
        assertEquals(animaSpellBook.getName(),"Common Anima SpellBook");
        assertEquals(animaSpellBook.getPower(),10);
        assertEquals(animaSpellBook.getMinRange(),2);
        assertEquals(animaSpellBook.getMaxRange(),3);
        assertNull(animaSpellBook.getOwner());
    }

    @Override
    @Test
    public void checkPropertyChange() {
        Alpaca alpaca = new Alpaca(50,5,null,"Alpaca");
        animaSpellBookFactory.setOwner(alpaca);
        animaSpellBookFactory.setName("Anima SpellBook");
        animaSpellBookFactory.setMaxRange(4);
        animaSpellBookFactory.setMinRange(3);
        animaSpellBookFactory.setPower(20);
        AnimaSpellBook animaSpellBook = (AnimaSpellBook) animaSpellBookFactory.create();
        assertTrue(alpaca.getItems().contains(animaSpellBook));
        assertEquals(animaSpellBook.getName(),"Anima SpellBook");
        assertEquals(animaSpellBook.getPower(),20);
        assertEquals(animaSpellBook.getMinRange(),3);
        assertEquals(animaSpellBook.getMaxRange(),4);
    }
}
