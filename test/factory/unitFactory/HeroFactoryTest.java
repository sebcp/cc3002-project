package factory.unitFactory;

import model.units.Hero;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class HeroFactoryTest extends UnitFactoryTestUnit {
    @Override
    @Test
    public void createDefault() {
        Hero hero = (Hero) heroFactory.create();
        assertEquals(hero.getName(),"Hero");
        assertEquals(hero.getMaxHitPoints(),50);
        assertEquals(hero.getMovement(),2);
        assertNull(hero.getLocation());
    }

    @Override
    @Test
    public void checkPropertyChanges() {
        heroFactory.setLocation(field.getCell(0,0));
        heroFactory.setMaxHitPoints(10);
        heroFactory.setMovement(3);
        heroFactory.setName("notHero");
        Hero hero = (Hero) heroFactory.create();
        assertEquals(hero.getName(),"notHero");
        assertEquals(hero.getMaxHitPoints(),10);
        assertEquals(hero.getMovement(),3);
        assertEquals(hero.getLocation(),field.getCell(0,0));

        heroFactory.setLocation(field.getCell(0,0));
        Hero hero2 = (Hero) heroFactory.create();
        assertNull(hero2.getLocation());
    }
}
