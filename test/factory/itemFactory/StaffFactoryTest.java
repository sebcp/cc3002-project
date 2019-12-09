package factory.itemFactory;

import model.items.Staff;
import model.units.Alpaca;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StaffFactoryTest extends ItemFactoryTestUnit{
    @Override
    @Test
    public void createDefault() {
        Staff staff = (Staff) staffFactory.create();
        assertEquals(staff.getName(),"Common Staff");
        assertEquals(staff.getPower(),-10);
        assertEquals(staff.getMinRange(),1);
        assertEquals(staff.getMaxRange(),2);
        assertNull(staff.getOwner());
    }

    @Override
    @Test
    public void checkPropertyChange() {
        Alpaca alpaca = new Alpaca(50,5,null,"Alpaca");
        staffFactory.setOwner(alpaca);
        staffFactory.setName("Staff");
        staffFactory.setMaxRange(3);
        staffFactory.setMinRange(2);
        staffFactory.setPower(-20);
        Staff staff = (Staff) staffFactory.create();
        assertTrue(alpaca.getItems().contains(staff));
        assertEquals(staff.getName(),"Staff");
        assertEquals(staff.getPower(),-20);
        assertEquals(staff.getMinRange(),2);
        assertEquals(staff.getMaxRange(),3);
    }
}
