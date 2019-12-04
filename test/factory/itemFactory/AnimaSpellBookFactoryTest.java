package factory.itemFactory;

import model.items.AnimaSpellBook;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnimaSpellBookFactoryTest extends ItemFactoryTestUnit {
    @Override
    @Test
    public void createDefault() {
        AnimaSpellBook animaSpellBook = (AnimaSpellBook) animaSpellBookFactory.create();
        assertEquals(animaSpellBook.getName(),"Common Anima SpellBook");
        assertEquals(animaSpellBook.getPower(),10);
        assertEquals(animaSpellBook.getMinRange(),2);
        assertEquals(animaSpellBook.getMaxRange(),3);
    }

    @Override
    @Test
    public void checkPropertyChange() {
        animaSpellBookFactory.setName("Anima SpellBook");
        animaSpellBookFactory.setMaxRange(4);
        animaSpellBookFactory.setMinRange(3);
        animaSpellBookFactory.setPower(20);
        AnimaSpellBook animaSpellBook = (AnimaSpellBook) animaSpellBookFactory.create();
        assertEquals(animaSpellBook.getName(),"Anima SpellBook");
        assertEquals(animaSpellBook.getPower(),20);
        assertEquals(animaSpellBook.getMinRange(),3);
        assertEquals(animaSpellBook.getMaxRange(),4);
    }
}
