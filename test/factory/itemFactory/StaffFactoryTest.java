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
    @Test
    public void checkPropertyChange() {
        staffFactory.setName("Staff");
        staffFactory.setMaxRange(3);
        staffFactory.setMinRange(2);
        staffFactory.setPower(-20);
        Staff staff = (Staff) staffFactory.create();
        assertEquals(staff.getName(),"Staff");
        assertEquals(staff.getPower(),-20);
        assertEquals(staff.getMinRange(),2);
        assertEquals(staff.getMaxRange(),3);
    }
}
