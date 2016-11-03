    var game = new Phaser.Game(
        1350, 
        700, 
        Phaser.AUTO, 
        "azurmite", 
        { 
            preload: preload,
            create: create,
            update: update
        }
    );
