import Skill from './skill.mjs'

export default class Entity {
    constructor(name) {
        this.name = name;
        this.health = new Skill("Health", 10);
        this.level = new Skill("Level", 1);
        this.luck = new Skill("Luck", 1);
        this.stamina = new Skill("Stamina", 1);
        this.strength = new Skill("Strength", 1);
    }

    levelup(health, level, luck, stamina, strength) {
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