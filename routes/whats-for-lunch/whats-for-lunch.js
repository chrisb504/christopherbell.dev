const express = require('express');

const router = express.Router();
const mainTitle = 'What\'s For Lunch?';

/* GET home page. */
router.get('/', (req, res, next) => {
    res.render('index', {
        title: mainTitle
    });
});

router.get('/add', (req, res, next) => {
    res.render('add', {
        title: mainTitle
    });
});

router.get('/delete', (req, res, next) => {
    res.render('delete', {
        title: mainTitle
    });
});

module.exports = router;
