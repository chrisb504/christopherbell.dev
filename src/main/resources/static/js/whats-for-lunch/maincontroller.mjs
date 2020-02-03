import * as restaurant from './restaurantcontroller.mjs';

$(document).ready(() => {
    if ($('#button-add').length > 0) {
        $('#button-add').on('click', restaurant.addRestaurant);
    }

    if ($('#button-delete').length > 0) {
        $('#button-delete').on('click', restaurant.deleteRestaurantWithID);
    }

    if ($('#getRestaurantData').length > 0) {
        restaurant.getData(restaurant.populateRestaurantTable);
    }
});