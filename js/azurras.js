function Main() {
	console.log("Hello World");
}

function SurivialGame() {

	function Player(playerHealth, playerLevel, playerName, playerStamina, playerStength) {
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


	function Inventory() {

	}

	
	function Enemy(enemyName, enemyHealth) {
		this.enemyName = enemyName;
		this.enemyHealth = enemyHealth
	}

	// Test stuff
	//player = new Player("Chris", 10);
	//player.reduceHealth(5);
	//console.log(player.playerHealth);
}

