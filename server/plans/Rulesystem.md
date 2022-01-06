# Dynamic Rules System

## Idea

The idea behind the Dynamic Rules System is that the rules are designed in a way which is easy to convert into human readable formats, but is stored in a machine readable format. The basis of the entire object-rule system is that the rules designate the kinds of objects which can be stored.

## A Broader Definition of the word 'Rule'
To make this work properly, the word rule means more than just a guideline or law about allowed/banned behaviours. In this system, a rule is anything that designates either fundemental elements of the ruleset, the behavior of those elements, or the constraints on allowed user behavior.

## Kinds of Rules

There are several fundemental kinds of rules.

|  name                |  machine readable id  |
|----------------------|-----------------------|
| No Simulation Effect | `nosim`               |
| Fundamental Element  | `fundamental-element` |
| Behavior             | `behavior`            |
| Constraint           | `constraint`          |
| Attribute            | `attribute`           |
| Constant             | `constant`            |
| Template             | `template`            |

### No Simulation Effect

This rule type is fairly self explanatory. It represents a rule which can be read and quoted by game masters, but it is never used for data definition, action definition, or simulatory checks.

### Fundamental Element

This type of rule defines a fundamental element of the universe created by the ruleset. Some examples might be: Creature, Weapon, Armor, and Spell. These will be used to design the base structures for data.

### Behavior

This type of rule defines the actions that are available as well as the reactions, responses, and various other things that occur during play. These often are coupled to constraints, attributes and fundamental elements. An exampple might be a rule on how walking movement works.

### Constraint

This type of rule defines predicates for when behaviors are available. These rules can also be used as a sort of proxy, allowing different attributes or behaviors to be used based on various in game conditions. Another use is to constrain what an attribute can be attached to.

### Attribute

This type of rule defines a format for specific attachable data. These can be features which take no parameters. An example of this might me a feature which gives whatever it's attached to an advantage on recalling information. There are also attributes which take in input data such as stats. An example of this might be an attribute which allows whatever it's attached to see a specified amount farther in the dark. These often link to behaviors and are often constrained. Another use for attributes is to define kinds statistics which can be used as variables for constraints and behaviors.

### Constant

This kind of rule represents any form of constant data which is set at the ruleset level. A constant-group can be created, which is most useful for describing things like states or predefined choices. An example might be a group of travel methods containing: Walking, Running, Horse, Flying, Swimming.


## How these are put together

All these kinds of rules work together to form the games (except for no simulation effect rules, which either can't be represented in the simulation or are there for reference purposes). There are a couple entry point rules which have no dependencies: Constant and Fundamental Elements. These two are the basis of everything else (mainly fundamental elements). Past those, there is a cyclic dependency between Attributes, Behaviors, and Constraints. The best representation of this would be that Attributes depend upon the existance of behavior rules, are constrained by constraints, and also are used within the definitions of many behaviors. Constraints rely upon the presence of attributes, the validation of other constraints, and are used to constrain the use and effects of behaviors and attributes. Behaviors make use of attributes, and are constrained by constraints.

## What makes it feasible

One of the most important parts of this system is the ability for template rules to be created. almost all of the rules are able to inherit both normal and template rules, which template rules can provide a way to group a number of templated rules together. This allows for builtin abstraction when there are a number of similar rules. As well, this decreases the amount of data which needs to be processed and read from the database for everything, allowing for better caching and speeds. If possible, there may be a tool to optimize a ruleset, which reformats the data for load speeds and simulator comprehension speed.

## How is this going to be done

The database will store JSON data containing rules, which are read into the simulation system and used to process object data. These rules define which fields will be present in JSON data representing the various objects within the simulation.


# JSON Format

## Required fields for all rules
- `type`
- `name`

## Optional Fields for all rules
- `description`

>
> The No Simulation Effect rule type requires the `description` field.
>

## Fundamental Element
>
> TODO
>

## Behavior
- `actions`

### Actions

Actions have a few base actions, and can use them or other actions to build complex actions.

#### `add(numeric a, numeric b) -> numeric`
Add 2 numbers together, can also be written as `a + b`

#### `sub(numeric a, numeric b) -> numeric`
Subtract 2 numbers, can also be written as `a - b`

#### `mul(numeric a, numeric b) -> numeric`
Multiply 2 numbers, can also be written as `a * b`

#### `div(numeric a, numeric b) -> numeric`
Divide 2 numbers, can also be written as `a / b`

#### `mod(numeric a, numeric b) -> int`
Take the modulus of `a` with the dividend `b`, can also be written as `a % b`

#### `floordiv(numeric a, numeric b) -> int`
Divide 2 numbers together, can also be written as `a // b`

#### `floor(numeric a) -> int`
Floor a number

#### `ceil(numeric a) -> int`
Ceiling a number

#### `abs(numeric a) -> numeric`
Take the absolute value of a number

#### `pow(numeric a, numeric b) -> numeric`

#### `sqrt(numeric a) -> numeric`

#### `cbrt(numeric a) -> numeric`

#### `(numeric a) -> numeric`

#### `abs(numeric a) -> numeric`








## Constraint
- `predicate`

## Attribute
- `parameters`



