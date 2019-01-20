const express = require('express');

const router = express.Router();

/* GET home page. */
router.get('/', (req, res, next) => {
    res.sendFile('/index.html', {
        root: './views/html/'
    });
});

router.get('/resume', (req, res, next) => {
    res.sendFile('/resume.html', {
        root: './views/html/'
    });
});

router.get('/azurmite', (req, res, next) => {
    res.sendFile('/azurmite.html', {
        root: './apps/azurmite/views/'
    });
});

router.get('/azurmite-game', (req, res, next) => {
    res.sendFile('/azurmite-game.html', {
        root: './apps/azurmite/views/'
    });
});

module.exports = router;
