var express = require('express');
var router = express.Router();
var mainTitle = 'Azurras';

/* GET home page. */
router.get('/', function (req, res, next) {
    res.sendFile('/index.html', {root: './views/html/'});
});

router.get('/resume', function (req, res, next) {
    res.sendFile('/resume.html', {root: './views/html/'});
});

router.get('/azurmite', function (req, res, next) {
    res.sendFile('/azurmite.html', {root: './apps/azurmite/views/'});
});

router.get('/azurmite-game', function (req, res, next) {
    res.sendFile('/azurmite-game.html', {root: './apps/azurmite/views/'});
});

module.exports = router;