const express = require('express');

const router = express.Router();
const mainTitle = 'Blog';

router.get('/', (req, res, next) => {
    res.sendFile('/blog.html', {
        root: './views/html/'
    });
});

router.get('/add', (req, res, next) => {
    res.render('blog/add', {
        title: mainTitle
    });
});

router.get('/delete', (req, res, next) => {
    res.render('blog/delete', {
        title: mainTitle
    });
});


module.exports = router;
