package factory.itemFactory;

import model.items.Staff;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StaffFactoryTest extends ItemFactoryTestUnit{
    @Override
    @Test
    public void createDefault() {
        Staff staff = (Staff) staffFactory.create();
        assertEquals(staff.getName(),"Common Staff");
        assertEquals(staff.getPower(),-10);
        assertEquals(staff.getMinRange(),1);
        assertEquals(staff.getMaxRange(),2);
    }

    @Override
    public void checkPropertyChange() {
        spearFactory.setName("Staff");
        spearFactory.setMaxRange(3);
        spearFactory.setMinRange(2);
        spearFactory.setPower(-20);
        Staff staff = (Staff) staffFactory.create();
        assertEquals(staff.getName(),"Staff");
        assertEquals(staff.getPower(),-20);
        assertEquals(staff.getMinRange(),2);
        assertEquals(staff.getMaxRange(),3);
    }
}
