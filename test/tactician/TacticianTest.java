package tactician;

import controller.GameController;
import factory.itemFactory.AxeFactory;
import factory.unitFactory.FighterFactory;
import model.map.Field;
import model.units.Fighter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


public class TacticianTest {
    GameController controller = new GameController(2,2);
    Field map = controller.getGameMap();
    FighterFactory fighterFactory = new FighterFactory();
    AxeFactory axeFactory = new AxeFactory();

    @Test
    public void GettersTest(){
        Tactician current = controller.getTurnOwner();
        fighterFactory.setLocation(map.getCell(0,0));
        fighterFactory.setTactician(current);
        Fighter fighter = (Fighter) fighterFactory.create();
        axeFactory.setOwner(fighter);
        axeFactory.create();
        assertEquals("",current.getSelectedName());
        assertEquals(0,current.getSelectedCurrentHitPoints());
        assertEquals(0,current.getSelectedMaxHitPoints());
        assertNull(current.getSelectedLocation());
        assertEquals(0,current.getSelectedMovement());
        assertNull(current.getSelectedItems());

        controller.selectUnitIn(0,0);
        assertEquals("Fighter",current.getSelectedName());
        assertEquals(50,current.getSelectedCurrentHitPoints());
        assertEquals(50,current.getSelectedMaxHitPoints());
        assertEquals(map.getCell(0,0),current.getSelectedLocation());
        assertEquals(2,current.getSelectedMovement());
        assertEquals(fighter.getItems(),current.getSelectedItems());
    }
}
