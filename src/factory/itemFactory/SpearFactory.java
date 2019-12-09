package factory.itemFactory;

import model.items.IEquipableItem;
import model.items.Spear;
import model.units.IUnit;

public class SpearFactory implements ItemFactoryInterface {
    private String name = "Common Spear";
    private int power = 10;
    private int minRange = 1;
    private int maxRange = 2;
    private IUnit owner;


    @Override
    public IEquipableItem create() {
        Spear spear = new Spear(name,power,minRange,maxRange);
        if(owner!=null){
            owner.addItem(spear);
        }
        return spear;
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
