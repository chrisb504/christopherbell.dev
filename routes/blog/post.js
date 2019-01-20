const express = require('express');

const router = express.Router();

/*
 * GET userlist.
 */
router.get('/', (req, res) => {
    const db = req.db;
    const collection = db.get('blogpost');
    collection.find({}, {}, (e, docs) => {
        res.json(docs);
    });
});

/*
 * POST to adduser.
 */
router.post('/add', (req, res) => {
    const db = req.db;
    const collection = db.get('blogpost');
    collection.insert(req.body, (err, result) => {
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
router.delete('/delete/:id', (req, res) => {
    const db = req.db;
    const collection = db.get('blogpost');
    const restaurantToDelete = req.params.id;
    collection.remove({
        _id: restaurantToDelete
    }, (err) => {
        res.send((err === null) ? {
            msg: ''
        } : {
            msg: `error: ${err}`
        });
    });
});

module.exports = router;
