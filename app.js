const express = require('express');
const path = require('path');
const logger = require('morgan');
const cookieParser = require('cookie-parser');
const bodyParser = require('body-parser');

// Database
const mongo = require('mongodb');
const monk = require('monk');

const db = monk('localhost:27017/');

// Routers
const indexRouter = require('./routes/index');
const blogRouter = require('./routes/blog/blog');
const blogPostRouter = require('./routes/blog/post');
const restaurantsRouter = require('./routes/whats-for-lunch/restaurants');
const whatsForLunchRouter = require('./routes/whats-for-lunch/whats-for-lunch');

const app = express();

// Make our db accessible to our router
app.use(function (req, res, next) {
    req.db = db;
    next();
});

// View engine setup
app.set('views', path.join(__dirname, 'views/pug/whats-for-lunch'));
app.set('view engine', 'pug');

app.use(logger('dev'));
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({
    extended: false
}));
app.use(cookieParser());

app.use('/apps', express.static(path.join(__dirname, 'apps')));
app.use('/views', express.static(path.join(__dirname, 'views')));
app.use('/public', express.static(path.join(__dirname, 'public')));

app.use('/', indexRouter);
app.use('/blog', blogRouter);
app.use('/blog/post', blogPostRouter);
app.use('/whats-for-lunch', whatsForLunchRouter);
app.use('/whats-for-lunch/restaurants', restaurantsRouter);

// Catch 404 and forwarding to error handler
app.use(function (req, res, next) {
    const err = new Error('Not Found');
    err.status = 404;
    next(err);
});

// Error Handlers
if (app.get('env') === 'development') {
    app.use(function (err, req, res, next) {
        res.status(err.status || 500);
        res.render('error', {
            message: err.message,
            error: err,
            title: 'Azurras'
        });
    });
} else {
    app.use(function (err, req, res, next) {
        res.status(err.status || 500);
        res.render('error', {
            message: err.message,
            error: {},
            title: 'Azurras'
        });
    });
}

module.exports = app;
