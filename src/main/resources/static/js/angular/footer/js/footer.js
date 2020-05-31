(function () {
    var footer = angular.module('footer', []);

    footer.directive('footerDirective', function () {
        return {
            templateUrl: '/apps/footer/html/footer.html',
            scope: {}
        };
    });

    footer.controller('footerController', function ($scope) {
        vm = this;
    });

    angular.bootstrap(document.getElementById("footer"), ['footer']);
})();