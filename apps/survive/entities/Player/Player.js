class Player {

    constructor(playerName) {
        this.playerHealth = 10;
        this.playerLevel = 1;
        this.playerLuck = 10;
        this.playerName = playerName;
        this.playerStamina = 10;
        this.playerStrength = 10;
    }

    attack() {
        let damageDone = this.playerStrength / 2;

        return damageDone;
    }

    gainHealth(amountGained) {
        this.playerHealth += amountGained;
    }

    gainLevel(levelGained) {
        this.playerLevel += levelGained;
    }

    levelup(playerHealth, playerLevel, playerLuck,
        playerStamina, playerStrength) {
        this.playerHealth += playerHealth;
        this.playerLevel += playerLevel;
        this.playerLuck += playerLuck;
        this.playerStamina += playerStamina;
        this.playerStrength += playerStrength;
    }

    reduceHealth(amountReduced) {
        this.playerHealth -= amountReduced;
    }

    reduceLevel(levelReduced) {
        this.playerLevel -= levelReduced
    }
}