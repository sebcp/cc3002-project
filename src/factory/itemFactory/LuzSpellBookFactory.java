package factory.itemFactory;

import model.items.IEquipableItem;
import model.items.LuzSpellBook;

public class LuzSpellBookFactory implements itemFactoryInterface {
    private String name = "Common Luz SpellBook";
    private int power = 10;
    private int minRange = 2;
    private int maxRange = 3;

    @Override
    public IEquipableItem create() {
        return new LuzSpellBook(name, power, minRange, maxRange);
    }

    @Override
    public void setName(String name) {
        this.name=name;
    }

    @Override
    public void setPower(int power) {
        this.power=power;
    }

    @Override
    public void setMinRange(int minRange) {
        this.minRange=minRange;
    }

    @Override
    public void setMaxRange(int maxRange) {
        this.maxRange=maxRange;
    }
}
