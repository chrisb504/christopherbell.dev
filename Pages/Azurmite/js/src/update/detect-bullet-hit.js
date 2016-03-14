    function detectBulletHit() {
        playerCurrentX = player.body.x;
        playerCurrentY = player.body.y;

        if(funkyBullet.body.x <= (playerCurrentX + playerHitBox) &&
        funkybullet.body.x >= (playerCurrentX - playerHitBox)) {
            //playerHealth = playerHealth - 1;
            //subtractPlayerHealth();
            //isPlayerDead();
            player.kill();
        }

        if(game.physics.overlap(funkyFlower, player)) {
            player.kill();
        }

    }
