var express = require('express');
var router = express.Router();
var mainTitle = 'What\'s For Lunch?';

/* GET home page. */
router.get('/', function (req, res, next) {
    res.render('index', {
        title: mainTitle
    });
});

router.get('/add', function (req, res, next) {
    res.render('add', {
        title: mainTitle
    });
});

router.get('/delete', function (req, res, next) {
    res.render('delete', {
        title: mainTitle
    });
});


module.exports = router;