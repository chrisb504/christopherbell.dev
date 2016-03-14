        game.physics.arcade.collide(player, layerDemoLevelPlatforms);
        game.physics.arcade.TILE_BIAS = 10;
        if (player.body.velocity.y > 600) {
            game.physics.arcade.TILE_BIAS = 400;
        }

        game.physics.arcade.collide(diamond, layerDemoLevelPlatforms);
        game.physics.arcade.collide(funkyFlowers, layerDemoLevelPlatforms);
        game.physics.arcade.collide(blackFlower, layerDemoLevelPlatforms);

        game.physics.arcade.collide(player, funkyFlowers);
        game.physics.arcade.collide(player, funkyBullets, kill, null, this);
        //game.physics.arcade.collide(funkyFlowers, playerBullets, funkyFlowerKill, null, this);
        game.physics.arcade.collide(blackFlower, playerBullets, blackFlowerKill, null, this);

        function kill() {
            player.kill();
        }

        function funkyFlowerKill() {
            funkyFlowers.kill();
        }

        function blackFlowerKill() {
            blackFlower.kill();
        }
