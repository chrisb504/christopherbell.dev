        demoBackgroundImage = game.add.tileSprite(0, -125, game.width, game.width, "sky");
        demoBackgroundImage.fixedToCamera = true;

        demoLevel = game.add.tilemap("demoLevel");
        demoLevel.addTilesetImage("phaser-tileset");
        demoLevel.addTilesetImage("phaser-tileset-small");
        demoLevel.setCollisionBetween(0, 1000);
        layerDemoBackgroundObjects = demoLevel.createLayer("Background");
        layerDemoLevelPlatforms = demoLevel.createLayer("Level");

        // Matches the world to the size of the layer
        layerDemoLevelPlatforms.resizeWorld();

        diamond = game.add.sprite(95, 600, "diamond");
        funkyBullet = game.add.sprite(0, 0, "funky-bullet");
        blackFlower = game.add.sprite(800, 50, "black-flower");
        player = game.add.sprite(100, 4, "dude");

        layerDemoForegroundObjects = demoLevel.createLayer("Foreground");

        game.physics.overlap(player.sprite, funkFlower.sprite, player.Kill(), null, this);
