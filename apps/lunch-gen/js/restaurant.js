(function () {
    var restaurant = angular.module('restaurant', []);
    restaurant.directive("restaurantDirective", function() {
        return {
            templateUrl : 'apps/lunch-gen/html/lunch-gen.html',
            scope: {}
        };
    });
    
    restaurant.controller('restaurantController', function($scope, $http, RestaurantFactory, $window) {
        vm = this;
        $scope.articles;
        
        RestaurantFactory.get().success(function (msg) {
            $scope.articles = msg.Articles;
        });
    });
    
    restaurant.factory('RestaurantFactory', function ($http) {
        return {
            get: function () {
                return $http.get('/apps/lunch-gen/data/restaurant.json');
            }
        };
    });

    angular.bootstrap(document.getElementById('restaurant'), ['restaurant']);
})();