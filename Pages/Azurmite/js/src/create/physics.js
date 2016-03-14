        game.physics.startSystem(Phaser.Physics.ARCADE);

        game.physics.arcade.enable(player);
        player.body.collideWorldBounds = true;
        player.anchor.setTo(0.5, 0.5);
        player.body.gravity.y = 1600;

        game.physics.arcade.enable(diamond);
        diamond.body.collideWorldBounds = true;
        diamond.body.bounce.y = 0.5;
        diamond.body.gravity.y = 350;

        game.physics.arcade.enable(funkyBullet);

        game.physics.arcade.enable(blackFlower);
        blackFlower.body.bounce.y = 0.8;
        blackFlower.body.gravity.y = 250;
