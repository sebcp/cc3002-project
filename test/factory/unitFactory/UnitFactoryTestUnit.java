package factory.unitFactory;

import model.map.Field;
import model.map.Location;
import model.units.Alpaca;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public abstract class UnitFactoryTestUnit {
    protected AlpacaFactory alpacaFactory = new AlpacaFactory();
    protected ArcherFactory archerFactory = new ArcherFactory();
    protected ClericFactory clericFactory = new ClericFactory();
    protected FighterFactory fighterFactory = new FighterFactory();
    protected HeroFactory heroFactory = new HeroFactory();
    protected SorcererFactory sorcererFactory = new SorcererFactory();
    protected SwordMasterFactory swordMasterFactory = new SwordMasterFactory();
    protected Field field;

    @Test
    public abstract void createDefault();

    @Test
    public abstract void checkPropertyChanges();


    @BeforeEach
    public void setTestField() {
        field = new Field();
        field.addCells(true, new Location(0, 0), new Location(0, 1), new Location(0, 2),
                new Location(1, 0), new Location(1, 1), new Location(1, 2), new Location(2, 0),
                new Location(2, 1), new Location(2, 2));
    }
}
