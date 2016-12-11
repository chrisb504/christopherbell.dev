    function Enemy(enemyHealth, enemyLevel, enemyName, enemyStamina, enemyStrength) {
        this.enemyHealth = enemyHealth;
        this.enemyLevel = enemyLevel;
        this.enemyName = enemyName;
        this.enemyStamina = enemyStamina;
        this.enemyStrength = enemyStrength;

        this.setEnemyHealth = function(newHealth) {
            this.enemyHealth = newHealth;
        }

        this.setEnemyLevel = function(newLevel) {
            this.enemyLevel = newLevel;
        }

        this.setEnemyName = function(newName) {
            this.enemyName = newName;
        }

        this.setEnemyStamina = function(newStamina) {
            this.enemyStamina = newStamina;
        }
    }