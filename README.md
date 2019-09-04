# Alpaca Emblem V0.1

Alpaca Emblem is the main project of course CC3002. It's a top-down strategy game in which you control different units with unique skills.

## Getting Started

Download the last release tagged as [Tarea1](https://github.com/sebcp/cc3002-project/releases/tag/Tarea1).

### Prerequisites

Java, JUnit 5.2

## Design

* Units must have over 0 max health points and movement (i.e.: units cannot be created dead or with a movement less or equal than 0).
* Units have a boolean, isAlive, that describes their current state. It's true if their hp hasn't fallen to 0; it's false if it has.
* Units equip items through double dispatch, if the unit is alive and it has the item in it's inventory, it sends a message to the item.
If the item "matches" the class, it's equipped (for example, when an archer tries to equip a bow, it'll work).
* Units can combat other units if they're both alive. Before every attack, the hp of the combatants is checked, if any of them has 0 hp the combat stops. If the target is in range and the attacker has an item equiped, the target will be attacked.
Unless the target doesn't have an item equipped, after being attacked, it's hp is checked, if it's alive it'll counter the attack. If the target doesn't have an item equipped it
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

* Receiving healing and receiving damage is very similar, a unit's current hp cannot be greater than it's maximum hp and it cannot be lesser than 0. Whenever this might happen, their hp is set to the max or to 0 respectively.

Double dispatch is used on the attack and the equip method because it is an easy way of disambiguating the type of the item that's being used to attack or being equipped, the only downside is the use of several auxiliary methods. It also provides a simple base for new items.
For the items, there are four abstract classes that define the common behaviour of currently listed items: AbstractAttackAbleItems, AbstractNonAttackAbleItems, AbstractRangedAttackAbleItems and AbstractSpellBook.
AbstractAttackAbleItems defines the behaviour of the items that can attack. AbstractRangedAttackAbleItems extends AbstractAttackAbleItems and defines a constructor in which the minimum range is always at least 2. AbstractSpellBook extends AbstractRangedAttackAbleItems and defines the behaviour for spell books. AbstractNonAttackAbleItems defines the behaviour of the items that can't attack.
The way they're defined provides a simple way of creating new items that can either attack or not and be ranged or not.

## Running the tests

There is a test package for every package on the model. For the item and unit test packages, 
right-click AbstractTestItem or AbstractTestUnit and then click Run 'AbstractTest with coverage', choose all.
For the map package, select both test classes at the same time, right-click on them and choose 'Run package with coverage'.

### Break down into end to end tests

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
