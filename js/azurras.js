function Main() {
	console.log("Hello World");
}

function SurivialGame() {
	function Player(playerName, playerHealth) {
		this.playerName = playerName;
		this.playerHealth = playerHealth;
		this.reduceHealth = function(amountToReduce) {
			this.playerHealth -= amountToReduce;
		}
	}

	function Inventory() {

	}
	
	function Enemy(enemyName, enemyHealth) {
		this.enemyName = enemyName;
		this.enemyHealth = enemyHealth
	}

	// Test stuff
	player = new Player("Chris", 10);
	player.reduceHealth(5);
	console.log(player.playerHealth);
}

