const express = require('express');

const router = express.Router();

/*
 * GET all post.
 */
router.get('/', (req, res) => {
    const db = req.db;
    const collection = db.get('blogpost');
    collection.find({}, {}, (e, docs) => {
        res.json(docs);
    });
});

/*
 * GET all post related to a given tag
 */
router.get('/tags/:tag', (req, res) => {
    const db = req.db;
    const collection = db.get('blogpost');
    const selectedTag = req.params.tag;
    const tagResponse = [];
    collection.find({}, {}, (e, docs) => {
        docs.map((posting) => {
            if (posting.hasOwnProperty('tags')) {
                const tagArray = posting.tags.split(',');
                tagArray.map((tag) => {
                    if (tag === selectedTag) {
                        tagResponse.push(posting);
                    }
                });
            }
        });
        res.send(tagResponse);
    });
});

/*
 * GET all tags
 */
router.get('/tags', (req, res) => {
    const db = req.db;
    const collection = db.get('blogpost');
    const tagResponse = [];
    collection.find({}, {}, (e, docs) => {
        docs.map((posting) => {
            if (posting.hasOwnProperty('tags')) {
                const tagArray = posting.tags.split(',');
                tagArray.map((tag) => {
                    if(!tagResponse.includes(tag)) {
                        tagResponse.push(tag);
                    }
                });
            }
        });
        res.send(tagResponse);
    });
});

/*
 * POST to post db.
 */
router.post('/add', (req, res) => {
    const db = req.db;
    const collection = db.get('blogpost');
    console.log('body', req.body);
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
 * DELETE a post.
 */
router.delete('/delete/:id', (req, res) => {
    const db = req.db;
    const collection = db.get('blogpost');
    const postToDelete = req.params.id;
    collection.remove({
        _id: postToDelete
    }, (err) => {
        res.send((err === null) ? {
            msg: ''
        } : {
            msg: `error: ${err}`
        });
    });
});

module.exports = router;
