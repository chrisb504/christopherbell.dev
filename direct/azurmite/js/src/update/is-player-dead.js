        function isPlayerDead() {
            if(playerHealth <= 0) {
                player.kill();
                player = game.add.sprite(180, 5, "dude");
            }
        }
