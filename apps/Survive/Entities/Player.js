    function Player(playerHealth, playerLevel, playerName, playerStamina, playerStrength) {
        this.playerHealth = playerHealth;
        this.playerLevel = playerLevel;
        this.playerName = playerName;

        this.getPlayerHealth = function() {
            return this.playerHealth;
        }

        this.getPlayerName = function() {
            return this.playerName;
        }

        this.setPlayerHealth = function(newHealth) {
            this.playerHealth = newHealth;
        }

        this.setPlayerName = function(newName) {
            this.playerName = newName;
        }
    }

    Player.prototype.reduceHealth(amountToReduce) {
        this.playerHealth -= amountToReduce;
    }

    Player.prototype.gainHealth(amountToGain) {
        this.playerHealth += amountToGain;
    }