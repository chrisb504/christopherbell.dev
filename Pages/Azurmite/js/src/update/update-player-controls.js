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
