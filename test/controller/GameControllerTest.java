package controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import model.Tactician;
import model.map.Field;
import model.map.Location;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Ignacio Slater Muñoz
 * @since v2.0
 */
class GameControllerTest {

  private GameController controller;
  private long randomSeed;
  private List<String> testTacticians;

  @BeforeEach
  void setUp() {
    // Se define la semilla como un número aleatorio para generar variedad en los tests
    randomSeed = new Random().nextLong();
    controller = new GameController(4, 7);
    testTacticians = List.of("Player 0", "Player 1", "Player 2", "Player 3");
  }

  @Test
  void getTacticians() {
    List<Tactician> tacticians = controller.getTacticians();
    assertEquals(4, tacticians.size());
    for (int i = 0; i < tacticians.size(); i++) {
      assertEquals("Player " + i, tacticians.get(i).getName());
    }
  }

  @Test
  void getGameMap() {
      Field gameMap = controller.getGameMap();
      assertEquals(7, gameMap.getSize()); // getSize deben definirlo
      assertTrue(controller.getGameMap().isConnected());

      Field mapita = new Field();
      Field mapita_clone = new Field();
      mapita_clone.setSeed(randomSeed);
      mapita.setSeed(randomSeed);
      for (int i = 0; i < 5; i++) {
        for (int j = 0; j < 5; j++) {
          mapita.addCells(false, new Location(i, j));
          mapita_clone.addCells(false, new Location(i, j));
        }
      }
      assertEquals(mapita, mapita_clone);
  }

  @Test
  void getTurnOwner() {
    //  En este caso deben hacer lo mismo que para el mapa
  }

  @Test
  void getRoundNumber() {
    controller.initGame(10);
    for (int i = 1; i < 10; i++) {
      assertEquals(i, controller.getRoundNumber());
      for (int j = 0; j < 4; j++) {
        controller.endTurn();
      }
    }
  }

  @Test
  void getMaxRounds() {
    Random randomTurnSequence = new Random();
    IntStream.range(0, 50).map(i -> randomTurnSequence.nextInt() & Integer.MAX_VALUE).forEach(nextInt -> {
      controller.initGame(nextInt);
      System.out.println(nextInt);
      assertEquals(nextInt, controller.getMaxRounds());
      System.out.println(nextInt);
    });
    controller.initEndlessGame();
    assertEquals(-1, controller.getMaxRounds());
  }

  @Test
  void endTurn() {
    Tactician firstPlayer = controller.getTurnOwner();
    Tactician secondPlayer = controller.getTurns().get(1);
    assertNotEquals(secondPlayer.getName(), firstPlayer.getName());

    controller.endTurn();
    assertNotEquals(firstPlayer.getName(), controller.getTurnOwner().getName());
    assertEquals(secondPlayer.getName(), controller.getTurnOwner().getName());
  }

  @Test
  void removeTactician() {
    assertEquals(4, controller.getTacticians().size());
    controller.getTacticians()
        .forEach(tactician -> Assertions.assertTrue(testTacticians.contains(tactician.getName())));

    controller.removeTactician("Player 0");
    assertEquals(3, controller.getTacticians().size());
    controller.getTacticians().forEach(tactician -> assertNotEquals("Player 0", tactician));
    controller.getTacticians()
        .forEach(tactician -> Assertions.assertTrue(testTacticians.contains(tactician.getName())));

    controller.removeTactician("Player 5");
    assertEquals(3, controller.getTacticians().size());
    controller.getTacticians()
        .forEach(tactician -> Assertions.assertTrue(testTacticians.contains(tactician.getName())));
  }

  @Test
  void getWinners() {
    controller.initGame(2);
    for(int i=0;i<8;i++) {
      controller.endTurn();
    }
    assertEquals(4, controller.getWinners().size());
    controller.getWinners()
        .forEach(player -> Assertions.assertTrue(testTacticians.contains(player)));

    controller.initGame(2);
    IntStream.range(0, 4).forEach(i -> controller.endTurn());
    assertNull(controller.getWinners());
    controller.removeTactician("Player 0");
    controller.removeTactician("Player 2");
    IntStream.range(0, 2).forEach(i -> controller.endTurn());
    List<String> winners = controller.getWinners();
    assertEquals(2, winners.size());
    assertTrue(List.of("Player 1", "Player 3").containsAll(winners));

    controller.initEndlessGame();
    for (int i = 0; i < 3; i++) {
      assertNull(controller.getWinners());
      controller.removeTactician("Player " + i);
    }
    assertTrue(List.of("Player 3").containsAll(controller.getWinners()));
  }

  @Test
  void getSelectedUnit() {
    assert(false);
  }

  @Test
  void selectUnitIn() {
    assert(false);
  }

  @Test
  void getItems() {
    assert(false);
  }

  @Test
  void equipItem() {
    assert(false);
  }

  @Test
  void useItemOn() {
    assert(false);
  }

  @Test
  void selectItem() {
    assert(false);
  }

  @Test
  void giveItemTo() {
    assert(false);
  }
}