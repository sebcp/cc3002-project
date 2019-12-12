package handlers;

import controller.GameController;
import factory.itemFactory.AxeFactory;
import factory.unitFactory.FighterFactory;
import factory.unitFactory.HeroFactory;
import model.map.Field;
import model.units.Fighter;
import model.units.Hero;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HandlersTestUnit {
    GameController controller = new GameController(2,2);
    Field map = controller.getGameMap();
    HeroFactory heroFactory = new HeroFactory();
    FighterFactory fighterFactory = new FighterFactory();
    AxeFactory axeFactory = new AxeFactory();

    @BeforeEach
    public void setTest(){
        heroFactory.setTactician(controller.getTurnOwner());
        heroFactory.setLocation(map.getCell(0,0));
        heroFactory.create();
        controller.endTurn();
        fighterFactory.setTactician(controller.getTurnOwner());
        fighterFactory.setLocation(map.getCell(0,1));
        Fighter fighter = (Fighter) fighterFactory.create();
        axeFactory.setOwner(fighter);
        axeFactory.setPower(50);
        axeFactory.create();
        controller.selectUnitIn(0,1);
        controller.equipItem(0);
    }

    @Test
    public void TestHandler(){
        controller.useItemOn(0,0);
        controller.getTacticians().get(0);
        assertEquals(controller.getWinners().get(0),controller.getTacticians().get(0).getName());
    }
}
