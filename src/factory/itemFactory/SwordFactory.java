package factory.itemFactory;

import model.items.IEquipableItem;
import model.items.Sword;
import model.units.IUnit;

public class SwordFactory implements ItemFactoryInterface {
    private String name = "Common Sword";
    private int power = 10;
    private int minRange = 1;
    private int maxRange = 2;
    private IUnit owner;


    @Override
    public IEquipableItem create() {
        Sword sword = new Sword(name,power,minRange,maxRange);
        if(owner!=null){
            owner.addItem(sword);
        }
        return sword;
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
