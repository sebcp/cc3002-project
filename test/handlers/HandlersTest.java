package handlers;

import controller.GameController;
import factory.itemFactory.AxeFactory;
import factory.itemFactory.SpearFactory;
import factory.unitFactory.FighterFactory;
import factory.unitFactory.HeroFactory;
import model.map.Field;
import model.units.Fighter;
import model.units.Hero;
import org.junit.jupiter.api.Test;
import tactician.Tactician;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HandlersTest {
    GameController controller = new GameController(2,2);
    Field map = controller.getGameMap();
    HeroFactory heroFactory = new HeroFactory();
    FighterFactory fighterFactory = new FighterFactory();
    AxeFactory axeFactory = new AxeFactory();
    SpearFactory spearFactory = new SpearFactory();

    @Test
    public void HeroDeathHandlerTest(){
        heroFactory.setTactician(controller.getTurnOwner());
        heroFactory.setLocation(map.getCell(0,0));
        Hero hero = (Hero) heroFactory.create();
        controller.endTurn();

        fighterFactory.setTactician(controller.getTurnOwner());
        fighterFactory.setLocation(map.getCell(0,1));
        Fighter fighter = (Fighter) fighterFactory.create();
        axeFactory.setOwner(fighter);
        axeFactory.setPower(100);
        axeFactory.create();
        controller.selectUnitIn(0,1);
        controller.equipItem(0);
        controller.useItemOn(0,0);
        controller.getTacticians().get(0);
        assertEquals(controller.getWinners().get(0),controller.getTacticians().get(0).getName());
    }

    @Test
    public void BorderHeroDeathTest(){
        Tactician first = controller.getTurnOwner();
        fighterFactory.setTactician(controller.getTurnOwner());
        fighterFactory.setLocation(map.getCell(0,1));
        Fighter fighter = (Fighter) fighterFactory.create();
        axeFactory.setOwner(fighter);
        axeFactory.setPower(100);
        axeFactory.create();
        controller.selectUnitIn(0,1);
        controller.equipItem(0);
        controller.endTurn();

        heroFactory.setTactician(controller.getTurnOwner());
        heroFactory.setLocation(map.getCell(0,0));
        Hero hero = (Hero) heroFactory.create();
        spearFactory.setOwner(hero);
        spearFactory.setPower(10);
        spearFactory.create();
        controller.selectUnitIn(0,0);
        controller.equipItem(0);
        controller.useItemOn(0,1);
        assertEquals(1,controller.getWinners().size());
        assertEquals(first.getName(),controller.getWinners().get(0));
    }

    @Test
    public void UnitDeathHandlerTest(){
        Tactician first = controller.getTurnOwner();
        fighterFactory.setTactician(first);
        fighterFactory.setLocation(map.getCell(0,1));
        Fighter fighter = (Fighter) fighterFactory.create();
        fighterFactory.setLocation(map.getCell(1,0));
        fighterFactory.create();
        assertEquals(2,first.getUnits().size());
        controller.endTurn();

        heroFactory.setTactician(controller.getTurnOwner());
        heroFactory.setLocation(map.getCell(0,0));
        Hero hero = (Hero) heroFactory.create();
        spearFactory.setOwner(hero);
        spearFactory.setPower(100);
        spearFactory.create();
        controller.selectUnitIn(0,0);
        controller.equipItem(0);
        controller.useItemOn(1,0);
        assertEquals(1,first.getUnits().size());
        assertEquals(fighter,first.getUnits().get(0));
    }
}
