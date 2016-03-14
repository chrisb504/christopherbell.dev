        game.physics.arcade.overlap(player, diamond, function(){
            diamond.kill();
            powerDiamonds += 1;
            powerDiamondsText.text = "Diamonds: " + powerDiamonds;

            player.kill();
        }, null, this);
