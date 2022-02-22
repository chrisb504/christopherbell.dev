import Attribute from '../Attribute.mjs'

export default class ActionableEntity {
    constructor(name) {
        this.DEFAULT_HEALTH = 10
        this.DEFAULT_NAME = "Player"
        this.DEFAULT_BASIC_ATTACK = 1
        this.DEFAULT_SPECIAL_ATTACK = 2
        this.DEFAULT_LEVEL = 1

        this.name = name;
        this.health = new Attribute("Health", 10);
        this.level = new Attribute("Level", 1);
        this.luck = new Attribute("Luck", 1);
        this.stamina = new Attribute("Stamina", 1);
        this.strength = new Attribute("Strength", 1);

        this.basicAttack = 1;
        this.specialAttack = 2;
    }

    isEntityAlive() {
        return this.health > 0;
    }

    levelup() {
        this.health += 5;
        this.level += 1;
        this.luck += 1;
        this.stamina += 2;
        this.strength += 2;
    }

    reduceHealth(amountReduced) {
        this.health -= amountReduced;
    }

    reduceLevel(levelReduced) {
        this.level -= levelReduced
    }
}