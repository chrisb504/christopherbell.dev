(function () {
    var blog = angular.module('blog', []);
    blog.directive("blogDirective", function() {
        return {
            templateUrl : 'apps/blog/html/blog.html',
            scope: {}
        };
    });
    
    blog.controller('blogController', function($scope, $http, BlogFactory, $window) {
        vm = this;
        $scope.articles;
        
        BlogFactory.get().success(function (msg) {
            $scope.articles = msg.Articles;
        });
    });
    
    blog.factory('BlogFactory', function ($http) {
        return {
            get: function () {
                return $http.get('/apps/blog/data/blogdata.json');
            }
        };
    });

    angular.bootstrap(document.getElementById('blog'), ['blog']);
})();