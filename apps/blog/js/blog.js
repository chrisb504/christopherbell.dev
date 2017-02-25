(function () {
    var app = angular.module("blog", []);
    app.directive("blogDirective", function() {
        return {
            templateUrl : 'apps/blog/html/blog.html',
            scope: {}
        };
    });
    
    app.controller('blogController', function($scope, $http, BlogFactory, $window) {
        vm = this;
        $scope.articles;
        
        BlogFactory.get().success(function (msg) {
            $scope.articles = msg.Articles;
        });
    });
    
    app.factory('BlogFactory', function ($http) {
        return {
            get: function () {
                return $http.get('/apps/blog/data/blogdata.json');
            }
        };
    });

    angular.bootstrap(document.getElementById("blog"), ['blog']);
})();


