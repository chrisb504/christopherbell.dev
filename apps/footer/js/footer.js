(function () {
    var footer = angular.module('footer', []);
    
    footer.directive('footerDirective', function() {
        return {
            templateUrl : '/Directory/Global/footer.html',
            scope: {}
        };
    });
    
    footer.controller('footerController', function($scope) {
        vm = this;
    });
    
    angular.bootstrap(document.getElementById("footer"), ['footer']);
})();


