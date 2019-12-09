package factory.itemFactory;

import model.items.Axe;
import model.items.IEquipableItem;
import model.units.IUnit;

public class AxeFactory implements ItemFactoryInterface {
    private String name = "Common Axe";
    private int power = 10;
    private int minRange = 1;
    private int maxRange = 2;
    private IUnit owner;


    @Override
    public IEquipableItem create() {
        Axe axe = new Axe(name, power, minRange, maxRange);
        if(owner!=null){
            owner.addItem(axe);
        }
        return axe;
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

    @Override
    public void setOwner(IUnit unit){
        owner = unit;
    }
}
