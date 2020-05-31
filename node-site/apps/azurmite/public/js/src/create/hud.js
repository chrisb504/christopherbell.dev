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
