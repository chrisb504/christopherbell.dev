const express = require('express');

const router = express.Router();

let lastUpdatedDate = new Date();
let chosenRestaurant = {};

function generateRandomRestaurant(data) {
    const uniqueRestaurants = {};
    let keys;

    for (let i = 0; i < data.length; i += 1) {
        uniqueRestaurants[data[i].name] = data[i];
    }

    keys = Object.keys(uniqueRestaurants);
    chosenRestaurant = uniqueRestaurants[keys[keys.length * Math.random() << 0]];
    return chosenRestaurant;
}

/*
 * GET userlist.
 */
router.get('/', (req, res) => {
    const db = req.db;
    const collection = db.get('restaurants');
    collection.find({}, {}, (e, docs) => {
        let data = docs;
        console.log(req.cookies);
        console.log(process.uptime());
        // console.log(chosenRestaurant.restaurant)
        if (lastUpdatedDate.getDate() !== new Date().getDate()) {
            chosenRestaurant = generateRandomRestaurant(data);
            lastUpdatedDate = new Date();
            console.log(lastUpdatedDate);
        }
        if (parseInt(process.uptime()) < 30) {
            chosenRestaurant = generateRandomRestaurant(data);
            console.log(chosenRestaurant);
        }
        if (chosenRestaurant.name && chosenRestaurant._id) {
            console.log('Sending Cookie out');
            res.cookie('selectedRestaurantName', chosenRestaurant.name.toString());
            res.cookie('selectedRestaurantID', chosenRestaurant._id.toString());
        }
        res.json(docs);
    });
});

/*
 * POST to adduser.
 */
router.post('/add', (req, res) => {
    const db = req.db;
    const collection = db.get('restaurants');
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
    const collection = db.get('restaurants');
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
