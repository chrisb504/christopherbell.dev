        // spawn funky flowers
        funkyFlowers = game.add.group();
        funkyFlowers.enableBody = true;
        funkyFlowers.physicsBodyType = Phaser.Physics.ARCADE;
        for(var i=11; i < 26; i++) {
            funkyFlower = funkyFlowers.create(i*400, 5, "funky-flower");
            funkyFlower.body.gravity.y = 250;
            funkyFlower.animations.add("spin", [4, 3, 2, 1, 0, 1, 2, 3, 4], 8, true);
            funkyFlower.animations.play("spin");
        }

        // give flowers some amo
        funkyBullets = game.add.group();
        funkyBullets.enableBody = true;
        funkyBullets.physicsBodyType = Phaser.Physics.ARCADE;
        funkyBullets.createMultiple(999, "funky-bullet");
        funkyBullets.setAll("outOfBoundsKill", true);
        funkyBullets.setAll("checkWorldBounds", true);


        // Give the player ammo*
        playerBullets = game.add.group();
        playerBullets.enableBody = true;
        playerBullets.physicsBodyType = Phaser.Physics.ARCADE;
        playerBullets.createMultiple(999, "player-bullet");
        playerBullets.setAll("outOfBoundsKill", true);
        playerBullets.setAll("checkWorldBounds", true);
