    function playerShootToRight() {
        playerBullet = playerBullets.create(player.x + 20, player.y + 5, "player-bullet");
        playerBullet.body.velocity.x = 500;
    }

    function playerShootToLeft() {
        playerBullet = playerBullets.create(player.x - 20, player.y + 5, "player-bullet");
        playerBullet.body.velocity.x = -500;
    }

    function playerShootUp() {
        playerBullet = playerBullets.create(player.x, player.y - 20, "player-bullet");
        playerBullet.body.velocity.y = -500;
    }

    function pauseGame() {
        game.state.pause = true;
    }

    detectBulletHit();
