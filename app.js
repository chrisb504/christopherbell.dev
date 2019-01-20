var express = require('express');
var path = require('path');
var logger = require('morgan');
var cookieParser = require('cookie-parser');
var bodyParser = require('body-parser');

// Database
var mongo = require('mongodb');
var monk = require('monk');
var db = monk('localhost:27017/');

// Routers
var indexRouter = require('./routes/index');
var blogRouter = require('./routes/blog/blog');
var blogPostRouter = require('./routes/blog/post');
var restaurantsRouter = require('./routes/whats-for-lunch/restaurants');
var whatsForLunchRouter = require('./routes/whats-for-lunch/whats-for-lunch');

var app = express();
var dirname = './';

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

app.use("/apps", express.static(path.join(__dirname, 'apps')));
app.use("/views", express.static(path.join(__dirname, 'views')));
app.use("/public", express.static(path.join(__dirname, 'public')));


app.use('/', indexRouter);
app.use('/blog', blogRouter);
app.use('/blog/post', blogPostRouter);
app.use('/whats-for-lunch', whatsForLunchRouter);
app.use('/whats-for-lunch/restaurants', restaurantsRouter);

/// Catch 404 and forwarding to error handler
app.use(function (req, res, next) {
    var err = new Error('Not Found');
    err.status = 404;
    next(err);
});

/// Error Handlers
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