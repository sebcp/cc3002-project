package factory.itemFactory;

import model.items.OscuridadSpellBook;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OscuridadSpellBookfFactoryTest extends ItemFactoryTestUnit {
    @Override
    @Test
    public void createDefault() {
        OscuridadSpellBook oscuridadSpellBook = (OscuridadSpellBook) oscuridadSpellBookFactory.create();
        assertEquals(oscuridadSpellBook.getName(),"Common Oscuridad SpellBook");
        assertEquals(oscuridadSpellBook.getPower(),10);
        assertEquals(oscuridadSpellBook.getMinRange(),2);
        assertEquals(oscuridadSpellBook.getMaxRange(),3);
    }

    @Override
    @Test
    public void checkPropertyChange() {
        oscuridadSpellBookFactory.setName("Oscuridad SpellBook");
        oscuridadSpellBookFactory.setMaxRange(4);
        oscuridadSpellBookFactory.setMinRange(3);
        oscuridadSpellBookFactory.setPower(20);
        OscuridadSpellBook oscuridadSpellBook = (OscuridadSpellBook) oscuridadSpellBookFactory.create();
        assertEquals(oscuridadSpellBook.getName(),"Oscuridad SpellBook");
        assertEquals(oscuridadSpellBook.getPower(),20);
        assertEquals(oscuridadSpellBook.getMinRange(),3);
        assertEquals(oscuridadSpellBook.getMaxRange(),4);
    }
}
