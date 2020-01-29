(function () {
    var blog = angular.module('blog', []);
    blog.directive("blogDirective", function() {
        return {
            templateUrl : '/blog/html/blog.html',
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
                return $http.get('/blog/data/blogdata.json');
            }
        };
    });

    angular.bootstrap(document.getElementById('blog'), ['blog']);
})();
(function () {
    var header = angular.module('header', []);

    header.directive('headerDirective', function() {
        return {
            template: '/apps/header/html/header.html',
            scope: {}
        };
    });

    header.controller('headerController', function($scope) {
        vm = this;
    });

    angular.bootstrap(document.getElementById('header'), ['header']);
})();

(function () {
    var footer = angular.module('footer', []);

    footer.directive('footerDirective', function() {
        return {
            templateUrl : '/apps/footer/html/footer.html',
            scope: {}
        };
    });

    footer.controller('footerController', function($scope) {
        vm = this;
    });

    angular.bootstrap(document.getElementById("footer"), ['footer']);
})();
