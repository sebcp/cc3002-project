package factory.itemFactory;

import org.junit.jupiter.api.Test;

/**
 * @author Sebasián Contreras Phillippi
 * @since 2.0
 */
public abstract class ItemFactoryTestUnit {
    protected BowFactory bowFactory = new BowFactory();
    protected AxeFactory axeFactory = new AxeFactory();
    protected SpearFactory spearFactory = new SpearFactory();
    protected StaffFactory staffFactory = new StaffFactory();
    protected SwordFactory swordFactory = new SwordFactory();
    protected AnimaSpellBookFactory animaSpellBookFactory = new AnimaSpellBookFactory();
    protected LuzSpellBookFactory luzSpellBookFactory = new LuzSpellBookFactory();
    protected OscuridadSpellBookFactory oscuridadSpellBookFactory = new OscuridadSpellBookFactory();

    @Test
    public abstract void createDefault();

    @Test
    public abstract void checkPropertyChange();
}
