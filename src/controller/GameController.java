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
  private int currentRound=1;
  private int maxRounds;
  private int numberOfPlayers;

  /**
   * Creates the controller for a new game.
   *
   * @param numberOfPlayers
   *     the number of players for this game
   * @param mapSize
   *     the dimensions of the map, for simplicity, all maps are squares
   */
  public GameController(int numberOfPlayers, int mapSize) {
    this.numberOfPlayers=numberOfPlayers;
    createMap(mapSize);
    setNewGame(numberOfPlayers);
  }

  public void setTurns(int numberOfPlayers, long randomSeed) {
    List<Tactician> copy = new ArrayList<>();
    for(int i =0; i<getTacticians().size();i++){
      Tactician clone = getTacticians().get(i);
      copy.add(clone);
    }
    Random ran = new Random(randomSeed);
    for(int i=0; i<numberOfPlayers; i++){
      int sel = ran.nextInt(copy.size()+1);
      Tactician selected = getTacticians().get(sel);
      turns.add(selected);
      copy.remove(selected);
      if(i==0) {
        if(firstPlayer!=null) {
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
   * @param mapSize
   *      the map's size (mapSize x mapSize)
   */
  public void createMap(int mapSize) {
    for(int i=0;i<mapSize;i++){
      for(int j=0;j<mapSize;j++){
        map.addCells(false, new Location(i,j));
      }
    }
  }

  /**
   * Creates the players for a new game.
   * @param numberOfPlayers
   *      the amount of players to be created for the game
   */
  private void createPlayers(int numberOfPlayers) {
    for(int i=0; i<numberOfPlayers; i++){
      Tactician tactician= new Tactician("Player " + i);
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
   * @param tactician
   *      the tactician to be added
   */
  public void addTactician(Tactician tactician){
    tacticians.add(tactician);
  }

  /**
   * @return the map of the current game
   */
  public Field getGameMap() {
    return map;
  }

  /**
   * @return the tactician that's currently playing
   */
  public Tactician getTurnOwner() {
    return currentPlayer;
  }

  public List<Tactician> getTurns(){
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
    if(turnsLeft-1==0){
      turnsLeft=getTacticians().size();
      setTurns(getTacticians().size(), (long) 0.000001);
      currentRound++;

    }
    else{
      turnsLeft--;
    }
    turns.remove(0);
    currentPlayer = turns.get(0);
  }

  /**
   * Removes a tactician and all of it's units from the game.
   *
   * @param tactician
   *     the player to be removed
   */
  public void removeTactician(String tactician) {
    int size = getTacticians().size();
    for(int i=0; i<size-1;i++){
      Tactician current = getTacticians().get(i);
      String name = getTacticianName(i);
      if(name.equals(tactician)){
        getTacticians().remove(current);
      }
    }
    for(int i=0;i<turnsLeft;i++){
      Tactician toCompare = turns.get(i);
      if(tactician.equals(toCompare.getName())){
        turns.remove(toCompare);
        turnsLeft--;
      }
    }
  }

  public int tacticiansLeft(){
    return getTacticians().size();
  }

  public String getTacticianName(int pos){
    return getTacticians().get(pos).getName();
  }

  /**
   * Starts the game.
   * @param maxTurns
   *  the maximum number of turns the game can last
   */
  public void initGame(final int maxTurns) {
    this.maxRounds=maxTurns;
    this.currentRound = 1;
    setNewGame(numberOfPlayers);
  }

  /**
   * Starts a game without a limit of turns.
   */
  public void initEndlessGame() {
    this.maxRounds=-1;
    this.currentRound = 1;
    setNewGame(numberOfPlayers);
  }

  public void setNewGame(int numberOfPlayers /** agregar randomSeed
   */){
    tacticians = new ArrayList<>();
    turns = new ArrayList<>();
    createPlayers(numberOfPlayers);
    turnsLeft = numberOfPlayers;
    setTurns(numberOfPlayers, (long) 0.000001);
  }

  /**
   * @return the winner of this game, if the match ends in a draw returns a list of all the winners
   */
  public List<String> getWinners() {
    List<String> winners = new ArrayList<>();
    if(tacticiansLeft()==1){
      winners.add(getTacticianName(0));
    }
    else if(currentRound>maxRounds && maxRounds!=-1){
      int max = 0;
      for(int i=0;i<tacticiansLeft();i++){
        Tactician current = tacticians.get(i);
        if(current.getUnits().size()>max){
          max=current.getUnits().size();
        }
      }
      for(int i=0;i<tacticiansLeft();i++) {
        Tactician current = tacticians.get(i);
        if(current.getUnits().size()==max){
          winners.add(getTacticianName(i));
        }
      }
    }
    if(winners.isEmpty()){
      winners = null;
    }
    return winners;
  }

  /**
   * @return the current player's selected unit
   */
  public IUnit getSelectedUnit() {
    return null;
  }

  /**
   * Selects a unit in the game map
   *
   * @param x
   *     horizontal position of the unit
   * @param y
   *     vertical position of the unit
   */
  public void selectUnitIn(int x, int y) {

  }

  /**
   * @return the inventory of the currently selected unit.
   */
  public List<IEquipableItem> getItems() {
    return null;
  }

  /**
   * Equips an item from the inventory to the currently selected unit.
   *
   * @param index
   *     the location of the item in the inventory.
   */
  public void equipItem(int index) {

  }

  /**
   * Uses the equipped item on a target
   *
   * @param x
   *     horizontal position of the target
   * @param y
   *     vertical position of the target
   */
  public void useItemOn(int x, int y) {

  }

  /**
   * Selects an item from the selected unit's inventory.
   *
   * @param index
   *     the location of the item in the inventory.
   */
  public void selectItem(int index) {

  }

  /**
   * Gives the selected item to a target unit.
   *
   * @param x
   *     horizontal position of the target
   * @param y
   *     vertical position of the target
   */
  public void giveItemTo(int x, int y) {

  }
}
