    function Player(playerHealth, playerLevel, playerName, playerStamina, playerStrength) {
        this.playerHealth = playerHealth;
        this.playerLevel = playerLevel;
        this.playerName = playerName;


        this.gainHealth = function(amountToGain) {
            this.playerHealth += amountToGain;
        }

        this.getPlayerHealth = function() {
            return this.playerHealth;
        }

        this.getPlayerName = function() {
            return this.playerName;
        }

        this.reduceHealth = function(amountToReduce) {
            this.playerHealth -= amountToReduce;
        }

        this.setPlayerHealth = function(newHealth) {
            this.playerHealth = newHealth;
        }

        this.setPlayerName = function(newName) {
            this.playerName = newName;
        }
    }