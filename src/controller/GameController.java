package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.Tactician;
import model.items.*;
import model.map.*;

import model.units.IUnit;

/**
 * Controller of the game.
 * The controller manages all the input received from de game's GUI.
 *
 * @author Ignacio Slater Muñoz
 * @author Sebastián Contreras Phillippi
 * @version 2.0
 * @since 2.0
 */
public class GameController {

  private List<Tactician> tacticians = new ArrayList<>();
  private int turnsLeft;
  private Field map = new Field();
  private Tactician currentPlayer;
  private List<Tactician> turns = new ArrayList<>();
  private Tactician firstPlayer;
  private int currentRound = 1;
  private int maxRounds;
  private int numberOfPlayers;
  private IEquipableItem selectedItem;
  private int selectedIndex;

  /**
   * Creates the controller for a new game.
   *
   * @param numberOfPlayers the number of players for this game
   * @param mapSize         the dimensions of the map, for simplicity, all maps are squares
   */
  public GameController(int numberOfPlayers, int mapSize) {
    this.numberOfPlayers = numberOfPlayers;
    createMap(mapSize);
    long seed = new Random().nextLong();
    setNewGame(numberOfPlayers, seed);
  }

  /**
   * Sets the turns for the current round. Turns are randomly decided and the first player of the last round
   * cannot be the first of this round.
   *
   * @param numberOfPlayers the number of players
   * @param randomSeed      the seed for the random generator
   */
  public void setTurns(int numberOfPlayers, long randomSeed) {
    List<Tactician> copy = new ArrayList<>();
    for (int i = 0; i < getTacticians().size(); i++) {
      Tactician clone = getTacticians().get(i);
      copy.add(clone);
    }
    Random ran = new Random(randomSeed);
    for (int i = 0; i < numberOfPlayers; i++) {
      int sel = ran.nextInt(copy.size());
      Tactician selected = copy.get(sel);
      turns.add(selected);
      copy.remove(selected);
      if (i == 0) {
        if (firstPlayer != null) {
          while (firstPlayer.equals(selected)) {
            sel = ran.nextInt(copy.size() + 1);
            selected = getTacticians().get(sel);
          }
        }
        firstPlayer = selected;
      }
    }
    currentPlayer = turns.get(0);
  }

  /**
   * Creates a square map of a given size
   *
   * @param mapSize the map's size (mapSize x mapSize)
   */
  public void createMap(int mapSize) {
    for (int i = 0; i < mapSize; i++) {
      for (int j = 0; j < mapSize; j++) {
        map.addCells(false, new Location(i, j));
      }
    }
  }

  /**
   * Creates the players for a new game.
   *
   * @param numberOfPlayers the amount of players to be created for the game
   */
  private void createPlayers(int numberOfPlayers) {
    for (int i = 0; i < numberOfPlayers; i++) {
      Tactician tactician = new Tactician("Player " + i);
      addTactician(tactician);
    }
  }

  /**
   * @return the list of all the tacticians participating in the game.
   */
  public List<Tactician> getTacticians() {
    return tacticians;
  }

  /**
   * Adds a tactician to the game.
   *
   * @param tactician the tactician to be added
   */
  public void addTactician(Tactician tactician) {
    tacticians.add(tactician);
  }

  /**
   * @return the map of the current game
   */
  public Field getGameMap() {
    return map;
  }

  /**
   * @return the tactician that's currently playing.
   */
  public Tactician getTurnOwner() {
    return currentPlayer;
  }

  /**
   * Resets the content of the firstPlayer variable.
   */
  public void wipeFirstTurn() {
    firstPlayer = null;
  }

  /**
   * @return the list of turns.
   */
  public List<Tactician> getTurns() {
    return turns;
  }

  /**
   * @return the number of rounds since the start of the game.
   */
  public int getRoundNumber() {
    return currentRound;
  }

  /**
   * @return the maximum number of rounds a match can last
   */
  public int getMaxRounds() {
    return maxRounds;
  }

  /**
   * Finishes the current player's turn.
   */
  public void endTurn() {
    if (turnsLeft - 1 == 0) {
      turnsLeft = getTacticians().size();
      long seed = new Random().nextLong();
      setTurns(getTacticians().size(), seed);
      currentRound++;

    } else {
      turnsLeft--;
    }
    turns.remove(0);
    currentPlayer = turns.get(0);
  }

  /**
   * Removes a tactician and all of it's units from the game.
   *
   * @param tactician the player to be removed
   */
  public void removeTactician(String tactician) {
    int size = getTacticians().size();
    for (int i = 0; i < size - 1; i++) {
      Tactician current = getTacticians().get(i);
      String name = getTacticianName(i);
      if (name.equals(tactician)) {
        getTacticians().remove(current);
      }
    }
    for (int i = 0; i < turnsLeft; i++) {
      Tactician toCompare = turns.get(i);
      if (tactician.equals(toCompare.getName())) {
        turns.remove(toCompare);
        turnsLeft--;
      }
    }
  }

  /**
   * @return the number of tacticians left in the game.
   */
  public int tacticiansLeft() {
    return getTacticians().size();
  }

  /**
   * Returns the name of an specific tactician.
   *
   * @param pos the position of the tactician in the tactician list
   * @return the name of the tactician.
   */
  public String getTacticianName(int pos) {
    return getTacticians().get(pos).getName();
  }

  /**
   * Starts the game.
   *
   * @param maxTurns the maximum number of turns the game can last
   */
  public void initGame(final int maxTurns) {
    this.maxRounds = maxTurns;
    this.currentRound = 1;
    firstPlayer = null;
    long seed = new Random().nextLong();
    setNewGame(numberOfPlayers, seed);
  }

  /**
   * Starts a game without a limit of turns.
   */
  public void initEndlessGame() {
    this.maxRounds = -1;
    this.currentRound = 1;
    firstPlayer = null;
    long seed = new Random().nextLong();
    setNewGame(numberOfPlayers, seed);
  }

  /**
   * Sets a new game on the current map
   *
   * @param numberOfPlayers the number of tacticians
   * @param randomSeed      the seed that determines how the turns are decided
   */
  public void setNewGame(int numberOfPlayers, long randomSeed) {
    tacticians = new ArrayList<>();
    turns = new ArrayList<>();
    createPlayers(numberOfPlayers);
    turnsLeft = numberOfPlayers;
    setTurns(numberOfPlayers, randomSeed);
  }

  /**
   * @return the winner of this game, if the match ends in a draw returns a list of all the winners
   */
  public List<String> getWinners() {
    List<String> winners = new ArrayList<>();
    if (tacticiansLeft() == 1) {
      winners.add(getTacticianName(0));
    } else if (currentRound > maxRounds && maxRounds != -1) {
      int max = 0;
      for (int i = 0; i < tacticiansLeft(); i++) {
        Tactician current = tacticians.get(i);
        if (current.getUnits().size() > max) {
          max = current.getUnits().size();
        }
      }
      for (int i = 0; i < tacticiansLeft(); i++) {
        Tactician current = tacticians.get(i);
        if (current.getUnits().size() == max) {
          winners.add(getTacticianName(i));
        }
      }
    }
    if (winners.isEmpty()) {
      winners = null;
    }
    return winners;
  }

  /**
   * @return the current player's selected unit
   */
  public IUnit getSelectedUnit() {
    return currentPlayer.getSelectedUnit();
  }

  /**
   * Selects a unit in the game map
   *
   * @param x horizontal position of the unit
   * @param y vertical position of the unit
   */
  public void selectUnitIn(int x, int y) {
    Location cell = map.getCell(x, y);
    if(!cell.isEmpty() && currentPlayer.getUnits().contains(cell.getUnit())){
      currentPlayer.selectUnit(cell.getUnit());
    }
    else{
      currentPlayer.selectUnit(null);
    }
  }

  /**
   * @return the inventory of the currently selected unit.
   */
  public List<IEquipableItem> getItems() {
    return currentPlayer.getSelectedUnit().getItems();
  }

  /**
   * Equips an item from the inventory to the currently selected unit.
   *
   * @param index the location of the item in the inventory.
   */
  public void equipItem(int index) {
    IUnit selected = currentPlayer.getSelectedUnit();
    IEquipableItem item = selected.getItems().get(index);
    selected.equipItem(item);
  }

  /**
   * Uses the equipped item on a target
   *
   * @param x horizontal position of the target
   * @param y vertical position of the target
   */
  public void useItemOn(int x, int y) {
    IUnit selected = currentPlayer.getSelectedUnit();
    Location cell = map.getCell(x, y);
    if (!cell.isEmpty()) {
      selected.combat(cell.getUnit());
    }
  }

  /**
   * Selects an item from the selected unit's inventory.
   *
   * @param index the location of the item in the inventory.
   */
  public void selectItem(int index) {
    IUnit selected = currentPlayer.getSelectedUnit();
    if (selected.getItems().size() > index) {
      selectedItem = selected.getItems().get(index);
      selectedIndex = index;
    } else {
      selectedItem = null;
    }
  }

  /**
   * Returns the currently selected item from the selected unit of the turn owner.
   * @return the item that's currently selected
   */
  public IEquipableItem getSelectedItem(){
    return selectedItem;
  }

  /**
   * Gives the selected item to a target unit.
   *
   * @param x horizontal position of the target
   * @param y vertical position of the target
   */
  public void giveItemTo(int x, int y) {
    IUnit selected = currentPlayer.getSelectedUnit();
    Location cell = map.getCell(x, y);
    if (!cell.isEmpty()) {
      IUnit unit = cell.getUnit();
      selected.giveItem(selectedIndex, unit);
    }
  }
}
