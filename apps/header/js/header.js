(function () {
    var header = angular.module('header', []);
    
    header.directive('headerDirective', function() {
        return {
            template: '/directory/Global/header.html',
            scope: {}
        };
    });
    
    header.controller('headerController', function($scope) {
        vm = this;
    });
    
    angular.bootstrap(document.getElementById('header'), ['header']);
})();