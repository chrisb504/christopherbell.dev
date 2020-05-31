const express = require('express');

const router = express.Router();
const mainTitle = 'What\'s For Lunch?';

/* GET home page. */
router.get('/', (req, res, next) => {
    res.render('whats-for-lunch/index', {
        title: mainTitle
    });
});

router.get('/add', (req, res, next) => {
    res.render('whats-for-lunch/add', {
        title: mainTitle
    });
});

router.get('/delete', (req, res, next) => {
    res.render('whats-for-lunch/delete', {
        title: mainTitle
    });
});

module.exports = router;