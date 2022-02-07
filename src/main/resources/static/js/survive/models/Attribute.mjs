export default class Attribute {
    constructor(name, level) {
        this.name = name;
        this.level = level;
        this.exp = 0;
        this.expToNextLevel = 5;
    }

    gainExp(expGained) {
        this.exp += expGained;
    }

    levelUp() {
        this.level += 1
    }
}