(function main() {
    
    var game = new Phaser.Game(
        1000, 
        700, 
        Phaser.AUTO, 
        "azurmite", 
        { 
            preload: preload,
            create: create,
            update: update
        }
    );

    var powerDiamondsText = "";
    var playerHealthText = "";

    var powerDiamonds = 0;

    var playerMovementVelocity = 220;
    var playerJumpVelocity = 620;
    var playerHealth = 3;
    var playerCurrentX = 0;
    var playerCurrentY = 0;
    var playerHitBox = 50;

    var funkyFlowerFieldOfVision = 250;
    var funkyFlowerShootingVelocity = 150;
    var funkyFlowerShootingTimer = 0;
    var funkyFlowerShootingFrequency = 2500;

    var demoLevel; // where the DemoLevel.json level file will be loaded into
    var demoBackgroundImage;
    var layerDemoLevelPlatforms, layerDemoLevelBorder, layerDemoForegroundObjects, layerDemoBackgroundObjects; // demo level layers
    
    var pleasantPlain;

    var player;
    var playerBullets;
    var playerBullet;

    var funkyFlower, funkyFlowers;
    var funkyBullet, funkyBullets;
    var blackFlower;

    var diamond;

	var demoMusic;

    var pauseKey;
    var shootKey;
    var movementKeys; // the up, down, left, and right arrow keys used for movement

    function preload() {

        "use strict";

        game.load.image("sky", "/apps/azurmite/public/images/levels/demo/sky.jpg");
        game.load.image("diamond", "/apps/azurmite/public/images/stock-images/diamond.png");

        /* image key must be same as its name in json file */
        game.load.image("phaser-tileset", "/apps/azurmite/public/images/tilesets/phaser-tileset.png");
        game.load.image("phaser-tileset-small", "/apps/azurmite/public/images/tilesets/phaser-tileset-small.png");
        game.load.image("funky-bullet", "/apps/azurmite/public/images/enemies/bullet.png");
        game.load.image("player-bullet", "/apps/azurmite/public/images/enemies/bullet.png");

        /* update audio load ["assets/testmusic1.mp3", "assets/testmusic1.oog"] firefox needs oog */
        game.load.audio("music-pleasant-plain", "/apps/azurmite/public/audio/various-tracks/chibi-ninja.mp3" );

        game.load.spritesheet("dude", "/apps/azurmite/public/images/main-character/main-character.png", 32, 48);        
        game.load.spritesheet("funky-flower", "/apps/azurmite/public/images/enemies/funky-flower.png", 28, 32);
        game.load.spritesheet("black-flower", "/apps/azurmite/public/images/enemies/black-flower.png", 260, 430); 

        /* 128px by 128px tiles in this set */
        game.load.tilemap("demoLevel", "/apps/azurmite/public/images/levels/demo/DemoLevel.json", null, Phaser.Tilemap.TILED_JSON);
        game.load.tilemap("pleasantPlain", "/apps/azurmite/public/images/levels/pleasant-plain/PleasantPlain.json", null, Phaser.Tilemap.TILED_JSON);

    } /*** end preload wrapper ***/

    function create() {

        "use strict";

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

        game.camera.follow(player);

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

        player.animations.add("left", [0, 1, 2, 3], 12, true);
        player.animations.add("right", [5, 6, 7, 8], 12, true);
        blackFlower.animations.add("move", [0, 1, 2, 3, 4], 8, true);
        blackFlower.animations.play("move");

        powerDiamondsText = game.add.text(16, 16, "Diamonds: 0", { font: "16pt Consolas",
            fill: "#fff",
        });
        powerDiamondsText.fixedToCamera = true;
        powerDiamondsText.cameraOffset.setTo(20, 40);

        playerHealthText = game.add.text(50, 25, "Health: 3", { font: "16pt Consolas",
            fill: "#fff",
        });
        playerHealthText.fixedToCamera = true;
        playerHealthText.cameraOffset.setTo(20, 10);

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

        demoMusic = game.add.audio("music-pleasant-plain");
        demoMusic.loop = true;
        demoMusic.play();

        movementKeys = game.input.keyboard.createCursorKeys();
        pauseKey = game.input.keyboard.addKey(Phaser.Keyboard.P);
        shootKey = game.input.keyboard.addKey(Phaser.Keyboard.Z);

    } /*** end create wrapper ***/

    function update() {

        "use strict";

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

        demoBackgroundImage.tilePosition.x -= 1;

        //game.physics.overlap(player, funkFlower, player.Kill(), null);
        //game.physics.overlap(player, funkFlower);

        player.body.velocity.x = 0;
        if (movementKeys.left.isDown) {
            player.body.velocity.x = playerMovementVelocity*-1;
            // player.body.velocity.x = -500; // for dev
            player.animations.play("left");
        } else if (movementKeys.right.isDown) {
            player.body.velocity.x = playerMovementVelocity;
            // player.body.velocity.x = 500; // for dev
            player.animations.play("right");
        } else {
            player.animations.stop();
            player.frame = 4;
        }

        if (movementKeys.up.isDown && player.body.onFloor()) {
            player.body.velocity.y = playerJumpVelocity*-1;
        }

        if (shootKey.isDown && movementKeys.right.isDown) {
            playerShootToRight();
        }

        else if (shootKey.isDown && movementKeys.left.isDown) {
            playerShootToLeft();
        }

        else if(shootKey.isDown && (!movementKeys.left.isDown || !movement.right.isDown)) {
            playerShootUp();
        }

        if (pauseKey.isDown) {
            game.state.pause = true;
            player.animations.currentAnim.paused = true;
        }

        game.physics.arcade.overlap(player, diamond, function(){
            diamond.kill();
            powerDiamonds += 1;
            powerDiamondsText.text = "Diamonds: " + powerDiamonds;

            player.kill();
        }, null, this);

        if (game.time.now > funkyFlowerShootingTimer) {
            funkyFlowerShootingAI();
        }
        funkyBullet.angle -= 40;
        funkyBullet.anchor.setTo(0.5, 0.5);
    } /*** end update wrapper ***/

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

        function isPlayerDead() {
            if(playerHealth <= 0) {
                player.kill();
                player = game.add.sprite(180, 5, "dude");
            }
        }

        function subtractPlayerHealth() {
            playerHealth = playerHealth - 1;
            playerHealthText.text = "Health: " + playerHealth;
        }

    function funkyFlowerShootingAI() {   
        funkyFlowers.forEachAlive(function (funkyFlower) {
            if (player.body.x < funkyFlower.body.x + funkyFlowerFieldOfVision
                && player.body.x > funkyFlower.body.x - funkyFlowerFieldOfVision) {

                if (player.body.x < funkyFlower.body.x) {
                    funkyBullet = funkyBullets.create(funkyFlower.x, funkyFlower.y + 12, "funky-bullet");
                    funkyBullet.body.velocity.x = funkyFlowerShootingVelocity * -1;
                } else {
                    funkyBullet = funkyBullets.create(funkyFlower.x + 12, funkyFlower.y + 12, "funky-bullet");
                    funkyBullet.body.velocity.x = funkyFlowerShootingVelocity;
                }
                funkyFlowerShootingTimer = game.time.now + funkyFlowerShootingFrequency;
            }
        });
    }

}());
