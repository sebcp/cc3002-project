package factory.itemFactory;

import model.items.OscuridadSpellBook;
import model.items.IEquipableItem;
import model.units.IUnit;

public class OscuridadSpellBookFactory implements ItemFactoryInterface {
    private String name = "Common Oscuridad SpellBook";
    private int power = 10;
    private int minRange = 2;
    private int maxRange = 3;
    private IUnit owner;


    @Override
    public IEquipableItem create() {
        OscuridadSpellBook oscuridadSpellBook = new OscuridadSpellBook(name, power, minRange, maxRange);
        if(owner!=null){
            owner.addItem(oscuridadSpellBook);
        }
        return oscuridadSpellBook;
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
