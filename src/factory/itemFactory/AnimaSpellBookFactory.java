package factory.itemFactory;

import model.items.AnimaSpellBook;
import model.items.IEquipableItem;

public class AnimaSpellBookFactory implements itemFactoryInterface {
    private String name = "Common Anima SpellBook";
    private int power = 10;
    private int minRange = 2;
    private int maxRange = 3;

    @Override
    public IEquipableItem create() {
        return new AnimaSpellBook(name, power, minRange, maxRange);
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
