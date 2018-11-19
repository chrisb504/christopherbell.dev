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
