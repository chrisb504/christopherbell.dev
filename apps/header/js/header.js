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
