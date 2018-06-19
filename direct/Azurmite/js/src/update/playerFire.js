    function playerFire() {
        var bullet = this.add.sprite(this.player.x, this.player.y - 20, 'player-bullet');

        bullet.anchor.setTo(0.5, 0.5);
        this.physics.enable(bullet, Phaser.Physics.ARCADE);
        bullet.body.velocity.y = -500;
        this.bullets.push(bullet);
    }
