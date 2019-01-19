var express = require('express');
var router = express.Router();

var lastUpdatedDate = new Date();
var chosenRestaurant = {};

/*
 * GET userlist.
 */
router.get('/', function (req, res) {
    var db = req.db;
    var collection = db.get('restaurants');
    collection.find({}, {}, function (e, docs) {
        var data = docs;
        console.log(req.cookies);
        console.log(process.uptime());
        //console.log(chosenRestaurant.restaurant)
        if(lastUpdatedDate.getDate() != new Date().getDate()) {
            chosenRestaurant = generateRandomRestaurant(data);
            lastUpdatedDate = new Date();
            console.log(lastUpdatedDate);
        }
        if(parseInt(process.uptime()) < 30) {
            chosenRestaurant = generateRandomRestaurant(data);
            console.log(chosenRestaurant);
        }
        if(chosenRestaurant.name && chosenRestaurant._id) {
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
router.post('/add', function (req, res) {
    var db = req.db;
    var collection = db.get('restaurants');
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
    var collection = db.get('restaurants');
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

function generateRandomRestaurant(data) {
    var uniqueRestaurants = {};
    var keys;

    for(var i=0; i<data.length; i++) {
        uniqueRestaurants[data[i].name] = data[i]
    }

    keys = Object.keys(uniqueRestaurants)
    chosenRestaurant = uniqueRestaurants[keys[ keys.length * Math.random() << 0]];
    return chosenRestaurant;
}

module.exports = router;