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
