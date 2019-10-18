package model;

import model.units.IUnit;

import java.util.ArrayList;
import java.util.List;

/**
 * The tactician represents a player of the game.
 * The tactician controls a set of chosen units and viciously fights other tacticians for triumph and honor.
 *
 * @author Sebastián Contreras Phillippi
 * @version 2.0
 * @since 2.0
 */
public class Tactician {
    private List<IUnit> units = new ArrayList<>();
    private String name;
    private IUnit selectedUnit;


    public Tactician(String name){
        this.name=name;
    }

    public String getName(){
        return name;
    }
}
