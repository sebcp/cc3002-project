# Alpaca Emblem V1.0

Alpaca Emblem is the main project of course CC3002. It's a top-down strategy game in which you control different units with unique skills.

## Getting Started

Download the last release tagged as [Tarea2](https://github.com/sebcp/cc3002-project/releases/tag/Tarea2).

### Prerequisites

Java, JUnit 5.2

## Design Ver 2.0

The main model was barely changed, the changes consist of the following:
* Units now can only move and act (attack, heal, give an item) once per turn.
* Location now has a new method to compare neighbours.

The new parts of the game are the following:
* GameController, the one in charge of the state of the game.
* Tacticians, the class that represents players.
* Factory classes for units and items for easy and safe creation.
* Handlers that manage the change of state of things like units dying or tacticians not being able to keep playing due to losing all their units or losing any of their heroes.

The game controller is responsible for the game's state at every given moment. It does it by being the one who instantiates the map, the tacticians and everything else via factories.
The game flow is the following:
* Create a GameController giving it the ammount of players and the size of the map:
- The game controller will then proceed to create a randomly connected map.
- Tacticians should be able to choose their units and the items to equip them. THIS IS NOT IMPLEMENTED YET.
- The game controller will get a random long number and then set the turns in an order that depends on the generated random number.
- Tacticians will be able to move and act with all their units once per turn.
- Whenever a unit dies, it will be removed from it's tacticians unit list.
- Whenever a tactician loses any of it's heroes or loses all it's units it is removed from the current game.
- The game is played until a single tactician is left or the game reaches it's maximum number of rounds (only for initGame).


Factories only create units and items in a safe and controlled way. Item factories can set the range, power, name and the unit that the item will belong to once it's created. 
As for unit factories, they can set their maximum health, maximum movement range, name, initial location and the tactician that will own it once it's created. When a tactician is designed as the owner it will create a handler (UnitDeathHandler in the case of any common unit and HeroDeathHandler in the case of hero units) that will receive a message whenever the unit dies and will send a message asking to remove the unit from the tacticians list.
Clearly, factories use the factory pattern.

There is also a game controller handler that receives a message whenever a tactician should lose the game (i.e. be removed from the current game) that will ask for the tactician to be removed whenever it happens. The pattern used is observer, where the game controller is an observer of every tactician on the game, the tacticians are both being observerd by the controller and are observing all their units.

## Running the tests

There is a test package for every new package, right-click any of them (or all of them) and then click Run '...Test with coverage', choose all.

## The tests

Tests were designed to check functionalities of the new packages.

GameController tests are as they follow:
* Checks that the getter for the tacticians works properly.
* Checks that the getter for the map works properly. Also checks that two maps made with the same seed are the same.
* Checks that the getter for the turn owner works properly. Also checks that two GameControllers with the same seed create the same list of turns.
* Checks that the getter for the rounds and the turn-round logic works properly.
* Checks that the max rounds are properly set whenever using initGame or initEndlessGame.
* Checks that ending turns works properly. If a player was the first one to act in the last round it cannot play first again.
* Checks that removing a tactician properly works (i.e. whenever prompted to erase a valid tactician it erases it from the game and whenever prompted to erase a non-valid tactician it doesn't do anything).
* Checks that getWinners properly works. If the game ends and there's more than one player left, it will count the units of each of them and choose the one (or more) tactician with the most units. 
* Checks that getSelectedUnit works. Tacticians will only be able to choose their own units that haven't done both moving and acting.
* Checks that the controller properly let's the current tactician to control (do any possible action defined on the main model) it's units.

Factory tests are quite simple and they're conceptually pretty much the same for the items and the units. The tests are as they follow:
* Check the creation of the default item or unit.
* Check the creation of an item or unit with specified characteristics. For items it considers a different value for power, max range and min range, name and owner. For units it considers different values for max health, max movement range, name, initial location and the tactician that owns the unit.


Handler tests are as they follow:
* Check the HeroDeathHandler works properly when the hero dies on a turn from a different tactician.
* Check that the HeroDeathHandler works properly when the hero dies on the tactician's turn.
* Check that UnitDeathHandler is properly working when a unit dies on a turn from a different tactician.
* Checks that the UnitDeathHandler works properly when the hero dies on the tactician's turn.
The last handler is used on both the testing of HeroDeathHandler and UnitDeathHandler, whenver a tactician should lose due to the loss of units it will send a message to the controller removing it from the game.

## Design Ver 1.0

* Units must have over 0 max health points and movement (i.e.: units cannot be created dead or with a movement less or equal than 0).
* Units have a boolean, isAlive, that describes their current state. It's true if their hp hasn't fallen to 0; it's false if it has.
* Units equip items through double dispatch, if the unit is alive and it has the item in it's inventory, it sends a message to the item.
If the item "matches" the class, it's equipped (for example, when an archer tries to equip a bow, it'll work).
* Units can combat other units if they're both alive. If the target is in range and the attacker has an item equiped, the target will be attacked. Unless the target doesn't have an item equipped, after being attacked, it's hp is checked and if it's still alive it'll counter the attack. If the target doesn't have an item equipped, it will just receive the damage of the item the unit's holding.
When attacked by a cleric with an equipped staff, the cleric will heal the unit and this unit won't counterattack.
* The units' attack is also done through double dispatch, the unit sends a message to its' item saying it will attack the target's item. Then the unit's item sends a message to the target's item saying it will attack it, when received, the target's item sends a message to the targeted unit making it receive the calculated damage.
* The damage a unit should received when attacked by an item is calculated as it follows: When attacked by a weapon that's strong against yours, you receive 1.5 times the damage. When attacked by a weapon that's weak against yours, you receive 20 less damage. 
The weakness/resistance tables are the following:

| Item   | Strong against| Weak against | 
| ------ | ------------- | ------------ | 
| Axe    | Spear         | Sword        | 
| Sword  | Axe           | Spear        | 
| Spear  | Sword         | Axe          | 

| SpellBook  | Strong against| Weak against | 
| ---------- | ------------- | ------------ | 
| Anima      | Luz           | Oscuridad    | 
| Oscuridad  | Anima         | Luz          | 
| Luz        | Oscuridad     | Anima        | 

Receiving negative damage heals the character.

* Staffs MUST have negative power.
* Receiving healing and receiving damage is very similar, a unit's current hp cannot be greater than it's maximum hp and it cannot be lesser than 0. Whenever this might happen, their hp is set to the max or to 0 respectively.
* SpellBooks are a ranged item, i.e. their minimum range must be greater or equal than 2, just like bows.

Double dispatch is used on the attack and the equip method because it is an easy way of disambiguating the type of the item that's being used to attack or being equipped, the only downside is the use of several auxiliary methods. It also provides a simple base for new items.
For the items, there are four abstract classes that define the common behaviour of currently listed items: AbstractAttackAbleItems, AbstractNonAttackAbleItems, AbstractRangedAttackAbleItems and AbstractSpellBook.
AbstractAttackAbleItems defines the behaviour of the items that can attack. AbstractRangedAttackAbleItems extends AbstractAttackAbleItems and defines a constructor in which the minimum range is always at least 2. AbstractSpellBook extends AbstractRangedAttackAbleItems and defines the behaviour for spell books. AbstractNonAttackAbleItems defines the behaviour of the items that can't attack.
The way they're defined provides a simple way of creating new items that can either attack or not and be ranged or not.

## Running the tests

There is a test package for every package on the model. For the item and unit test packages, 
right-click AbstractTestItem or AbstractTestUnit and then click Run 'AbstractTest with coverage', choose all.
For the map package, select both test classes at the same time, right-click on them and choose 'Run package with coverage'.

### The tests

Tests were created to design and check the functionalities of the logical model.
Items are tested as it follows:
* Checks that the constructors are properly working, items cannot have a range less than 0 and their maximum range should be greater than it's minimum range.
* Checks that the items are attacking properly, items can only attack other items that are already owned by another unit and they cannot attack themselves. There are some items like staffs that cannot attack.
* Checks that the items equals method is properly working.
* Checks the items' weaknesses and resistances. If they're weak to the attack, they should receive 1.5 times the damage, if they're resistance to the attack they should receive 20 points less of damage.
* Checks that they're properly equipped to the different classes. i.e.: Checks that the items only equip to the class that can use them.

Units are tested as it follows:
* Checks that the constructors are properly working.
* Checks that the units can equip the correct type of items.
* Checks that the units can properly combat other units, units cannot combat units that are dead and they cannot combat or counter units that are not in their item's range.
* Checks that units can properly give other units their items. Units can only exchange items if the receiver doesn't have a full inventory and if the item that's been given isn't equipped and is in the units inventory.
* Checks that units are healed properly, units shouldn't be healed over their maximum health points and dead units shouldn't be healed.
* Checks that units can move properly, they can't move more than their movement and a unit cannot move to a cell with another unit in it or an invalid location cell.

## Authors

* **Ignacio Slater** - *Base code* - [islaterm](https://github.com/islaterm)
* **Sebastián Contreras** - *Rest of the model* - [sebcp](https://github.com/sebcp)
