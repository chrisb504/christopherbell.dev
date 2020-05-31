export let restaurantController = {
    restaurant: {
        distance: '',
        duration: '',
        did: '',
        lastWent: '',
        location: '',
        name: '',
        phone: '',
        priceRange: ''
    },
    data: {},
    output: ''
};

const location = '/whats-for-lunch';
// if(window.location.origin.includes("localhost")) {
//     location = '';
// } else {
//     location = '/whats-for-lunch';
// }

export function addRestaurant(event) {
    event.preventDefault();
    console.log('Adding rest');

    if (validateRestaurant() === 0) {
        setRestaurant();
        postRestaurant();
    } else {
        // If errorCount is more than 0, error out
        alert('Please fill in all fields');
        return false;
    }
}

export function deleteRestaurant(event) {
    event.preventDefault();
    const confirmation = confirm('Are you sure you want to delete this user?');

    if (confirmation === true) {
        $.ajax({
            type: 'DELETE',
            url: `${location  }/restaurants/delete/${$(this).attr('rel')}`
        }).done((response) => {
            alert("Restaurant Deleted");
            $('input').val('');
            if (response.msg === '') {} else {
                alert('Error: ' + response.msg);
            }
        });
    } else {
        return false;
    }
}

export function deleteRestaurantWithID(event) {
    event.preventDefault();
    const confirmation = confirm('Are you sure you want to delete this user?');

    if (confirmation === true) {
        $.ajax({
            type: 'DELETE',
            url: `${location  }/restaurants/delete/${  $('#input-restaurant-id').val()}`
        }).done((response) => {
            alert("Restaurant Deleted");
            $('input').val('');
            if (response.msg === '') {} else {
                alert('Error: ' + response.msg);
            }
        });
    } else {
        return false;
    }
}

function generateRandomRestaurant() {
    let uniqueRestaurants = {};
    let keys;

    for (let i = 0; i < restaurantController.data.length; i++) {
        uniqueRestaurants[restaurantController.data[i].name] = restaurantController.data[i];
    }

    keys = Object.keys(uniqueRestaurants);
    restaurantController.restaurant = uniqueRestaurants[keys[keys.length * Math.random() << 0]];
}

function getCookie(name) {
    let value = '; ' + document.cookie;
    let parts = value.split('; ' + name + '=');
    if (parts.length == 2) return parts.pop().split(';').shift();
}

export function getData(callback) {
    console.log('Getting Data');
    console.log(location);
    console.log(`${location  }/restaurants/`);
    $.getJSON(`${location  }/restaurants/`, (data) => {
            restaurantController.data = data;
            console.log("Restaurant Data");
            console.log(restaurantController.data);
        })
        .done(() => {
            console.log("Starting CallBack");
            callback();
        });
}

export function populateRestaurantTable() {
    let chosenRestaurant = '';

    console.log('Starting to populate table');
    generateRandomRestaurant();

    console.log('Chosen Restaurant');
    console.log(restaurantController.restaurant);
    console.log(getCookie('selectedRestaurantName'));

    if (getCookie('selectedRestaurantName')) {
        chosenRestaurant = getCookie('selectedRestaurantName');
    } else {
        // chosenRestaurant = restaurantController.restaurant.name;
    }

    restaurantController.output += '<div id="restaurant-name">';
    restaurantController.output += `${chosenRestaurant.split('%20').join(' ')  }</div>`;

    $('#fields').html(restaurantController.output);
}

function postRestaurant() {
    console.log(restaurantController.restaurant);
    $.ajax({
        type: 'POST',
        data: restaurantController.restaurant,
        url: `${location}/restaurants/add`,
        dataType: 'JSON'
    }).done((response) => {
        if (response.msg === '') {
            // Clear the form inputs
            $('input').val('');
            alert("Restaurant Posted");
        } else {
            alert('Error: ' + response.msg);
        }
    });
}

function setRestaurant() {
    restaurantController.restaurant = {
        name: $('input#input-name').val(),
        phone: $('input#input-phone').val(),
        location: $('input#input-location').val(),
        duration: $('#input#input-duration').val(),
        priceRange: $('input#input-price-range').val(),
        distance: $('input#input-distance').val()
    };
}

function validateRestaurant() {
    // Super basic validation - increase errorCount variable if any fields are blank
    let errorCount = 0;
    $('#input').each(function (index, val) {
        if ($(this).val() === '') {
            errorCount++;
        }
    });
    return errorCount;
}