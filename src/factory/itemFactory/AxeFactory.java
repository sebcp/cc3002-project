package factory.itemFactory;

import model.items.Axe;
import model.items.IEquipableItem;

public class AxeFactory implements itemFactoryInterface {
    private String name = "Common Axe";
    private int power = 10;
    private int minRange = 1;
    private int maxRange = 2;

    @Override
    public IEquipableItem create() {
        return new Axe(name, power, minRange, maxRange);
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
