var express = require('express');
var router = express.Router();

/*
 * GET userlist.
 */
router.get('/', function (req, res) {
    var db = req.db;
    var collection = db.get('blogpost');
    collection.find({}, {}, function (e, docs) {
        res.json(docs);
    });
});

/*
 * POST to adduser.
 */
router.post('/add', function (req, res) {
    var db = req.db;
    var collection = db.get('blogpost');
    collection.insert(req.body, function (err, result) {
        res.send(
            (err === null) ? {
                msg: ''
            } : {
                msg: err
            }
        );
    });
});

/*
 * DELETE to deleteuser.
 */
router.delete('/delete/:id', function (req, res) {
    var db = req.db;
    var collection = db.get('blogpost');
    var restaurantToDelete = req.params.id;
    collection.remove({
        '_id': restaurantToDelete
    }, function (err) {
        res.send((err === null) ? {
            msg: ''
        } : {
            msg: 'error: ' + err
        });
    });
});

module.exports = router;