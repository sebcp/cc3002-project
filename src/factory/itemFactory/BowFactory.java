package factory.itemFactory;

import model.items.Bow;
import model.items.IEquipableItem;
import model.units.IUnit;

public class BowFactory implements ItemFactoryInterface {
    private String name = "Common Bow";
    private int power = 10;
    private int minRange = 2;
    private int maxRange = 3;
    private IUnit owner;


    @Override
    public IEquipableItem create() {
        Bow bow = new Bow(name,power,minRange,maxRange);
        if(owner!=null){
            owner.addItem(bow);
        }
        return bow;
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
