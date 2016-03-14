        demoBackgroundImage = game.add.tileSprite(0, -125, game.width, game.width, "sky");
        demoBackgroundImage.fixedToCamera = true;

        pleasantPlain = game.add.tilemap("pleasantPlain");
        pleasantPlain.addTilesetImage("phaser-tileset");
        pleasantPlain.addTilesetImage("phaser-tileset-small");
        pleasantPlain.setCollisionBetween(0, 1000);
        layerDemoBackgroundObjects = pleasantPlain.createLayer("Background");
        layerDemoLevelPlatforms = pleasantPlain.createLayer("Level");

        // Matches the world to the size of the layer
        layerDemoLevelPlatforms.resizeWorld();

        diamond = game.add.sprite(95, 1000, "diamond");
        funkyBullet = game.add.sprite(0, 0, "funky-bullet");
        blackFlower = game.add.sprite(8000, 50, "black-flower");
        player = game.add.sprite(100, 4, "dude");
        playerBullet = game.add.sprite(0,0, "player-bullet");

        layerDemoForegroundObjects = pleasantPlain.createLayer("Foreground");
