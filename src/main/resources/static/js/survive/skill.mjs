export default class Skill {
    constructor(name, level) {
        this.name = name;
        this.level = level;
        this.exp = 0;
    }

    gainExp(expGained) {
        this.exp += expGained;
    }
}