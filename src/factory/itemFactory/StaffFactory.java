package factory.itemFactory;

import model.items.IEquipableItem;
import model.items.Staff;
import model.units.IUnit;

public class StaffFactory implements ItemFactoryInterface {
    private String name = "Common Staff";
    private int power = -10;
    private int minRange = 1;
    private int maxRange = 2;
    private IUnit owner;


    @Override
    public IEquipableItem create() {
        Staff staff = new Staff(name,power,minRange,maxRange);
        if(owner!=null){
            owner.addItem(staff);
        }
        return staff;
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
