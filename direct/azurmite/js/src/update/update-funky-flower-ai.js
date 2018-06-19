        if (game.time.now > funkyFlowerShootingTimer) {
            funkyFlowerShootingAI();
        }
        funkyBullet.angle -= 40;
        funkyBullet.anchor.setTo(0.5, 0.5);